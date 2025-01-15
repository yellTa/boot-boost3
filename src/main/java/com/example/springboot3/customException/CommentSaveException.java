package com.example.springboot3.customException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommentSaveException extends RuntimeException {

	@ExceptionHandler(CommentSaveException.class)
	public ResponseEntity<?> saveException(CommentSaveException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("result", "fail");
		return ResponseEntity.internalServerError()
							 .body(response);
	}

}
