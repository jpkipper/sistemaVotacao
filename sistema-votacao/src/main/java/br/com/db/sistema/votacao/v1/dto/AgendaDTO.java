package br.com.db.sistema.votacao.v1.dto;

import java.time.LocalDateTime;

import br.com.db.sistema.votacao.v1.enums.AgendaStatusEnum;

public class AgendaDTO
{
    private Long id;
	private String description;
	private LocalDateTime dtStart;
	private LocalDateTime dtEnd;
	private AgendaStatusEnum status;

}
