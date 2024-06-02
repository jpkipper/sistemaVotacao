package br.com.db.sistema.votacao.v1.dto;

import br.com.db.sistema.votacao.v1.enums.AssociateStatusEnum;

public class AssociateDTO
{
    private Long id;
    private String name;
    private String cpf;
    private AssociateStatusEnum status;
}
