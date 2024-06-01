package br.com.db.sistema.votacao.v1.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "assembly" )
public class Assembly 
{
    @Id
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;

    public Long getId() 
    {
        return id;
    }

    public void setId( Long id ) 
    {
        this.id = id;
    }

    public LocalDateTime getStart() 
    {
        return start;
    }

    public void setStart( LocalDateTime start ) 
    {
        this.start = start;
    }

    public LocalDateTime getEnd() 
    {
        return end;
    }

    public void setEnd( LocalDateTime end ) 
    {
        this.end = end;
    }
}
