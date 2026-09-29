package com.example.capston2blooddonation.Advice;

import com.example.capston2blooddonation.ApiResponse.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;


@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

   @ExceptionHandler(value = ApiException.class)
   public ResponseEntity<?> ApiException(ApiException e){
       String message = e.getMessage();
       return ResponseEntity.status(400).body(message);
   }

   //Did not follow the attribute instruction Ex: add 5 numbers will @Min = 10
   @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationException(MethodArgumentNotValidException e){
       String message = e.getBindingResult().getFieldError().getDefaultMessage();
       return ResponseEntity.status(400).body(message);
   }

   //input miss match (input a String instead of Integer)
   @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> JsonException(HttpMessageNotReadableException e){
       return ResponseEntity.status(400).body("Invalid data input");
   }




}
