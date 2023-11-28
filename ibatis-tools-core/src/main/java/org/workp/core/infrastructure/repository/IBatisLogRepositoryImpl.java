package org.workp.core.infrastructure.repository;

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
        IBatisLogPo po = iBatisLogFactory.toPo(iBatisLog);
        iBatisLogMapper.insert(po);
    }
}
