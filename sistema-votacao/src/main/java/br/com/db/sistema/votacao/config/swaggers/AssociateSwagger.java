package br.com.db.sistema.votacao.config.swaggers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.db.sistema.votacao.v1.model.dto.AssociateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

public interface AssociateSwagger
{
    @Operation(operationId = "Criar um novo associado",
			summary = "Cria um novo associado com os dados fornecidos",
			tags = {"associate"},
			parameters = {@Parameter()},
			responses = {
					@ApiResponse(responseCode = "200",
							description = "Operação bem Sucedida",
							content = @Content(schema = @Schema(implementation = AssociateDTO.class))),
					@ApiResponse(responseCode = "400", description = "Erro ao criar o associado")
    })
    @PostMapping
    ResponseEntity<String> createAssociate(@Valid @RequestBody AssociateDTO associateDTO);

    @Operation(operationId = "Buscar associado por CPF",
            summary = "Retorna o associado correspondente ao CPF fornecido",
            tags = {"associate"},
            parameters = {@Parameter()},
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Operação bem Sucedida",
                            content = @Content(schema = @Schema(implementation = AssociateDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Associado não encontrado")
    })
    @GetMapping("/{cpf}")
    ResponseEntity<AssociateDTO> getAssociateByCpf(@PathVariable String cpf);
}
