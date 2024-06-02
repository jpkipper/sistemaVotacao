package br.com.db.sistema.votacao.v1.dto;

import java.time.LocalDateTime;
import java.util.List;

public class AssemblyDTO
{
    private Long id;
	private LocalDateTime dtStart;
	private LocalDateTime dtEnd;
	List<AgendaDTO> agendas;

	public AssemblyDTO( Long id, LocalDateTime dtStart, LocalDateTime dtEnd, List<AgendaDTO> agendas ) 
	{
		this.id = id;
		this.dtStart = dtStart;
		this.dtEnd = dtEnd;
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
		return "AssemblyDTO [id=" + id + ", dtStart=" + dtStart + ", dtEnd=" + dtEnd + ", agendas=" + agendas + "]";
	}
}
