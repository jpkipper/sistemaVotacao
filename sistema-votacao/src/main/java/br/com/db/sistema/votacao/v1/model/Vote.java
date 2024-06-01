package br.com.db.sistema.votacao.v1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "vote" )
public class Vote
{
    @Id
    private Long id;
    private Agenda agenda;
    private Associate associate;
    private String value;

    public Long getId() 
    {
        return id;
    }

    public void setId( Long id ) 
    {
        this.id = id;
    }

    public Agenda getAgenda() 
    {
        return agenda;
    }

    public void setAgenda( Agenda agenda ) 
    {
        this.agenda = agenda;
    }

    public Associate getAssociate() 
    {
        return associate;
    }

    public void setAssociate( Associate associate ) 
    {
        this.associate = associate;
    }

    public String getValue() 
    {
        return value;
    }

    public void setValue( String value ) 
    {
        this.value = value;
    }
}
