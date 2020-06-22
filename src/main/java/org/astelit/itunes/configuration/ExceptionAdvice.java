package org.astelit.itunes.configuration;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.core.config.Order;
import org.astelit.itunes.dto.ExceptionResponse;
import org.astelit.itunes.exception.BadRequestException;
import org.astelit.itunes.exception.NotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.validation.ConstraintViolationException;

@Log4j2
@Primary
@RestControllerAdvice
@Order(1)
public class ExceptionAdvice {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> handle(Throwable ex) {
        Throwable root = ExceptionUtils.getRootCause(ex);

        String message = root.getMessage();

        if (StringUtils.containsIgnoreCase(message, "Broken pipe"))
            return null;

        if (StringUtils.trimToEmpty(message).isEmpty())
            message = "Ошибка сервиса";

        String path = "";
        String method = "";

        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes) {
            path = ((ServletRequestAttributes) attributes).getRequest().getRequestURI();
            method = ((ServletRequestAttributes) attributes).getRequest().getMethod();
        }

        HttpStatus status = status(ex, root);
        String logMessage = String.format("[%s] for %s %s", root.getClass(), method, path);

        if (status == HttpStatus.INTERNAL_SERVER_ERROR)
            log.error(logMessage, ex);
        else
            log.warn(logMessage);

        if (ex instanceof BindException)
            return ResponseEntity.status(status)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ExceptionResponse((BindException) root, path));

        if (ex instanceof MethodArgumentNotValidException)
            return ResponseEntity.status(status)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ExceptionResponse((MethodArgumentNotValidException) root, path));

        if (root instanceof ConstraintViolationException)
            return ResponseEntity.status(status)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ExceptionResponse((ConstraintViolationException) root, path));

        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ExceptionResponse(status.value(), message, path));
    }

    private HttpStatus status(Throwable ex, Throwable root) {
        if (ex instanceof NotFoundException)
            return HttpStatus.NOT_FOUND;

        if (ex instanceof BadRequestException)
            return HttpStatus.BAD_REQUEST;

        if (ex instanceof TransactionSystemException)
            if (root instanceof ConstraintViolationException)
                return HttpStatus.BAD_REQUEST;

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
