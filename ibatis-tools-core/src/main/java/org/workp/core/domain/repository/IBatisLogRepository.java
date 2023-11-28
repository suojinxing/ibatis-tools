package org.workp.core.domain.repository;

import org.workp.core.domain.model.aggregate.IBatisLog;

public interface IBatisLogRepository {
    void save(IBatisLog iBatisLog);
}
