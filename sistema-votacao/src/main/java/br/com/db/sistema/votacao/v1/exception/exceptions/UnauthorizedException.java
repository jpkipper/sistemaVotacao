package br.com.db.sistema.votacao.v1.exception.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends RuntimeException
{
    public UnauthorizedException( String message )
    {
        super( message );
    }

    public UnauthorizedException( Throwable cause )
    {
        super( cause );
    }

    public UnauthorizedException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public HttpStatus getStatus()
    {
        return HttpStatus.UNAUTHORIZED;
    }
}
