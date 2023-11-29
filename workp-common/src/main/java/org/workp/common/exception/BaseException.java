package org.workp.common.exception;

import lombok.Data;
import org.workp.common.enums.ExceptionCodeEnum;

/**
 * 异常基类
 */
@Data
public class BaseException extends RuntimeException {
    private String errorCode;
    private String message;
    private String type;


    public BaseException(ExceptionCodeEnum codeEnum, Throwable cause) {
        super(cause);
        this.errorCode = codeEnum.getCode();
        this.message = codeEnum.getMessage();
        this.type = this.errorCode.substring(0, 1);
    }


}
