package br.com.db.sistema.votacao.v1.model.dto;

import com.google.gson.Gson;

import br.com.db.sistema.votacao.v1.model.enums.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VoteDTO
{
    private Long id;
	private AgendaDTO agenda;
	private AssociateDTO associate;
	private VoteEnum status;

	@Override
	public String toString()
	{
		// return "VoteDTO [id=" + id + ", agenda=" + agenda + ", associate=" + associate + ", status=" + status + "]";
		Gson gson = new Gson();
        return gson.toJson( this );
	}
}
