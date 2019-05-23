package ru.bell.practice.springboot.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bell.practice.springboot.response.view.*;
import ru.bell.practice.springboot.exception.*;

import javax.validation.ValidationException;

@RestControllerAdvice
public class ResponseWrapper implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (methodParameter.getParameterType().equals(void.class)) {
            return new SuccessResponseView("success");
        }

        if (methodParameter.getParameterType().equals(ErrorResponseView.class)) {
            return o;
        }

        return new DataResponseView(o);
    }

    /**
     * Формирует сообщение об ошибке
     *
     * @param e обрабатываемое исключение
     * @return view с сообщением об ошбике
     */
    @ExceptionHandler(ValidationException.class)
    public ErrorResponseView dataValidationException(RuntimeException e){
        return new ErrorResponseView("Invalid input");
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ErrorResponseView dataSearchException (RuntimeException e) {
        return new ErrorResponseView("At your request, nothing found");
    }

    @ExceptionHandler(WrongRequestException.class)
    public ErrorResponseView saveException (RuntimeException e) {
        return new ErrorResponseView("Failed to update or save data");
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponseView exceptionHandler(Exception e){
        return new ErrorResponseView("Error");
    }
}
