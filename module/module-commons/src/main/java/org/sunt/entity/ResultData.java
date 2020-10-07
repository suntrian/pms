package org.sunt.entity;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResultData<T> {

    private final T data;
    private final int code;
    private final String message;

    public ResultData(T data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(data, HttpStatus.OK.value(), null);
    }

    public static <T, R extends ResultData<T>> R failure(String message) {
        return new ResultData<T>(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }


}
