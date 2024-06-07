package br.com.db.sistema.votacao.v1.exception.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException
{
    public BadRequestException( String message )
    {
        super( message );
    }

    public BadRequestException( Throwable cause )
    {
        super( cause );
    }

    public BadRequestException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public HttpStatus getStatus()
    {
        return HttpStatus.BAD_REQUEST;
    }
}
