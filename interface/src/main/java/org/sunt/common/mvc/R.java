package org.sunt.common.mvc;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.Map;

@Getter
public class R<T> {

    private int code;
    private String message;
    private T data;
    private Long total;
    private final long timestamp = System.currentTimeMillis();

    public R() {
    }

    public R(T data, int code, Long total, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
    }

    public static <S> R<S> success(S data) {
        Long total = data instanceof Collection ? (Long) (long) ((Collection<?>) data).size() : data instanceof Map ? (Long) (long) ((Map<?, ?>) data).size() : null;
        return new R<>(data, HttpStatus.OK.value(), total, null);
    }

    public static <S> R<S> success(S data, long total) {
        return new R<>(data, HttpStatus.OK.value(), total, null);
    }

    public static <S> R<S> failure(String message) {
        return new R<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, message);
    }


}
