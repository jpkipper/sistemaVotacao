package br.com.db.sistema.votacao.v1.dto;

import java.time.LocalDateTime;
import java.util.List;

public class AssemblyDTO
{
    private Long id;
	private LocalDateTime dtStart;
	private LocalDateTime dtEnd;
	List<AgendaDTO> agendas;
}
