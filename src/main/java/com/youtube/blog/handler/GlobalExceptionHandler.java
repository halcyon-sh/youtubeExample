package com.youtube.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //모든곳에서 exception 발생 시 이 Class 수행시키기 위함
@RestController
public class GlobalExceptionHandler {
    //Exception이 발생 시 호출되도록 설정

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e) {
        return "</h1>" + e.getMessage() + "</h1>";
    }
}
