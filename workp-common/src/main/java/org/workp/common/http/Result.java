package org.workp.common.http;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Result<T> {
    private T data;
    private CodeEnum code;
    private String message;
}
