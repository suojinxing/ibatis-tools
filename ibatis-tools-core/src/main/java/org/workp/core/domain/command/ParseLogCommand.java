package org.workp.core.domain.command;

import lombok.Data;
import org.workp.core.domain.model.aggregate.IBatisLog;

import java.time.LocalDateTime;

@Data
public class ParseLogCommand {
    private String originLogStr;
    private LocalDateTime startDateTime;

    public IBatisLog toIBatisLog() {
        return IBatisLog.builder()
                .originLogStr(originLogStr)
                .startDateTime(startDateTime)
                .build();
    }
}
