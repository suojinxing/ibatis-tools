package org.workp.log;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * 拦截器四大对象
 * 1. Executor
 * 2. StatementHandler
 * 3. ResultSetHandler
 * 4. ParameterHandler
 */
@Intercepts(
        @Signature(method = "setParameters", type = ParameterHandler.class, args = {PreparedStatement.class})
)
public class MapperLogInterceptor implements Interceptor {
    public static final Logger log = LoggerFactory.getLogger(MapperLogInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String executableLog = null;
        DefaultParameterHandler parameterHandler = (DefaultParameterHandler) invocation.getTarget();
        MetaObject phMetaObject = SystemMetaObject.forObject(parameterHandler);
        BoundSql boundSql = (BoundSql) phMetaObject.getValue("boundSql");
        Object parameterObject = (Object) phMetaObject.getValue("parameterObject");
        MappedStatement mappedStatement = (MappedStatement) phMetaObject.getValue("mappedStatement");
        Configuration configuration = (Configuration) phMetaObject.getValue("configuration");
        TypeHandlerRegistry typeHandlerRegistry = (TypeHandlerRegistry) phMetaObject.getValue("typeHandlerRegistry");
        // select * from user where id = ? and age > ?
        executableLog = boundSql.getSql();

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                // 如果参数不是存储过程
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    if (boundSql.hasAdditionalParameter(propertyName)) { // issue #448 ask first for additional params
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else {
                        MetaObject metaObject = configuration.newMetaObject(parameterObject);
                        value = metaObject.getValue(propertyName);
                    }
                    JdbcType jdbcType = parameterMapping.getJdbcType();
                    if (value == null && jdbcType == null) {
                        jdbcType = configuration.getJdbcTypeForNull();
                    }
                    String strValue = getStringValue(value, jdbcType);
                    executableLog = executableLog.replaceFirst("\\?", strValue);
                    log.info("Executable ===> {}", executableLog);
                }
            }
        }
        return invocation.proceed();
    }

    private String getStringValue(Object value, JdbcType jdbcType) {
        if (value == null) {
            return "null";
        }

        if (value instanceof Date) {
            String dateFormat = "yyyy-MM-dd";
            String timeFormat = "HH:mm:ss";
            String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = null;
            if (jdbcType == null || JdbcType.TIMESTAMP == jdbcType) {
                sdf = new SimpleDateFormat(dateTimeFormat);
                return sdf.format((Date) value);
            } else if (JdbcType.DATE == jdbcType) {
                sdf = new SimpleDateFormat(dateFormat);
                return sdf.format((Date) value);
            } else if (JdbcType.TIME == jdbcType) {
                sdf = new SimpleDateFormat(timeFormat);
                return sdf.format((Date) value);
            } else {
                log.info("warring jdbcType is {}, but param type is {}", jdbcType, value);
            }
        } else if (value instanceof LocalDateTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return formatter.format((LocalDateTime) value);
        } else if (value instanceof LocalDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return formatter.format((LocalDate) value);
        } else if (value instanceof LocalTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            return formatter.format((LocalTime) value);
        }
        if (value instanceof Number) {
            return value.toString();
        }
        if (value instanceof String) {
            return "'" + (String) value + "'";
        }
        return value.toString();
    }


}
