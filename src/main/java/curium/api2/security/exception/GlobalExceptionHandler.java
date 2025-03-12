package curium.api2.security.exception;

import curium.api2.security.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<Response> handleDataNotFoundException(final ResourceNotFoundException e) {

		return new ResponseEntity<>(new Response("error", "Donnée non retrouvée."), HttpStatus.NOT_FOUND); // Retourne 404 pour toute exception de type ResourceNotFoundException
	}

}
