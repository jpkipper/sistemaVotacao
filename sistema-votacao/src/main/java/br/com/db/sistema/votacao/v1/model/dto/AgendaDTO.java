package br.com.db.sistema.votacao.v1.model.dto;

import java.time.LocalDateTime;

import br.com.db.sistema.votacao.v1.model.enums.AgendaStatusEnum;

public class AgendaDTO
{
    private Long id;
	private Long assemblyId;
	private String description;
	private LocalDateTime start;
	private LocalDateTime end;
	private AgendaStatusEnum status;

	public AgendaDTO( Long id, Long assemblyId, String description, LocalDateTime start, LocalDateTime end, AgendaStatusEnum status )
	{
		this.id = id;
		this.assemblyId = assemblyId;
		this.description = description;
		this.start = start;
		this.end = end;
		this.status = status;
	}

	public AgendaDTO()
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

	public Long getAssemblyId()
	{
		return assemblyId;
	}

	public void setAssemblyId( Long assemblyId )
	{
		this.assemblyId = assemblyId;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
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

	public AgendaStatusEnum getStatus()
	{
		return status;
	}

	public void setStatus( AgendaStatusEnum status )
	{
		this.status = status;
	}

	@Override
	public String toString()
	{
		return "AgendaDTO [id=" + id + ", description=" + description + ", DT_Start=" + start + ", DT_End=" + end
				+ ", status=" + status + "]";
	}	
}
