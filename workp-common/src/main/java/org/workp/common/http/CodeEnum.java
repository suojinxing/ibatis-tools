package org.workp.common.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeEnum {
    ERROR("999999", "未知异常"),
    SUCCESS("000000", "正常"),
    ;

    private final String code;
    private final String desc;
}
