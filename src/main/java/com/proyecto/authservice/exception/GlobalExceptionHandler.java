package com.proyecto.authservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(BadRequestException ex, HttpServletRequest req) {
        return new ErrorResponse(Instant.now(), 400, "Bad Request", ex.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(NotFoundException ex, HttpServletRequest req) {
        return new ErrorResponse(Instant.now(), 404, "Not Found", ex.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler({ UnauthorizedException.class, BadCredentialsException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUnauthorized(RuntimeException ex, HttpServletRequest req) {
        return new ErrorResponse(Instant.now(), 401, "Unauthorized", ex.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        var firstError = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .orElse("Petición inválida");
        return new ErrorResponse(Instant.now(), 400, "Validation Error", firstError, req.getRequestURI());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraint(ConstraintViolationException ex, HttpServletRequest req) {
        var msg = ex.getConstraintViolations().stream()
                .findFirst()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .orElse("Petición inválida");
        return new ErrorResponse(Instant.now(), 400, "Validation Error", msg, req.getRequestURI());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMalformed(HttpMessageNotReadableException ex, HttpServletRequest req) {
        return new ErrorResponse(Instant.now(), 400, "Malformed JSON", "Cuerpo de la petición inválido", req.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleAll(Exception ex, HttpServletRequest req) {
        return new ErrorResponse(Instant.now(), 500, "Internal Server Error", ex.getMessage(), req.getRequestURI());
    }
}
