package org.workp.core.application.commandservice;

import org.springframework.stereotype.Service;
import org.workp.core.domain.command.ParseLogCommand;
import org.workp.core.domain.model.aggregate.IBatisLog;
import org.workp.core.domain.model.valueobject.ParsedLog;

@Service
public class IBatisLogCommandService {
    public ParsedLog parseLog(ParseLogCommand command) {
        IBatisLog iBatisLog = command.toIBatisLog();
        IBatisLog result = iBatisLog.parse();
        return result.getParsedLog();
    }
}
