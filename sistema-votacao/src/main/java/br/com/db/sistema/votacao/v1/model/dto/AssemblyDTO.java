package br.com.db.sistema.votacao.v1.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssemblyDTO
{
    private Long id;
	private LocalDateTime start;
	private LocalDateTime end;
	List<AgendaDTO> agendas;
}
