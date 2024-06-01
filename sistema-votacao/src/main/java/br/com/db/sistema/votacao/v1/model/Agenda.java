package br.com.db.sistema.votacao.v1.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "agenda" )
public class Agenda 
{
    @Id
    private Long id;
    private String description;
    private List<Vote> votes;
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

    public String getDescription() 
    {
        return description;
    }

    public void setDescription( String description ) 
    {
        this.description = description;
    }

    public List<Vote> getVotes() 
    {
        return votes;
    }

    public void setVotes( List<Vote> votes ) 
    {
        this.votes = votes;
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
