package org.workp.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCodeEnum {
    /**
     * 异常分为这几类
     * 用户可见的提示异常，比如操作项必输，手机号格式错误等。
     * 用户不可见的服务器异常，只展示给用户错误代码，程序员根据错误代码来定位问题
     *
     * 用户可见的提示异常 1开头
     * 用户不可见的服务器异常 2开头
     */
    UNKNOWN_HOST("200000", "未知主机地址");
    private final String code;
    private final String message;
}
