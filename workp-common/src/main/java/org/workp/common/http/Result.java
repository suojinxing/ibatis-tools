package org.workp.common.http;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Result<T> {
    private T data;
    private String code;
    private String message;


    public static <T> Result<T> success() {
        return new Result<>();
    }
}
