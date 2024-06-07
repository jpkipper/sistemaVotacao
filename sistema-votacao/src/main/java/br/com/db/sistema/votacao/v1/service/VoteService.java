package br.com.db.sistema.votacao.v1.service;

import static java.time.LocalDateTime.now;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.db.sistema.votacao.v1.exception.exceptions.BadRequestException;
import br.com.db.sistema.votacao.v1.exception.exceptions.NotFoundException;
import br.com.db.sistema.votacao.v1.model.dto.VoteDTO;
import br.com.db.sistema.votacao.v1.model.entity.Agenda;
import br.com.db.sistema.votacao.v1.model.entity.Associate;
import br.com.db.sistema.votacao.v1.model.entity.Vote;
import br.com.db.sistema.votacao.v1.model.enums.AssociateStatusEnum;
import br.com.db.sistema.votacao.v1.repository.VoteRepository;
import br.com.db.sistema.votacao.validator.CpfValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoteService
{
    private final VoteRepository voteRepository;
    private final AgendaService agendaService;
    private final AssociateService associateService;
    private final CpfValidator cpfValidator;

    public void saveVote(VoteDTO voteDTO) throws Exception
    {
            String cpf = voteDTO.getAssociate().getCpf();
            Associate associate = validateAssociate(cpf);
            Agenda agenda = validateAgenda( voteDTO.getAgenda().getId() );

            if( hasVoted(associate.getId(), agenda.getId() ) )
            {
                throw new BadRequestException("Associate has already voted on this agenda");
            }

            Vote vote = new Vote(voteDTO, associate, agenda);

            agenda.addVoto( vote );
            voteRepository.save(vote);
            agendaService.save(agenda);
    }

    private Associate validateAssociate(String cpf) throws Exception
    {
        cpfValidator.isCPFValid(cpf);
        Associate associate = associateService.findAssociate(cpf);

        if(associate == null)
        {
            throw new NotFoundException("Associate with CPF = " + cpf + " not found");
        }

        if (!associate.getAssociateStatusEnum().equals(AssociateStatusEnum.ABLE_TO_VOTE))
        {
            throw new BadRequestException("Associate " + associate.getName() + " with CPF = " + cpf + " cannot vote!");
        }

        return associate;
    }

    public boolean hasVoted(Long associateId, Long agendaId)
    {
        return voteRepository.existsByAssociateIdAndAgendaId( associateId, agendaId );
    }

    private Agenda validateAgenda(Long id) throws Exception
    {
        Agenda agenda = agendaService.findById(id);

        if( agenda.getEnd().isBefore( now() )) 
        {
            throw new BadRequestException("Agenda with ID: " + id + " has already expired!");
        }

        return agenda;
    }
}
