package br.com.db.sistema.votacao.v1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.db.sistema.votacao.v1.exception.exceptions.BadRequestException;
import br.com.db.sistema.votacao.v1.model.dto.AgendaDTO;
import br.com.db.sistema.votacao.v1.model.dto.AssociateDTO;
import br.com.db.sistema.votacao.v1.model.dto.VoteDTO;
import br.com.db.sistema.votacao.v1.model.entity.Agenda;
import br.com.db.sistema.votacao.v1.model.entity.Associate;
import br.com.db.sistema.votacao.v1.model.entity.Vote;
import br.com.db.sistema.votacao.v1.model.enums.AssociateStatusEnum;
import br.com.db.sistema.votacao.v1.model.enums.VoteEnum;
import br.com.db.sistema.votacao.v1.models.entity.AgendaStub;
import br.com.db.sistema.votacao.v1.repository.VoteRepository;
import br.com.db.sistema.votacao.validator.CpfValidator;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest
{
    @InjectMocks
    private VoteService voteService;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private AgendaService agendaService;

    @Mock
    private AssociateService associateService;

    @Mock
    private CpfValidator cpfValidator;

    private VoteDTO voteDTO;
    private Vote vote;
    private AssociateDTO associateDTO;
    private Associate associate;
    private Agenda agenda;
    private AgendaDTO agendaDTO;

    @BeforeEach
    void setUp() throws Exception
    {
        associateDTO = new AssociateDTO();
        associateDTO.setId(1L);
        associateDTO.setCpf( "01964652065" );
        associateDTO.setName( "João Pedro Kipper" );
        associateDTO.setStatus( AssociateStatusEnum.ABLE_TO_VOTE );

        associate = new Associate( associateDTO );

        agenda = new Agenda();
        agenda.setId(1L);
        agenda.setEnd( LocalDateTime.now().plusDays(1) );

        agendaDTO = AgendaStub.createAgendaDTOWithtId();

        voteDTO = new VoteDTO();
        voteDTO.setAssociate( associateDTO );
        voteDTO.setAgenda( agendaDTO );
        voteDTO.setStatus( VoteEnum.SIM );

        vote = new Vote( voteDTO, associate, agenda );
    }

    @Test
    void shouldSaveVoteSuccessfully() throws Exception
    {
        when(associateService.findAssociate("01964652065")).thenReturn(associate);
        when(agendaService.findById(1L)).thenReturn(agenda);
        when(voteRepository.existsByAssociateIdAndAgendaId(1L, 1L)).thenReturn(false);

        voteService.saveVote(voteDTO);

        verify(voteRepository, times(1)).save(any(Vote.class));
    }

    @Test
    void shouldSaveVote_AssociateNotFound() throws Exception
    {
        VoteDTO voteDTO = new VoteDTO();

        when(associateService.findAssociate(anyString())).thenReturn( associate );

        assertThrows(BadRequestException.class, () -> voteService.saveVote(voteDTO));
    }

    @Test
    void shouldSaveVote_AssociateCannotVote() throws Exception
    {
        VoteDTO voteDTO = new VoteDTO();
        Associate associate = new Associate();
        associate.setAssociateStatusEnum(AssociateStatusEnum.UNABLE_TO_VOTE);

        when(associateService.findAssociate(anyString())).thenReturn(associate);

        assertThrows(BadRequestException.class, () -> voteService.saveVote(voteDTO));
    }

    @Test
    void shouldSaveVote_AgendaExpired() throws Exception
    {
        VoteDTO voteDTO = new VoteDTO();
        Associate associate = new Associate();
        associate.setAssociateStatusEnum(AssociateStatusEnum.ABLE_TO_VOTE);
        Agenda agenda = new Agenda();
        agenda.setEnd(LocalDateTime.now().minusDays(1));

        when(associateService.findAssociate(anyString())).thenReturn(associate);
        when(agendaService.findById(anyLong())).thenReturn(agenda);

        assertThrows(BadRequestException.class, () -> voteService.saveVote(voteDTO));
    }

    @Test
    void shouldSaveVote_HasVoted() throws Exception
    {
        VoteDTO voteDTO = new VoteDTO();
        Associate associate = new Associate();
        associate.setId(1L);
        associate.setAssociateStatusEnum(AssociateStatusEnum.ABLE_TO_VOTE);
        Agenda agenda = new Agenda();
        agenda.setId(1L);
        agenda.setEnd(LocalDateTime.now().plusDays(1));

        when(associateService.findAssociate(anyString())).thenReturn(associate);
        when(agendaService.findById(anyLong())).thenReturn(agenda);
        when(voteService.hasVoted(anyLong(), anyLong())).thenReturn(true);

        assertThrows(BadRequestException.class, () -> voteService.saveVote(voteDTO));
    }

    @Test
    void shouldHasVoted_VoteExists_ReturnsTrue() 
    {
        // Arrange
        Long associateId = 1L;
        Long agendaId = 1L;
        when(voteRepository.existsByAssociateIdAndAgendaId(associateId, agendaId)).thenReturn(true);

        // Act
        boolean hasVoted = voteService.hasVoted(associateId, agendaId);

        // Assert
        assertTrue(hasVoted);
        verify(voteRepository, times(1)).existsByAssociateIdAndAgendaId(associateId, agendaId);
    }

    @Test
    void shouldHasVoted_VoteDoesNotExist_ReturnsFalse()
    {
        // Arrange
        Long associateId = 1L;
        Long agendaId = 1L;
        when(voteRepository.existsByAssociateIdAndAgendaId(associateId, agendaId)).thenReturn(false);

        // Act
        boolean hasVoted = voteService.hasVoted(associateId, agendaId);

        // Assert
        assertFalse(hasVoted);
        verify(voteRepository, times(1)).existsByAssociateIdAndAgendaId(associateId, agendaId);
    }
}
