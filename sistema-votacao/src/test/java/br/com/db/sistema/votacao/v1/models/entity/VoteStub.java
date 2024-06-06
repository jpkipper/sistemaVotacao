package br.com.db.sistema.votacao.v1.models.entity;

import br.com.db.sistema.votacao.v1.model.dto.VoteDTO;
import br.com.db.sistema.votacao.v1.model.entity.Vote;
import br.com.db.sistema.votacao.v1.model.enums.VoteEnum;

public class VoteStub
{
    public static VoteDTO createRegisterVoteYes()
    {
        VoteDTO voteDTO = VoteDTO.builder()
                                .associate( AssociateStub.createAssociateDTOWithId() )
                                .agenda( AgendaStub.createAgendaDTOWithoutId() )
                                .status( VoteEnum.SIM )
                                .build();
        return voteDTO;
    }

    public static VoteDTO createRegisterVoteNo()
    {
        VoteDTO voteDTO = VoteDTO.builder()
                                .associate( AssociateStub.createAssociateDTOWithId() )
                                .agenda( AgendaStub.createAgendaDTOWithoutId() )
                                .status( VoteEnum.NÃO )
                                .build();
        return voteDTO;
    }

    public static Vote createVoteYesWithId()
    {
        Vote vote = Vote.builder()
                        .id( 1L )
                        .associate( AssociateStub.createAssociateWithId() )
                        .agenda( AgendaStub.createAgendaWithId() )
                        .value( VoteEnum.SIM ).build();

        return vote;
    }

    public static Vote createVoteNoWithId()
    {
        Vote vote = Vote.builder()
                        .id( 1L )
                        .associate( AssociateStub.createAssociateWithId() )
                        .agenda( AgendaStub.createAgendaWithId() )
                        .value( VoteEnum.NÃO ).build();

        return vote;
    }
}
