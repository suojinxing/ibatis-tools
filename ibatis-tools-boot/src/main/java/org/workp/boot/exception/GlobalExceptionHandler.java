package org.workp.boot.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.workp.common.exception.BaseException;
import org.workp.common.http.CodeEnum;
import org.workp.common.http.Result;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result baseException(BaseException e) {
        logger.error("业务基础异常 ", e);
        Result result = new Result();
        result.setCode(e.getErrorCode());
        result.setMessage("未知异常");
        return result;
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result invalidFormatException(InvalidFormatException e) {
        logger.error("非法格式化参数异常 ", e);
        Result result = new Result();
        result.setCode(CodeEnum.ERROR.getCode());
        return result;
    }

    @ExceptionHandler(MissingRequestValueException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result missingRequestValueException(MissingRequestValueException e) {
        logger.error("缺少请求参数 ", e);
        Result result = new Result();
        result.setCode(CodeEnum.ERROR.getCode());
        return result;
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result httpMessageConversionException(HttpMessageConversionException e) {
        logger.error("http报文转换异常 ", e);
        Result result = new Result();
        result.setCode(CodeEnum.ERROR.getCode());
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result httpMessageConversionException(MethodArgumentNotValidException e) {
        logger.error("参数验证异常 ", e);
        Result result = new Result();
        result.setCode(CodeEnum.ERROR.getCode());
        return result;
    }
}
