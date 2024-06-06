package br.com.db.sistema.votacao.v1.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import br.com.db.sistema.votacao.v1.model.dto.AgendaDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "tb_agenda" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agenda 
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( nullable = false )
    private String description;

    @OneToMany( cascade = CascadeType.ALL )
	@JoinTable( name = "agenda_votation", joinColumns = {
			@JoinColumn( name = "agenda_id", referencedColumnName = "id" )}, inverseJoinColumns = {
			@JoinColumn( name = "votes_id", referencedColumnName = "id" ) } )
    private List<Vote> votes;
    private LocalDateTime start;
    private LocalDateTime end;

    public Agenda( AgendaDTO agendaDTO, List<Vote> votes )
    {
        this.id = agendaDTO.getId();
        this.description = agendaDTO.getDescription();
        this.start = agendaDTO.getStart();
        this.end = agendaDTO.getEnd();
        this.votes = votes;
    }
}
