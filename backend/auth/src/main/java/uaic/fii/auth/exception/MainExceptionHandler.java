package uaic.fii.auth.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ValidationException;

@ControllerAdvice
public class MainExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {RuntimeException.class, ValidationException.class})
    public String handleException() {
        return "Couldn't generate jwt token";
    }

}
