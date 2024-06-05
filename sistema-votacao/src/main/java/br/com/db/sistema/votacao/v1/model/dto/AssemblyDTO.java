package br.com.db.sistema.votacao.v1.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class AssemblyDTO
{
    private Long id;
	private LocalDateTime start;
	private LocalDateTime end;
	List<AgendaDTO> agendas;

	public AssemblyDTO( Long id, LocalDateTime start, LocalDateTime end, List<AgendaDTO> agendas ) 
	{
		this.id = id;
		this.start = start;
		this.end = end;
		this.agendas = agendas;
	}

	public AssemblyDTO()
	{
	}

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public LocalDateTime getStart()
	{
		return start;
	}

	public void setStart( LocalDateTime start )
	{
		this.start = start;
	}

	public LocalDateTime getEnd()
	{
		return end;
	}

	public void setEnd( LocalDateTime end )
	{
		this.end = end;
	}

	public List<AgendaDTO> getAgendas()
	{
		return agendas;
	}

	public void setAgendas( List<AgendaDTO> agendas )
	{
		this.agendas = agendas;
	}

	@Override
	public String toString()
	{
		return "AssemblyDTO [id=" + id + ", start=" + start + ", end=" + end + ", agendas=" + agendas + "]";
	}
}
