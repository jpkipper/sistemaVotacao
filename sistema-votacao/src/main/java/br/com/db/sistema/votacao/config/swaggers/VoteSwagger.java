package br.com.db.sistema.votacao.config.swaggers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.db.sistema.votacao.v1.model.dto.VoteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

public interface VoteSwagger
{
    @Operation(
		operationId = "Salvar um voto",
		summary = "Salva um novo voto com os dados fornecidos",
		tags = {"Vote"},
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Operação bem Sucedida",
				content = @Content(
					schema = @Schema(implementation = VoteDTO.class),
					mediaType = MediaType.APPLICATION_JSON_VALUE 
				)
			),
			@ApiResponse(
				responseCode = "400", 
				description = "Erro ao salvar voto",
				content = @Content(
					schema = @Schema( implementation = Error.class ),
					mediaType = MediaType.APPLICATION_JSON_VALUE 
				)
			)
    	}
	)
    @PostMapping
    ResponseEntity<String> saveVote(@Valid @RequestBody VoteDTO voteDTO);
}
