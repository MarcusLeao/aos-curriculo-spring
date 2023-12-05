package unicap.example.curriculo.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import unicap.example.curriculo.domain.dto.ResponseDTO;
import unicap.example.curriculo.exception.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDTO> handleNotFound(NotFoundException notFoundException){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(HttpStatus.NOT_FOUND.value(), notFoundException.getMessage()));
    }
    
}
