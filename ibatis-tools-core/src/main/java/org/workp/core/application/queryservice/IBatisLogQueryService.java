package org.workp.core.application.queryservice;

import cn.hutool.db.Page;
import org.springframework.stereotype.Service;
import org.workp.common.http.PageDto;
import org.workp.core.domain.query.IBatisLogQuery;
import org.workp.core.domain.repository.IBatisLogRepository;

import javax.annotation.Resource;

@Service
public class IBatisLogQueryService {
    @Resource
    private IBatisLogRepository iBatisLogRepository;

    public PageDto query(IBatisLogQuery query) {
        if (query.getPage() == null) {
            query.setPage(Page.of(1, 20));
        }
        return iBatisLogRepository.query(query);
    }
}
