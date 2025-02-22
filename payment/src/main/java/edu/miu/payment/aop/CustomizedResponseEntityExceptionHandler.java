package edu.miu.payment.aop;

import edu.miu.payment.dto.ErrorResponse;
import edu.miu.payment.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse();
        ErrorResponse.Error errorDetails = new ErrorResponse.Error();

        errorDetails.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.setMessage(ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred");

        errorResponse.setError(errorDetails);

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        ErrorResponse.Error errorDetails = new ErrorResponse.Error();

        errorDetails.setCode(HttpStatus.NOT_FOUND.value());
        errorDetails.setMessage(ex.getMessage() != null ? ex.getMessage() : "User not found");

        errorResponse.setError(errorDetails);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}