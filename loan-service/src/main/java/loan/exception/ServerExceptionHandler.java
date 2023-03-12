package loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServerExceptionHandler {
	@ExceptionHandler(value = ServerNotFoundException.class)
	public ResponseEntity<Object> exception(ServerNotFoundException ex){
	return new ResponseEntity<Object>("Server is down!!!",HttpStatus.NOT_FOUND);
	}
}
