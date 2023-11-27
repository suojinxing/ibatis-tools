package org.workp.core.interfaces.rest.assembler;

import org.workp.core.domain.command.ParseLogCommand;
import org.workp.core.interfaces.rest.dto.ParsedLogDto;

import java.time.LocalDateTime;

public class IBatisLogCommandDtoAssembler {
    public static ParseLogCommand toCommand(ParsedLogDto dto) {
        ParseLogCommand command = new ParseLogCommand();
        command.setOriginLogStr(dto.getOriginLogStr());
        command.setStartDateTime(LocalDateTime.now());
        return command;
    }
}
