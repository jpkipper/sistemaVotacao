package br.com.db.sistema.votacao.v1.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public interface Handler<T extends Exception>
{
    public ResponseEntity<Error> handle( T e );

    public default ResponseEntity<Error> response( String message, HttpStatus status )
    {
        return new ResponseEntity<Error>( new Error( message ), status );
    }
}
