package br.com.db.sistema.votacao.v1.exception.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.db.sistema.votacao.v1.exception.exceptions.DuplicateObjectException;

@ControllerAdvice
public class DuplicateObjectHandler implements Handler<DuplicateObjectException>
{
    @Override
    @ExceptionHandler( DuplicateObjectException.class )
    public ResponseEntity<Error> handle( DuplicateObjectException e ) 
    {
        return response( e.getMessage(), e.getStatus() );
    }
}