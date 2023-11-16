package com.hanwha.robotics.user.common.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviceHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(),e);
        
        RuntimeException runtimeException = new RuntimeException(e.getClass().getName());
        runtimeException.setStackTrace(new StackTraceElement[0]);
        return  new ResponseEntity<>(runtimeException, HttpStatus.INTERNAL_SERVER_ERROR); 
    }
    
}
