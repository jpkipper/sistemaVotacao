package br.com.db.sistema.votacao.v1.exception.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.db.sistema.votacao.v1.exception.exceptions.UnauthorizedException;

@ControllerAdvice
public class UnauthorizedHandler implements Handler<UnauthorizedException>
{
    @Override
    @ExceptionHandler( UnauthorizedException.class )
    public ResponseEntity<Error> handle( UnauthorizedException e ) 
    {
        return response( e.getMessage(), e.getStatus() );
    }
}
