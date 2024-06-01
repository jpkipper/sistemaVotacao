package br.com.db.sistema.votacao.v1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "associate" )
public class Associate
{
    @Id
    private Long id;
    private String name;
    private String cpf;
    private String status;

    public Long getId() 
    {
        return id;
    }

    public void setId( Long id ) 
    {
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName( String name ) 
    {
        this.name = name;
    }

    public String getCpf() 
    {
        return cpf;
    }

    public void setCpf( String cpf ) 
    {
        this.cpf = cpf;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setStatus( String status ) 
    {
        this.status = status;
    }
}
