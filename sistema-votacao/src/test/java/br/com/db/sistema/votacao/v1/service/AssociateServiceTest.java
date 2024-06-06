package br.com.db.sistema.votacao.v1.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.db.sistema.votacao.v1.model.dto.AssociateDTO;
import br.com.db.sistema.votacao.v1.model.entity.Associate;
import br.com.db.sistema.votacao.v1.model.enums.AssociateStatusEnum;
import br.com.db.sistema.votacao.v1.repository.AssociateRepository;
import br.com.db.sistema.votacao.validator.CpfValidator;

@ExtendWith(MockitoExtension.class)
class AssociateServiceTest
{
    @InjectMocks
    private AssociateService associateService;

    @Mock
    private AssociateRepository associateRepository;
    
    @Mock
    private CpfValidator cpfValidator;

    @Test
    void shouldCreateNewAssociateSuccessfully() throws Exception
    {
        doNothing().when(cpfValidator).isCPFValid(any());
        when(associateRepository.save(any())).thenReturn(new Associate());
        when(associateRepository.findByCpf(any())).thenReturn(Optional.empty());

        AssociateDTO associateDTO = new AssociateDTO(1L, "Fulano", "152.152.152-15", AssociateStatusEnum.ABLE_TO_VOTE);

        associateService.createAssociate(associateDTO);

        verify(associateRepository, times(1)).save(any());
    }
}