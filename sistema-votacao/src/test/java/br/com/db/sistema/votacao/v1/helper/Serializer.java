package br.com.db.sistema.votacao.v1.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.db.sistema.votacao.v1.exception.exceptions.BadRequestException;

public class Serializer 
{
    public static <T> String json( final T objectToConvert )
    {
        try 
        {
            ObjectMapper objectMapper = new ObjectMapper();
            
            objectMapper.registerModule( new JavaTimeModule() );

            return objectMapper.writeValueAsString( objectToConvert );
        } 
        catch( JsonProcessingException e )
        {
            throw new BadRequestException( e.getMessage() );
        }
    }
}