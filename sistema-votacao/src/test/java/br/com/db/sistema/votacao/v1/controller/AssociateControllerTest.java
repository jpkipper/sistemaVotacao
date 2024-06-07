package br.com.db.sistema.votacao.v1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.db.sistema.votacao.v1.exception.exceptions.NotFoundException;
import br.com.db.sistema.votacao.v1.model.dto.AssociateDTO;
import br.com.db.sistema.votacao.v1.service.AssociateService;

public class AssociateControllerTest
{
    @Mock
    private AssociateService associateService;

    @InjectMocks
    private AssociateController associateController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("[POST] Deve retornar status 200 e mensagem de sucesso ao criar um associado")
    void createAssociate_Success()
	{
        AssociateDTO associateDTO = new AssociateDTO();
        doNothing().when(associateService).createAssociate(associateDTO);

        ResponseEntity<String> response = associateController.createAssociate(associateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Associate created successfully", response.getBody());
    }

    @Test
    @DisplayName("[POST] Deve retornar status 400 e mensagem de erro ao ocorrer uma exceção ao criar um associado")
    void createAssociate_Exception()
	{
        AssociateDTO associateDTO = new AssociateDTO();
        String errorMessage = "Erro ao criar o associado";
        doThrow(new RuntimeException(errorMessage)).when(associateService).createAssociate(associateDTO);

        ResponseEntity<String> response = associateController.createAssociate(associateDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains(errorMessage));
    }

    @Test
    @DisplayName("[GET] Deve retornar status 200 e o associado correspondente ao CPF fornecido")
    void getAssociateByCpf_Success()
	{
        AssociateDTO associateDTO = new AssociateDTO();
        when(associateService.findAssociateDTO(anyString())).thenReturn(associateDTO);

        ResponseEntity<AssociateDTO> response = associateController.getAssociateByCpf(anyString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(associateDTO, response.getBody());
    }

    @Test
    @DisplayName("[GET] Deve retornar status 404 ao buscar por um associado com CPF não encontrado")
    void getAssociateByCpf_NotFound()
	{
        when(associateService.findAssociateDTO(anyString())).thenThrow(new NotFoundException("Associate not found"));

        ResponseEntity<AssociateDTO> response = associateController.getAssociateByCpf(anyString());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}