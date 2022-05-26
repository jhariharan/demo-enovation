package com.enovation.demoenovation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends Exception{

  public EmployeeNotFoundException(String message,int id){
    super("Employee not found with this id : " + id);
  }
}
