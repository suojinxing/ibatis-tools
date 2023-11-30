package org.workp.core.infrastructure.repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;
import org.workp.common.http.PageDto;
import org.workp.core.domain.model.aggregate.IBatisLog;
import org.workp.core.domain.query.IBatisLogQuery;
import org.workp.core.domain.repository.IBatisLogRepository;
import org.workp.core.infrastructure.factory.IBatisLogFactory;
import org.workp.core.infrastructure.mapper.IBatisLogMapper;
import org.workp.core.infrastructure.po.IBatisLogPo;
import org.workp.core.interaction.rest.dto.IBatisLogQueryDto;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public PageDto query(IBatisLogQuery query) {
        PageInfo<IBatisLogPo> res = PageHelper.startPage(query.getPage().getPageNumber(), query.getPage().getPageSize()).doSelectPageInfo(
                () -> iBatisLogMapper.query()
        );
        List<IBatisLogPo> poList = res.getList();
        List<IBatisLogQueryDto> dtoList = poList.stream().map(po -> iBatisLogFactory.toQueryResult(po)).collect(Collectors.toList());
        return new PageDto(res.getTotal(), dtoList);
    }
}
