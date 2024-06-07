package br.com.db.sistema.votacao.v1.model.dto;

import java.time.LocalDateTime;

import br.com.db.sistema.votacao.v1.model.enums.AgendaStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AgendaDTO
{
    private Long id;
	private Long assemblyId;
	private String description;
	private LocalDateTime start;
	private LocalDateTime end;
	private AgendaStatusEnum status;
}
