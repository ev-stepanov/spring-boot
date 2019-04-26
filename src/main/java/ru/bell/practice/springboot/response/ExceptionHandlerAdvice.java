package ru.bell.practice.springboot.response;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bell.practice.springboot.exception.RecordNotFoundException;
import ru.bell.practice.springboot.exception.WrongRequestException;
import ru.bell.practice.springboot.response.view.ErrorResponseView;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({WrongRequestException.class, RecordNotFoundException.class})
    public ErrorResponseView exceptionHandler(RuntimeException e){
        return new ErrorResponseView(e.getMessage());
    }
}
