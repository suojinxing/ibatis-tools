package org.workp.core.interaction.rest.assembler;

import org.workp.core.domain.command.ParseLogCommand;
import org.workp.core.infrastructure.IpUtils;
import org.workp.core.interaction.rest.dto.IBatisLogParsedDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class IBatisLogCommandDtoAssembler {
    public static ParseLogCommand toCommand(IBatisLogParsedDto dto, HttpServletRequest request) {
        String ipAddr = IpUtils.getIpAddr(request);
        ParseLogCommand command = new ParseLogCommand();
        command.setOriginLogStr(dto.getOriginLogStr());
        command.setStartDateTime(LocalDateTime.now());
        command.setIp(ipAddr);
        return command;
    }
}
