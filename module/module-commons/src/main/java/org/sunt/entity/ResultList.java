package org.sunt.entity;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collection;

@Getter
public class ResultList<T> extends ResultData<Collection<T>> {

    private final long total;

    public ResultList(Collection<T> data, int code, long total, String message) {
        super(data, code, message);
        this.total = total;
    }

    public static <T> ResultList<T> success(Collection<T> data, long total) {
        return new ResultList<>(data, HttpStatus.OK.value(), total, null);
    }

    public static <T> ResultList<T> success(Collection<T> data) {
        return success(data, data.size());
    }

    public static <T> ResultData<Collection<T>> failure(String message) {
        return new ResultList<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), 0, message);
    }
}
