package com.example.RestApi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class noTeacher extends RuntimeException {

	public noTeacher(String message) {
		super(message);
	}
}
