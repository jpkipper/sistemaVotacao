package br.com.db.sistema.votacao.v1.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.db.sistema.votacao.v1.model.dto.AssemblyDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "tb_assembly" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assembly 
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private LocalDateTime start;
    private LocalDateTime end;

    @OneToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "assembly_agenda", joinColumns = {
            @JoinColumn( name = "assembly_id", referencedColumnName = "id" ) }, inverseJoinColumns = {
            @JoinColumn( name = "agenda_id", referencedColumnName = "id" ) } )
    private List<Agenda> agendas = new ArrayList<>();

    public Assembly( AssemblyDTO assemblyDTO, List<Agenda> agendas )
    {
        this.id = assemblyDTO.getId();
        this.start = assemblyDTO.getStart();
        this.end = assemblyDTO.getEnd();
        this.agendas = agendas;
    }
}
