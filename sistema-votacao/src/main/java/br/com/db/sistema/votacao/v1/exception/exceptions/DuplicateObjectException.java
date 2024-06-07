package br.com.db.sistema.votacao.v1.exception.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateObjectException extends RuntimeException
{
    public DuplicateObjectException( String message )
    {
        super( message );
    }

    public DuplicateObjectException( Throwable cause )
    {
        super( cause );
    }

    public DuplicateObjectException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public HttpStatus getStatus()
    {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}