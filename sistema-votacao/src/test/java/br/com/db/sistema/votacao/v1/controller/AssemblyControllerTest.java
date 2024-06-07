package br.com.db.sistema.votacao.v1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.db.sistema.votacao.v1.model.dto.AssemblyDTO;
import br.com.db.sistema.votacao.v1.service.AssemblyService;

public class AssemblyControllerTest
{
    @Mock
    private AssemblyService assemblyService;

    @InjectMocks
    private AssemblyController assemblyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("[POST] Deve retornar status 200 e mensagem de sucesso ao criar uma assembleia")
    void createAssembly_Success()
	{        
        AssemblyDTO assemblyDTO = new AssemblyDTO();
        doNothing().when(assemblyService).createAssembly(assemblyDTO);
        
        ResponseEntity<String> response = assemblyController.createAssembly(assemblyDTO);
       
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Assembly created successfully", response.getBody());
    }

    @Test
    @DisplayName("[POST] Deve retornar status 400 e mensagem de erro ao ocorrer uma exceção ao criar uma assembleia")
    void createAssembly_Exception()
	{
        AssemblyDTO assemblyDTO = new AssemblyDTO();
        String errorMessage = "Erro ao criar a assembleia";
        doThrow(new RuntimeException(errorMessage)).when(assemblyService).createAssembly(assemblyDTO);

        ResponseEntity<String> response = assemblyController.createAssembly(assemblyDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains(errorMessage));
    }

    @Test
    @DisplayName("[GET] Deve retornar status 200 e a lista de todas as assembleias")
    void getAllAssemblies_Success()
	{
        List<AssemblyDTO> assemblies = new ArrayList<>();
        when(assemblyService.findAll()).thenReturn(assemblies);

        ResponseEntity<List<AssemblyDTO>> response = assemblyController.getAllAssemblies();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assemblies, response.getBody());
    }
}