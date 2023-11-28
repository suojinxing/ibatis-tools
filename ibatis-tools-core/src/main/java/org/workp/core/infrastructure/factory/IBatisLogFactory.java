package org.workp.core.infrastructure.factory;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.workp.core.domain.model.aggregate.IBatisLog;
import org.workp.core.infrastructure.po.IBatisLogPo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, Date.class, ZoneId.class})//交给spring管理
public interface IBatisLogFactory {
    //@Mapping(source = "iBatisLog.parsedLog.parsedResultStr", target = "parsedResultStr")
    //@Mapping(source = "iBatisLog.IpAddress.ip", target = "ip")
    //@Mapping(source = "iBatisLog.duration", target = "duration")
    IBatisLogPo toPo(IBatisLog iBatisLog);
}
