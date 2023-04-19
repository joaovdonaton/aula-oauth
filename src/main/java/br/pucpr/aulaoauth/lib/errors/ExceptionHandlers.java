package br.pucpr.aulaoauth.lib.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ExceptionHandlers {
    // para imprimir o erro em um formato mais visível
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    private ResponseEntity<String> badRequest(HttpClientErrorException.BadRequest e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
