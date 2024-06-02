package br.com.db.sistema.votacao.v1.dto;

import br.com.db.sistema.votacao.v1.enums.VoteEnum;

public class VoteDTO
{
    private Long id;
	private AgendaDTO agenda;
	private AssociateDTO associate;
	private VoteEnum status;

	public VoteDTO( Long id, AgendaDTO agenda, AssociateDTO associate, VoteEnum status )
	{
		this.id = id;
		this.agenda = agenda;
		this.associate = associate;
		this.status = status;
	}

	public VoteDTO()
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

	public AgendaDTO getAgenda()
	{
		return agenda;
	}

	public void setAgenda( AgendaDTO agenda )
	{
		this.agenda = agenda;
	}

	public AssociateDTO getAssociate()
	{
		return associate;
	}

	public void setAssociate( AssociateDTO associate )
	{
		this.associate = associate;
	}

	public VoteEnum getStatus()
	{
		return status;
	}

	public void setStatus( VoteEnum status )
	{
		this.status = status;
	}

	@Override
	public String toString()
	{
		return "VoteDTO [id=" + id + ", agenda=" + agenda + ", associate=" + associate + ", status=" + status + "]";
	}
}
