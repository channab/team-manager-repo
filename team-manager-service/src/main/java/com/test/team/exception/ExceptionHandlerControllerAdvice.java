package com.test.team.exception;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.test.team.response.model.WebResponse;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody WebResponse handleResourceNotFound(final EntityNotFoundException exception,
			final HttpServletRequest request) {

		WebResponse error = new WebResponse("Failed", exception.getMessage(), Response.SC_NOT_FOUND);

		return error;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
	public @ResponseBody WebResponse handleResourceNotFound(final Exception exception,
			final HttpServletRequest request) {

		WebResponse error = new WebResponse("Failed", "Application Error!",  Response.SC_SERVICE_UNAVAILABLE);

		return error;
	}
}
