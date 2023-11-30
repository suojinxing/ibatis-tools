package org.workp.core.interaction.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IBatisLogQueryDto {
    private Integer id;
    private String originLogStr;
    private String parsedResultStr;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private long duration;
    private String ip;
}
