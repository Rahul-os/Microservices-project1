package loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import loan.exception.ApplicationNotFoundException;
@ControllerAdvice
public class ApplicationExceptionHandler {
	@ExceptionHandler(value = ApplicationNotFoundException.class)
	public ResponseEntity<Object> exception(ApplicationNotFoundException ex){
		return new ResponseEntity<Object>("Application not available",HttpStatus.NOT_FOUND);

}
}
