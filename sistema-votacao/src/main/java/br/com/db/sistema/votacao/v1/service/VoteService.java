package br.com.db.sistema.votacao.v1.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.db.sistema.votacao.v1.model.dto.VoteDTO;
import br.com.db.sistema.votacao.v1.model.entity.Agenda;
import br.com.db.sistema.votacao.v1.model.entity.Associate;
import br.com.db.sistema.votacao.v1.model.entity.Vote;
import br.com.db.sistema.votacao.v1.model.enums.AssociateStatusEnum;
import br.com.db.sistema.votacao.v1.repository.VoteRepository;
import br.com.db.sistema.votacao.validator.CpfValidator;

@Service
public class VoteService
{
    private final VoteRepository voteRepository;
    private final AgendaService agendaService;
    private final AssociateService associateService;
    public CpfValidator cpfValidator;

    public VoteService( VoteRepository voteRepository, AgendaService agendaService, AssociateService associateService )
    {
        this.voteRepository = voteRepository;
        this.agendaService = agendaService;
        this.associateService = associateService;
    }

    public void saveVote( VoteDTO voteDTO ) throws Exception
    {
        Associate associate = validateAssociate( voteDTO.getAssociate().getCpf() );
        Agenda agenda = validateAgenda( voteDTO.getAgenda().getId() );

        hasVoted( associate.getId(), agenda.getId() );

        Vote vote = new Vote();
        vote.setValue( voteDTO.getStatus() );
        vote.setAgenda( agenda );
        vote.setAssociate( associate );

        voteRepository.save( vote );
        agenda.getVotes().add( vote );
        agendaService.save( agenda );
    }

    private Associate validateAssociate( String cpf ) throws Exception
    {
        cpfValidator.isCPFValid( cpf );
        Associate associate = associateService.findAssociate(cpf);

        if( associate.getStatus().getValue().equals( AssociateStatusEnum.ABLE_TO_VOTE ) )
            return associate;

        throw new Exception( "Associate " + associate.getName() + " with CPF = " + cpf + " cannot vote!" );
    }

    public boolean hasVoted( Long associateId, Long agendaId )
    {
        return voteRepository.findAll().stream()
            .anyMatch( voto -> voto.getAssociate().getId().equals( associateId ) && voto.getAgenda().getId().equals( agendaId ) );
    }
    
    private Agenda validateAgenda( Long id ) throws Exception
    {
        Agenda agenda = agendaService.findById( id );
        
        if( agenda.getEnd().isAfter( LocalDateTime.now() ) )
            return agenda;
        
        throw new Exception( "Agenda with ID: " + id + " - has already expired!" );
    }
}
