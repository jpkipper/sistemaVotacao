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

	public AgendaDTO( Long id, String description, LocalDateTime dtStart, LocalDateTime dtEnd, AgendaStatusEnum status )
	{
		this.id = id;
		this.description = description;
		this.dtStart = dtStart;
		this.dtEnd = dtEnd;
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public LocalDateTime getDtStart()
	{
		return dtStart;
	}

	public void setDtStart( LocalDateTime dtStart )
	{
		this.dtStart = dtStart;
	}

	public LocalDateTime getDtEnd()
	{
		return dtEnd;
	}

	public void setDtEnd( LocalDateTime dtEnd )
	{
		this.dtEnd = dtEnd;
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
		return "AgendaDTO [id=" + id + ", description=" + description + ", dtStart=" + dtStart + ", dtEnd=" + dtEnd
				+ ", status=" + status + "]";
	}	
}
