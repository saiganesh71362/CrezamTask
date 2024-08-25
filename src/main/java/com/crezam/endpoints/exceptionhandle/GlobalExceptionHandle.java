package com.crezam.endpoints.exceptionhandle;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandle {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ExceptionMessage> handleIdNotFoundException(IdNotFoundException idNotFoundException,
			WebRequest webRequest) {

		ExceptionMessage message = new ExceptionMessage(new Date(), idNotFoundException.getMessage(),
				webRequest.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(JobNotFoundException.class)
	public ResponseEntity<ExceptionMessage> handleCategoryNotFoundException(JobNotFoundException jobNotFoundException,
			WebRequest webrequest) {
		ExceptionMessage message = new ExceptionMessage(new Date(), jobNotFoundException.getMessage(),
				webrequest.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

}
