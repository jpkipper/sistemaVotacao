package br.com.db.sistema.votacao.v1.dto;

import br.com.db.sistema.votacao.v1.enums.VoteEnum;

public class VoteDTO
{
    private Long id;
	private AgendaDTO agenda;
	private AssociateDTO associate;
	private VoteEnum status;

}
