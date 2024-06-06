package br.com.db.sistema.votacao.v1.model.entity;

import br.com.db.sistema.votacao.v1.model.dto.VoteDTO;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "tb_vote" )
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Vote( VoteDTO voteDTO, Associate associate, Agenda agenda )
    {
        this.id = voteDTO.getId();
        this.agenda = agenda;
        this.associate = associate;
        this.value = voteDTO.getStatus();
    }
}
