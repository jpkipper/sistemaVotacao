package br.com.db.sistema.votacao.v1.models.entity;

import br.com.db.sistema.votacao.v1.model.dto.AssociateDTO;
import br.com.db.sistema.votacao.v1.model.entity.Associate;
import br.com.db.sistema.votacao.v1.model.enums.AssociateStatusEnum;

public class AssociateStub
{
    public static Associate createAssociateWithId()
    {
        Associate associate = Associate.builder()
                                        .id( 1L )
                                        .name("João Pedro Kipper")
                                        .cpf("01964652065")
                                        .associateStatusEnum( AssociateStatusEnum.ABLE_TO_VOTE )
                                        .build();

        return associate;
    }

    public static Associate createAssociateWithoutId()
    {
        Associate associate = Associate.builder()
                                        .name("João Pedro Kipper")
                                        .cpf("01964652065")
                                        .build();
        return associate;
    }

    public static AssociateDTO createAssociateDTOWithId()
    {
        AssociateDTO associateDTO = AssociateDTO.builder()
                                        .id( 1L )
                                        .name("João Pedro Kipper")
                                        .cpf("01964652065")
                                        .status( AssociateStatusEnum.ABLE_TO_VOTE )
                                        .build();

        return associateDTO;
    }

    public static AssociateDTO createAssociateDTOWithoutId()
    {
        AssociateDTO associateDTO = AssociateDTO.builder()
                                        .name("João Pedro Kipper")
                                        .cpf("01964652065")
                                        .status( AssociateStatusEnum.UNABLE_TO_VOTE )
                                        .build();
        return associateDTO;
    }
}
