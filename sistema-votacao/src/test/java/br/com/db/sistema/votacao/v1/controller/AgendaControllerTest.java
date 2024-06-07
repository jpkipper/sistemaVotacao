package br.com.db.sistema.votacao.v1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.db.sistema.votacao.v1.exception.exceptions.NotFoundException;
import br.com.db.sistema.votacao.v1.model.dto.AgendaDTO;
import br.com.db.sistema.votacao.v1.service.AgendaService;

public class AgendaControllerTest
{
    @Mock
    private AgendaService agendaService;

    @InjectMocks
    private AgendaController agendaController;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("[POST] Deve retornar status 200 e mensagem de sucesso ao criar uma agenda")
    void createAgenda_Success()
	{
        AgendaDTO agendaDTO = new AgendaDTO();
        doNothing().when(agendaService).createAgenda(agendaDTO);

        ResponseEntity<String> response = agendaController.createAgenda(agendaDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Agenda created successfully", response.getBody());
    }

    @Test
    @DisplayName("[POST] Deve retornar status 400 e mensagem de erro ao ocorrer uma exceção na criação de uma agenda")
    void createAgenda_Exception()
	{
        AgendaDTO agendaDTO = new AgendaDTO();
        doThrow(new RuntimeException("Erro na criação da agenda")).when(agendaService).createAgenda(agendaDTO);

        ResponseEntity<String> response = agendaController.createAgenda(agendaDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro na criação da agenda", response.getBody());
    }

    @Test
    @DisplayName("[GET] Deve retornar status 200 e uma agenda ao buscar pelo ID")
    void getAgendaById_Success()
	{
        AgendaDTO agendaDTO = new AgendaDTO(); 
        when(agendaService.findDTOById(any())).thenReturn(agendaDTO);

        ResponseEntity<AgendaDTO> response = agendaController.getAgendaById(any());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(agendaDTO, response.getBody());
    }

    @Test
    @DisplayName("[GET] Deve retornar status 404 ao buscar por um ID de agenda inexistente")
    void getAgendaById_NotFound()
	{
        doThrow(new NotFoundException("Agenda not found")).when(agendaService).findDTOById(any());

        ResponseEntity<AgendaDTO> response = agendaController.getAgendaById(any());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    @DisplayName("[GET] Deve retornar status 200 e a lista de todas as agendas")
    void getAllAgendas_Success()
	{
        List<AgendaDTO> agendaList = Arrays.asList(new AgendaDTO(), new AgendaDTO());
        when(agendaService.findAll()).thenReturn(agendaList);

        ResponseEntity<List<AgendaDTO>> response = agendaController.getAllAgendas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(agendaList, response.getBody());
    }
}