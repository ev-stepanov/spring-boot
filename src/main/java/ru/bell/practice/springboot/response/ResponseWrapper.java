package ru.bell.practice.springboot.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bell.practice.springboot.exception.RecordNotFoundException;
import ru.bell.practice.springboot.exception.WrongRequestException;
import ru.bell.practice.springboot.response.view.DataResponseView;
import ru.bell.practice.springboot.response.view.ErrorResponseView;
import ru.bell.practice.springboot.response.view.SuccessResponseView;

import java.lang.reflect.AnnotatedElement;

@RestControllerAdvice
public class ResponseWrapper implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (methodParameter.getParameterType() == void.class) {
            return new SuccessResponseView("success");
        }

        if (methodParameter.getParameterType() == ErrorResponseView.class) {
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
    @ExceptionHandler({WrongRequestException.class, RecordNotFoundException.class})
    public ErrorResponseView exceptionHandler(RuntimeException e){
        return new ErrorResponseView(e.getMessage());
    }

    /**
     * Формирует сообщение об ошибке
     * @param e исключение
     * @return view с сообщением лб ошибке
     */
    @ExceptionHandler(Exception.class)
    public ErrorResponseView unpredictableExceptionHandler(Exception e){
        return new ErrorResponseView(e.getMessage());
    }
}
