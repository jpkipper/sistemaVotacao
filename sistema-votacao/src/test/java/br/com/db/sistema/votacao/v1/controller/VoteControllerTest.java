package br.com.db.sistema.votacao.v1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.db.sistema.votacao.v1.model.dto.VoteDTO;
import br.com.db.sistema.votacao.v1.service.VoteService;

public class VoteControllerTest
{
    @Mock
    private VoteService voteService;

    @InjectMocks
    private VoteController voteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("[POST] Deve retornar status 200 e mensagem de sucesso ao salvar um voto")
    void saveVote_Success()
    {
        VoteDTO voteDTO = new VoteDTO();
        doNothing().when(voteService).saveVote(voteDTO);

        ResponseEntity<String> response = voteController.saveVote(voteDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Vote saved successfully", response.getBody());
    }

    @Test
    @DisplayName("[POST] Deve retornar status 400 e mensagem de erro ao ocorrer uma exceção ao salvar um voto")
    void saveVote_Exception()
    {
        VoteDTO voteDTO = new VoteDTO(); // Adicione dados de teste conforme necessário
        String errorMessage = "Erro ao salvar o voto";
        doThrow(new RuntimeException(errorMessage)).when(voteService).saveVote(voteDTO);

        ResponseEntity<String> response = voteController.saveVote(voteDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains(errorMessage));
    }
}