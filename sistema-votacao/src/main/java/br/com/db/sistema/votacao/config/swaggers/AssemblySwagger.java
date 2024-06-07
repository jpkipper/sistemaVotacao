package br.com.db.sistema.votacao.config.swaggers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.db.sistema.votacao.v1.model.dto.AssemblyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

public interface AssemblySwagger
{
    @Operation(operationId = "Criar uma nova assembleia",
			summary = "Cria uma nova assembleia com os dados fornecidos",
			tags = {"assembly"},
			parameters = {@Parameter()},
			responses = {
					@ApiResponse(responseCode = "200",
							description = "Operação bem Sucedida",
							content = @Content(schema = @Schema(implementation = AssemblyDTO.class))),
					@ApiResponse(responseCode = "400", description = "Erro ao criar a assembleia")
    })
    @PostMapping
    ResponseEntity<String> createAssembly(@Valid @RequestBody AssemblyDTO assemblyDTO);

    @Operation(operationId = "Buscar todas as assembleias",
			summary = "Retorna todas as assembleias",
			tags = {"assembly"},
			parameters = {@Parameter()},
			responses = {
					@ApiResponse(responseCode = "200",
							description = "Operação bem Sucedida",
							content = @Content(schema = @Schema(implementation = List.class)))
    })
    @GetMapping
    ResponseEntity<List<AssemblyDTO>> getAllAssemblies();
}

