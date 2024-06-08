package br.com.db.sistema.votacao.config.swaggers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.db.sistema.votacao.v1.model.dto.AssociateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

public interface AssociateSwagger
{
    @Operation(
        operationId = "Criar um novo associado",
        summary = "Cria um novo associado com os dados fornecidos",
        tags = {"Associate"},
        responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Operação bem Sucedida",
				content = @Content(
						schema = @Schema(implementation = AssociateDTO.class),
						mediaType = MediaType.APPLICATION_JSON_VALUE
				)
			),
			@ApiResponse(
				responseCode = "400", 
				description = "Erro ao criar o associado",
				content = @Content(
						schema = @Schema(implementation = Error.class),
						mediaType = MediaType.APPLICATION_JSON_VALUE
				)
			)
        }
    )
    @PostMapping
    ResponseEntity<String> createAssociate( @Valid @RequestBody AssociateDTO associateDTO );

    @Operation(
        operationId = "Buscar associado por CPF",
        summary = "Retorna o associado correspondente ao CPF fornecido",
        tags = {"Associate"},
        responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Operação bem Sucedida",
				content = @Content(
						schema = @Schema(implementation = AssociateDTO.class),
						mediaType = MediaType.APPLICATION_JSON_VALUE
				)
			),
			@ApiResponse(
				responseCode = "404", 
				description = "Associado não encontrado",
				content = @Content(
						schema = @Schema(implementation = Error.class),
						mediaType = MediaType.APPLICATION_JSON_VALUE
				)
			)
        }
    )
    @GetMapping("/{cpf}")
    ResponseEntity<AssociateDTO> getAssociateByCpf( @PathVariable String cpf );
}
