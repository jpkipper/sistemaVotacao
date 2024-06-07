package br.com.db.sistema.votacao.v1.model.dto;

import br.com.db.sistema.votacao.v1.model.enums.AssociateStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssociateDTO
{
    private Long id;
    private String name;
    private String cpf;
    private AssociateStatusEnum status;
}
