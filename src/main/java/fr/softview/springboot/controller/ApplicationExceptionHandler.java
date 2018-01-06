package fr.softview.springboot.controller;

import fr.softview.springboot.exception.AccessForbiddenException;
import fr.softview.springboot.exception.AccountException;
import fr.softview.springboot.exception.OperationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;

/**
 * Created by Samba Kamara on 21/11/2017.
 */

@ControllerAdvice
public class ApplicationExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public String handleInputTypeValidationException(MethodArgumentTypeMismatchException e)  {
        return HttpStatus.BAD_REQUEST.toString();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public String handleInputParameterMissingException(MissingServletRequestParameterException e)  {
        return HttpStatus.BAD_REQUEST.toString();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccountException.class)
    @ResponseBody
    public String handleOperationException(Exception e) throws IOException {
        return HttpStatus.BAD_REQUEST.toString();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OperationException.class)
    @ResponseBody
    public String handleAccountException(Exception e) throws IOException {
        return HttpStatus.BAD_REQUEST.toString();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessForbiddenException.class)
    @ResponseBody
    public String handleUnauthorizedException(Exception e) throws IOException {
        return HttpStatus.FORBIDDEN.toString();
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public String handleBadMethodException(Exception e) throws IOException {
        return HttpStatus.METHOD_NOT_ALLOWED.toString();
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) throws IOException {
        return HttpStatus.INTERNAL_SERVER_ERROR.toString();
    }


}
