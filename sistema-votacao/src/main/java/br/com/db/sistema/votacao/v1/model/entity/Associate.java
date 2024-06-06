package br.com.db.sistema.votacao.v1.model.entity;

import org.hibernate.validator.constraints.br.CPF;

import br.com.db.sistema.votacao.v1.model.dto.AssociateDTO;
import br.com.db.sistema.votacao.v1.model.enums.AssociateStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "tb_associate" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public Associate( AssociateDTO associateDTO )
    {
        this.id = associateDTO.getId();
        this.name = associateDTO.getName();
        this.cpf = associateDTO.getCpf();
        this.associateStatusEnum = associateDTO.getStatus();
    }
}
