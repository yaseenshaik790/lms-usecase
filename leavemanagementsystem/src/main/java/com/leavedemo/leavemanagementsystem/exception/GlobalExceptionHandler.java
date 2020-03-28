package com.leavedemo.leavemanagementsystem.exception;

/**
 * GlobalExceptionHandler class is used to handle all the exception classes
 * @author ShaikYaseen
 */
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.leavedemo.leavemanagementsystem.dto.ErrorResponse;
import com.leavedemo.leavemanagementsystem.dto.ExceptionResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {

		if (ex instanceof MethodArgumentNotValidException) {

			MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;

			List<String> errorList = exception.getBindingResult()

					.getFieldErrors()

					.stream()

					.map(fieldError -> fieldError.getDefaultMessage())

					.collect(Collectors.toList());

			ErrorResponse errorDetails = new ErrorResponse("this is a message from handler", errorList, 856);

			return super.handleExceptionInternal(ex, errorDetails, headers, status, request);

		}
		return super.handleExceptionInternal(ex, body, headers, status, request);

	}

	@ExceptionHandler(LeavesNotAvailableException.class)
	public ResponseEntity<Object> leavesNotAvailableException(LeavesNotAvailableException leavesNotAvailableException) {

		ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(leavesNotAvailableException.getMessage(),
				leavesNotAvailableException.getErrorCode());

		return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(WeekendDateException.class)
	public ResponseEntity<Object> weekendDateException(WeekendDateException weekendDateException) {

		ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(weekendDateException.getMessage(),
				weekendDateException.getErrorCode());

		return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(InvalidFromDateException.class)
	public ResponseEntity<Object> invalidFromDateException(InvalidFromDateException invalidFromDateException) {

		ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(invalidFromDateException.getMessage(),
				invalidFromDateException.getErrorCode());

		return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(LeaveIdNotAvailableException.class)
	public ResponseEntity<Object> leaveCatagoryNotAvailableException(
			LeaveIdNotAvailableException leaveCatagoryNotAvailableException) {

		ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
				leaveCatagoryNotAvailableException.getMessage(), leaveCatagoryNotAvailableException.getErrorCode());

		return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(InvalidToDateException.class)
	public ResponseEntity<Object> invalidToDateException(InvalidToDateException invalidToDateException) {

		ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(invalidToDateException.getMessage(),
				invalidToDateException.getErrorCode());

		return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> userNotFoundException(UserNotFoundException userNotFoundException) {

		ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(userNotFoundException.getMessage(),
				userNotFoundException.getErrorCode());

		return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);

	}

}
