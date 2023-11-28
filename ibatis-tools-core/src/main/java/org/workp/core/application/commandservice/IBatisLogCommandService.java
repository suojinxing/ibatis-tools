package org.workp.core.application.commandservice;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.workp.core.domain.command.ParseLogCommand;
import org.workp.core.domain.event.IBatisLogParsedEvent;
import org.workp.core.domain.model.aggregate.IBatisLog;
import org.workp.core.domain.model.valueobject.ParsedLog;

import javax.annotation.Resource;

@Service
public class IBatisLogCommandService {
    @Resource
    private ApplicationEventPublisher eventPublisher;
    public ParsedLog parseLog(ParseLogCommand command) {
        // step1. 准备领域对象
        IBatisLog iBatisLog = command.toIBatisLog();
        // step2. 执行业务——解析ibatis的log日志
        IBatisLog result = iBatisLog.parse();
        // step3. 发送领域事件
        eventPublisher.publishEvent(new IBatisLogParsedEvent(iBatisLog));
        return result.getParsedLog();
    }
}
