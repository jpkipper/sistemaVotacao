package br.com.db.sistema.votacao.v1.model.entity;

import br.com.db.sistema.votacao.v1.model.enums.VoteEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "tb_vote" )
public class Vote
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @OneToOne
    @JoinColumn( name = "agenda_id", nullable = false )
    private Agenda agenda;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "associate_id", nullable = false )
    private Associate associate;

    @Column( nullable = false )
    @Enumerated( EnumType.STRING )
    private VoteEnum value;

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

    public VoteEnum getValue() 
    {
        return value;
    }

    public void setValue( VoteEnum value ) 
    {
        this.value = value;
    }
}
