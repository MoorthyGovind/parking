package com.hcl.parking.exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcl.parking.common.AppConstants;
import com.hcl.parking.response.ApiResponse;

import javassist.NotFoundException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**handleMethodArgumentNotValid
     * error handle for @Valid
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, 
    		HttpStatus status, WebRequest request) {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    	Date currentDate = new Date();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", formatter.format(currentDate));
        body.put("status", status.value());

        //Get all errors for field valid
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }
    
    @ExceptionHandler(NotFoundException.class)
  	public ResponseEntity<ApiResponse> handleNotFoundException(Exception ex){
    	ApiResponse apiResponse = new ApiResponse(AppConstants.FAILURE, HttpStatus.NOT_FOUND.value(), ex.getMessage());
    	return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  	}
    
    @ExceptionHandler(ParseException.class)
  	public ResponseEntity<ApiResponse> handleParseException(Exception ex){
    	ApiResponse apiResponse = new ApiResponse(AppConstants.FAILURE, HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    	return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  	}
}
