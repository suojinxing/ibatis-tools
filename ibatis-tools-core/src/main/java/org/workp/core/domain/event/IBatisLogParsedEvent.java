package org.workp.core.domain.event;

import org.springframework.context.ApplicationEvent;
import org.workp.core.domain.model.aggregate.IBatisLog;

/**
 * IBatis日志被解析事件
 */
public class IBatisLogParsedEvent extends ApplicationEvent {
    public IBatisLogParsedEvent(IBatisLog batisLog) {
        super(batisLog);
    }

    @Override
    public IBatisLog getSource() {
        return (IBatisLog) super.getSource();
    }
}
