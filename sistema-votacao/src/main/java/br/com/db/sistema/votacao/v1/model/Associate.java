package br.com.db.sistema.votacao.v1.model;

import org.hibernate.validator.constraints.br.CPF;

import br.com.db.sistema.votacao.v1.enums.AssociateStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "tb_associate" )
public class Associate
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( nullable = false )
    private String name;

    @Column( length = 14 )
    @CPF( message = "Insira um CPF válido!" )
    private String cpf;
    
    @Enumerated( EnumType.STRING )
    private AssociateStatusEnum associateStatusEnum;

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

    public AssociateStatusEnum getStatus() 
    {
        return associateStatusEnum;
    }

    public void setStatus( AssociateStatusEnum associateStatusEnum ) 
    {
        this.associateStatusEnum = associateStatusEnum;
    }
}
