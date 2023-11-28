package org.workp.core.infrastructure.factory;

import cn.hutool.core.net.Ipv4Util;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.workp.core.domain.model.aggregate.IBatisLog;
import org.workp.core.infrastructure.constants.MapStructConst;
import org.workp.core.infrastructure.po.IBatisLogPo;

import java.time.Duration;

@Mapper(componentModel = "spring", imports = {Ipv4Util.class})//交给spring管理
public interface IBatisLogFactory {
    @Mapping(source = "iBatisLog.parsedLog.parsedResultStr", target = "parsedResultStr")
    @Mapping(target = "duration", qualifiedByName = MapStructConst.DURATION2LONG_MILLS)
    @Mapping(target = "ip", expression = "java(Ipv4Util.ipv4ToLong(iBatisLog.getIpAddress().getIp()))")
    IBatisLogPo toPo(IBatisLog iBatisLog);

    @Named(value = MapStructConst.DURATION2LONG_MILLS)
    public default long timeMillisToDateTimeStr(Duration duration) {
        if (duration == null) return 0L;
        return duration.toMillis();
    }

}
