package org.workp.core.interfaces.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ParsedLogDto {
    @NotBlank
    private String originLogStr;
}
