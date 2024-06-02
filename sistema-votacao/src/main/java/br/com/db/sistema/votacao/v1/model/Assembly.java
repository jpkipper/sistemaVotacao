package br.com.db.sistema.votacao.v1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "tb_assembly" )
public class Assembly 
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private List<Agenda> agendas = new ArrayList<>();

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

    public List<Agenda> getAgendas()
    {
        return agendas;
    }

    public void setAgendas( List<Agenda> agendas )
    {
        this.agendas = agendas;
    }
}
