package org.workp.core.domain.command;

import lombok.Data;
import org.workp.core.domain.model.aggregate.IBatisLog;
import org.workp.core.domain.model.valueobject.IpAddress;

import java.time.LocalDateTime;

@Data
public class ParseLogCommand {
    private String originLogStr;
    private LocalDateTime startDateTime;
    private String ip;

    public IBatisLog toIBatisLog() {
        return IBatisLog.builder()
                .originLogStr(originLogStr)
                .startDateTime(startDateTime)
                .ipAddress(new IpAddress(ip))
                .build();
    }
}
