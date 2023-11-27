package org.workp.core.domain.model.aggregate;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.sql.SqlUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.workp.common.tag.AggregateId;
import org.workp.common.tag.AggregateRoot;
import org.workp.core.domain.model.valueobject.IpAddress;
import org.workp.core.domain.model.valueobject.ParsedLog;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@AggregateRoot
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IBatisLog {
    /**
     * 占位符
     */
    public static final String PLACEHOLDER = "@@_@@";
    /**
     * 日志参数分隔符
     */
    public static final String PARAM_SPILT = ",";
    Log log = LogFactory.get();
    @AggregateId
    private ParsedLog parsedLog;
    private String originLogStr;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Duration duration;
    private IpAddress ipAddress;


    public IBatisLog parse() {
        Assert.notBlank(originLogStr, "解析字符不能为空！");
        // 解析不换行模式
        String executedSql = noNewline();
        // 解析换行模式
        this.endDateTime = LocalDateTime.now();
        this.duration = Duration.between(startDateTime, endDateTime);
        this.parsedLog = new ParsedLog(this.originLogStr, executedSql, SqlUtil.formatSql(executedSql));
        return this;
    }

    /**
     * =>  Preparing: select * from user where id = ?
     * ==> Parameters: 1(Integer)
     */
    private String noNewline() {
        List<String> logList = StrUtil.split(originLogStr, "\n");
        Assert.isTrue(CollUtil.size(logList) >= 2, "日志格式错误，起码要两行日志");
        String sqlLog = logList.get(0); // ==>  Preparing: select * from user where id = ?
        String sqlLogParam = logList.get(1); // ==> Parameters: 1(Integer)
        int count = StrUtil.count(sqlLog, "?");
        //log.info("共有{}个参数", count);

        // 解析sql为预处理语句
        String preparedExecutedSql = ""; // select count(1) from table where a = ? and b = ?
        String paramsWithTypeStr = ""; // 1(Integer), ttt(String), 2022-02-22(Date)
        Pattern executedStartPatt = Pattern.compile("(select|update|insert|delete)");
        Pattern paramStartPatt = Pattern.compile("(Parameters:)");
        Matcher preparedExecutedMat = executedStartPatt.matcher(sqlLog);
        Matcher paramMat = paramStartPatt.matcher(sqlLogParam);
        if (preparedExecutedMat.find()) {
            preparedExecutedSql = sqlLog.substring(preparedExecutedMat.start());
            if (count == 0) return preparedExecutedSql;
        }
        if (paramMat.find()) {
            paramsWithTypeStr = sqlLogParam.substring(paramMat.end());
        }
        // ["1(Integer)","ttt(String)",...]
        List<String> paramWithTypeList = StrUtil.split(paramsWithTypeStr, PARAM_SPILT, -1, true, false);
        List<String> quoTypeList = Arrays.asList("(String)", "(Date)", "(Timestamp)"); // 需要+引号的类型
        List<String> nonQuoTypeList = Arrays.asList("(Integer)", "(Long)", "(Double)", "(Float)", "(Decimal)", "(BigDecimal)"); // 不需要加引号的类型
        for (String paramWithType : paramWithTypeList) {
            String param = "";
            for (String type : quoTypeList) {
                if (StrUtil.endWith(paramWithType, type)) {
                    param = StrUtil.format("'{}'", paramWithType.substring(0, paramWithType.length() - type.length())).trim();
                    break;
                }
            }
            for (String type : nonQuoTypeList) {
                if (StrUtil.endWith(paramWithType, type)) {
                    param = StrUtil.format("{}", paramWithType.substring(0, paramWithType.length() - type.length())).trim();
                    break;
                }
            }
            preparedExecutedSql = preparedExecutedSql.replaceFirst("\\?", param);
        }
        return preparedExecutedSql;
    }
}
