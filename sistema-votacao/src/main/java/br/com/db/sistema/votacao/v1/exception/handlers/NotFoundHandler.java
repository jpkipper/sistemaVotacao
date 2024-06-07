package br.com.db.sistema.votacao.v1.exception.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.db.sistema.votacao.v1.exception.exceptions.NotFoundException;


@ControllerAdvice
public class NotFoundHandler implements Handler<NotFoundException>
{
    @Override
    @ExceptionHandler( NotFoundException.class )
    public ResponseEntity<Error> handle( NotFoundException e ) 
    {
        return response( e.getMessage(), e.getStatus() );
    }
}
