package org.example.fitnesstrackingsystem.ControllerAdvise;

import org.example.fitnesstrackingsystem.ApiResponse.ApiException;
import org.example.fitnesstrackingsystem.ApiResponse.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Objects;

@ControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiResponse<String>> apiException(ApiException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(message));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = Objects.requireNonNull(e.getFieldError()).getDefaultMessage();

        return ResponseEntity.status(400).body(new ApiResponse<>(message));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<String>> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(message));
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<String>> NoResourceFoundException(NoResourceFoundException e) {
        String message = e.getMessage();

        return ResponseEntity.status(400).body(new ApiResponse<>(message));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<String>> DataIntegrityViolationException(DataIntegrityViolationException e) {
        String message = e.getMessage();

        return ResponseEntity.status(400).body(new ApiResponse<>(message));
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ApiResponse<String>> NullPointerException(NullPointerException e) {
        String message = e.getMessage();

        return ResponseEntity.status(400).body(new ApiResponse<>(message));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<String>> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String message = e.getMessage();

        return ResponseEntity.status(400).body(new ApiResponse<>(message));
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiResponse<String>> HttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(message));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<String>> Exception(Exception e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse<>(message));
    }

}
