package org.sunt.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collection;

@Getter
public class R<T> {

    private final int code;
    private final String message;
    private final T data;
    private final Long total;
    private final long timestamp = System.currentTimeMillis();

    public R(T data, int code, Long total, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
    }

    public static <S> R<S> success(S data) {
        return new R<>(data, HttpStatus.OK.value(), null, null);
    }

    public static <S> R<S> success(S data, long total) {
        return new R<>(data, HttpStatus.OK.value(), total, null);
    }

    public static <S extends Collection<?>> R<S> success(S data) {
        return new R<>(data, HttpStatus.OK.value(), (long) data.size(), null);
    }

    public static <S> R<S> failure(String message) {
        return new R<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, message);
    }


}
