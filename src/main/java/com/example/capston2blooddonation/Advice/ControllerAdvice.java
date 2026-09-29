package com.example.capston2blooddonation.Advice;

import com.example.capston2blooddonation.ApiResponse.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

   @ExceptionHandler(ApiException.class)
   public ResponseEntity<?> handelApiException(ApiException e){
       String message = e.getMessage();
       return ResponseEntity.status(400).body(message);
   }
}
