package com.example.RestApi.Exceptions;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptions extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleALLException(Exception ex, WebRequest request) throws Exception {
		ExceptoinDetails exceptiondetails=new ExceptoinDetails(ex.getMessage());
		return new ResponseEntity<Object>(exceptiondetails,HttpStatus.INTERNAL_SERVER_ERROR); 
		
	}
	
	@ExceptionHandler(noTeacher.class)
	public final ResponseEntity<ExceptoinDetails> UserNotFoundException(Exception ex, WebRequest request) throws Exception {
		ExceptoinDetails exceptiondetails=new ExceptoinDetails(ex.getMessage());
		return new ResponseEntity<ExceptoinDetails>(exceptiondetails,HttpStatus.NOT_FOUND); 
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String err=" ";
//		for(int i=0;i<ex.getFieldErrorCount();i++){
		List<FieldError> ferr=ex.getFieldErrors();
			for(int i=0;i<ferr.size();i++){
				err=err+" "+(i+1)+":"+ferr.get(i).getDefaultMessage();
			}
		
		ExceptoinDetails exceptiondetails=new ExceptoinDetails(" total errors:"+ex.getFieldErrorCount()+""+err);
		return new ResponseEntity<Object>(exceptiondetails,HttpStatus.BAD_REQUEST); 
	}
}
