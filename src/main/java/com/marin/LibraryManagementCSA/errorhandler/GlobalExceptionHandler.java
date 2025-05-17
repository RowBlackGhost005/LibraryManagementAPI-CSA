package com.marin.LibraryManagementCSA.errorhandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        String path = request.getRequestURI();

        // Exclude Swagger API docs path due to a random bug that doesn't allow swagger to execute correctly
        if (path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui")) {
            return ResponseEntity.ok("Swagger API docs request ignored.");
        }

        System.err.println(ex.getMessage());
        System.err.println(ex.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something unexpected happened in the server");
    }

}
