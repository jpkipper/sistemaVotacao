package br.com.db.sistema.votacao.v1.model.dto;

import com.google.gson.Gson;

import br.com.db.sistema.votacao.v1.model.enums.AssociateStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AssociateDTO
{
    private Long id;
    private String name;
    private String cpf;
    private AssociateStatusEnum status;

    @Override
    public String toString() 
    {
        // return "AssociateDTO [id=" + id + ", name=" + name + ", cpf=" + cpf + ", status=" + status + "]";
        Gson gson = new Gson();
        return gson.toJson( this );
    }
}
