package org.workp.core.interaction.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.workp.common.http.PageDto;
import org.workp.common.http.Result;
import org.workp.core.application.commandservice.IBatisLogCommandService;
import org.workp.core.application.queryservice.IBatisLogQueryService;
import org.workp.core.domain.command.ParseLogCommand;
import org.workp.core.domain.model.valueobject.ParsedLog;
import org.workp.core.domain.query.IBatisLogQuery;
import org.workp.core.infrastructure.mapper.IBatisLogMapper;
import org.workp.core.interaction.rest.assembler.IBatisLogCommandDtoAssembler;
import org.workp.core.interaction.rest.dto.IBatisLogParsedDto;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/ibatislog")
@Slf4j
public class IBatisLongController {
    @Resource
    private IBatisLogCommandService iBatisLogCommandService;
    @Resource
    private IBatisLogQueryService iBatisLogQueryService;
    @Resource
    private IBatisLogMapper mapper;

    @PostMapping("/parse")
    public Result parseLog(@RequestBody @Valid IBatisLogParsedDto dto, HttpServletRequest request) {
        log.info("开始解析日志，参数内容：{}", dto);
        ParseLogCommand command = IBatisLogCommandDtoAssembler.toCommand(dto, request);
        ParsedLog result = iBatisLogCommandService.parseLog(command);
        return Result.builder().data(result).build();
    }

    @GetMapping
    public Result<PageDto> query() {
        IBatisLogQuery query = new IBatisLogQuery();
        PageDto pageDto = iBatisLogQueryService.query(query);
        Result<PageDto> result = Result.success();
        result.setData(pageDto);
        return result;
    }
}
