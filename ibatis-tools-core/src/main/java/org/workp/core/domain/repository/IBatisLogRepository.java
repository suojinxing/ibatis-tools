package org.workp.core.domain.repository;

import org.workp.common.http.PageDto;
import org.workp.core.domain.model.aggregate.IBatisLog;
import org.workp.core.domain.query.IBatisLogQuery;

public interface IBatisLogRepository {
    void save(IBatisLog iBatisLog);

    PageDto query(IBatisLogQuery query);
}
