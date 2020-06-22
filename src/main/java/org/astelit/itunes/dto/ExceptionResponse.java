package org.astelit.itunes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.astelit.itunes.utils.DateUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ExceptionResponse {
    private final long timestamp = DateUtils.nowUnix();
    private final int status;
    private final String message;
    private final String path;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Map<String, String> validation;

    public ExceptionResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.validation = null;
    }

    public ExceptionResponse(BindException ex, String path) {
        this.status = 400;
        this.path = path;
        this.validation = ex.getFieldErrors()
                .stream()
                .collect(HashMap::new, (k, v) -> k.put(v.getField(), v.getDefaultMessage()), HashMap::putAll);

        this.message = this.validation.entrySet()
                .stream()
                .map(e -> String.format("%s: %s", e.getKey(), e.getValue()))
                .collect(Collectors.joining("; "));
    }

    public ExceptionResponse(ConstraintViolationException ex, String path) {
        this.status = 400;
        this.path = path;
        this.validation = ex.getConstraintViolations()
                .stream()
                .collect(HashMap::new, (k, v) -> k.put(v.getPropertyPath().toString(), v.getMessage()), HashMap::putAll);

        this.message = this.validation.entrySet()
                .stream()
                .map(e -> String.format("%s: %s", e.getKey(), e.getValue()))
                .collect(Collectors.joining("; "));
    }

    public ExceptionResponse(MethodArgumentNotValidException ex, String path) {
        this.status = 400;
        this.path = path;
        this.validation = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(HashMap::new, (k, v) -> k.put(v.getField(), v.getDefaultMessage()), HashMap::putAll);
        this.message = this.validation.entrySet()
                .stream()
                .map(e -> String.format("%s: %s", e.getKey(), e.getValue()))
                .collect(Collectors.joining("; "));
    }
}
