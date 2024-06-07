package br.com.db.sistema.votacao.v1.exception.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.db.sistema.votacao.v1.exception.exceptions.BadRequestException;

@ControllerAdvice
public class BadRequestHandler implements Handler<BadRequestException>
{
    @Override
    @ExceptionHandler( BadRequestException.class )
    public ResponseEntity<Error> handle( BadRequestException e ) 
    {
        return response( e.getMessage(), e.getStatus() );
    }
}
