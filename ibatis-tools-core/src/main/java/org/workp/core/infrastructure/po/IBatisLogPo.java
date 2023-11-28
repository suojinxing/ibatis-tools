package org.workp.core.infrastructure.po;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class IBatisLogPo {
    private Integer id;
    private String originLogStr;
    private String parsedResultStr;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private long duration;
    private long ip;
}
