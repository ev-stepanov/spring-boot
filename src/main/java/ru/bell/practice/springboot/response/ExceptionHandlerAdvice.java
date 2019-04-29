package ru.bell.practice.springboot.response;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bell.practice.springboot.exception.RecordNotFoundException;
import ru.bell.practice.springboot.exception.WrongRequestException;
import ru.bell.practice.springboot.response.view.ErrorResponseView;

/**
 * Содержит обработчики исключений, формирующие соответствующий ответ
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * Формирует сообщение об ошибке
     * @param e обрабатываемое исключение
     * @return view с сообщением об ошбике
     */
    @ExceptionHandler({WrongRequestException.class, RecordNotFoundException.class})
    public ErrorResponseView exceptionHandler(RuntimeException e){
        return new ErrorResponseView(e.getMessage());
    }
}
