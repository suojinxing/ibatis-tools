package org.workp.core.infrastructure.repository;

import cn.hutool.core.net.Ipv4Util;
import org.springframework.stereotype.Repository;
import org.workp.core.domain.model.aggregate.IBatisLog;
import org.workp.core.domain.repository.IBatisLogRepository;
import org.workp.core.infrastructure.factory.IBatisLogFactory;
import org.workp.core.infrastructure.mapper.IBatisLogMapper;
import org.workp.core.infrastructure.po.IBatisLogPo;

import javax.annotation.Resource;

@Repository
public class IBatisLogRepositoryImpl implements IBatisLogRepository {
    @Resource
    private IBatisLogMapper iBatisLogMapper;
    @Resource
    private IBatisLogFactory iBatisLogFactory;

    @Override
    public void save(IBatisLog iBatisLog) {
        //IBatisLogPo po = iBatisLogFactory.toPo(iBatisLog);
        IBatisLogPo po = new IBatisLogPo();
        po.setOriginLogStr(iBatisLog.getOriginLogStr());
        po.setParsedResultStr(iBatisLog.getParsedLog().getParsedResultStr());
        po.setStartDateTime(iBatisLog.getStartDateTime());
        po.setEndDateTime(iBatisLog.getEndDateTime());
        po.setDuration(iBatisLog.getDuration().toMillis());
        po.setIp(Ipv4Util.ipv4ToLong(iBatisLog.getIpAddress().getIp()));

        iBatisLogMapper.insert(po);
    }
}
