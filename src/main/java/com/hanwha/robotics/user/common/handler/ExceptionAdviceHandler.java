package com.hanwha.robotics.user.common.handler;

import com.hanwha.robotics.user.common.handler.exception.BadRequestException;
import com.hanwha.robotics.user.common.handler.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hanwha.robotics.user.common.handler.exception.ForbiddenException;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ExceptionAdviceHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
		log.error(e.getMessage(), e);

		RuntimeException runtimeException = new RuntimeException(e.getClass().getName());
		runtimeException.setStackTrace(new StackTraceElement[0]);
		return new ResponseEntity<>(runtimeException, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<Object> handleForbiddenException(ForbiddenException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
	}

}
