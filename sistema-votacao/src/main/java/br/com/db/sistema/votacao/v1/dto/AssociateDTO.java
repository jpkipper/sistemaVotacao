package br.com.db.sistema.votacao.v1.dto;

import br.com.db.sistema.votacao.v1.enums.AssociateStatusEnum;

public class AssociateDTO
{
    private Long id;
    private String name;
    private String cpf;
    private AssociateStatusEnum status;

    public AssociateDTO(Long id, String name, String cpf, AssociateStatusEnum status)
    {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.status = status;
    }

    public AssociateDTO() 
    {
    }

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
        return status;
    }

    public void setStatus( AssociateStatusEnum status )
    {
        this.status = status;
    }

    @Override
    public String toString() 
    {
        return "AssociateDTO [id=" + id + ", name=" + name + ", cpf=" + cpf + ", status=" + status + "]";
    }
}
