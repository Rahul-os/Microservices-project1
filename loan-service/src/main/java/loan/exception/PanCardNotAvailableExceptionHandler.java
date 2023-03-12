package loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PanCardNotAvailableExceptionHandler {
	@ExceptionHandler(value = PanCardNotAvailableException.class)
	public ResponseEntity<Object> exception(PanCardNotAvailableException ex)
	{
		return new ResponseEntity<Object>("Pan details not available in database!!",HttpStatus.NOT_FOUND);
	}

}
