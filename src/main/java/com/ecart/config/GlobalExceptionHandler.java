package com.ecart.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecart.model.CommonResponse;
import com.ecart.model.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFound(
            ResourceNotFoundException ex, HttpServletRequest request) {
        
		ExceptionResponse errorObj = new ExceptionResponse(
				String.valueOf(HttpStatus.NOT_FOUND.value()),
                "Not Found",
                ex.getMessage()
        );
        
        return new ResponseEntity<>(errorObj, HttpStatus.NOT_FOUND);
    }

    // Generic fallback handler for any unhandled application errors (HTTP 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGlobalException(
            Exception ex, HttpServletRequest request) {
        
    	ExceptionResponse errorObj = new ExceptionResponse(
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                "Internal Server Error",
                "An unexpected database or system error occurred."
        );
        
        return new ResponseEntity<>(errorObj, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
