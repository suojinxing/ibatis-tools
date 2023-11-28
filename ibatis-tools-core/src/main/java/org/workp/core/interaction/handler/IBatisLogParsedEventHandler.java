package org.workp.core.interaction.handler;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.workp.core.domain.event.IBatisLogParsedEvent;
import org.workp.core.domain.model.aggregate.IBatisLog;
import org.workp.core.domain.repository.IBatisLogRepository;

import javax.annotation.Resource;


/**
 * 日志被解析事件监听者
 */
@Component
public class IBatisLogParsedEventHandler implements ApplicationListener<IBatisLogParsedEvent> {
    @Resource
    private IBatisLogRepository iBatisLogRepository;

    @Override
    public void onApplicationEvent(IBatisLogParsedEvent event) {
        IBatisLog iBatisLog = event.getSource();
        // 日志被解析后，需要保存入库
        iBatisLogRepository.save(iBatisLog);
    }
}
