package br.com.db.sistema.votacao.v1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.db.sistema.votacao.v1.exception.exceptions.DuplicateObjectException;
import br.com.db.sistema.votacao.v1.exception.exceptions.NotFoundException;
import br.com.db.sistema.votacao.v1.model.dto.AssociateDTO;
import br.com.db.sistema.votacao.v1.model.entity.Associate;
import br.com.db.sistema.votacao.v1.models.entity.AssociateStub;
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
    void testCreateAssociate_Success()
    {
        AssociateDTO associateDTO = AssociateStub.createAssociateDTOWithId();

        doNothing().when(cpfValidator).isCPFValid(anyString());
        when(associateRepository.existsByCpf(anyString())).thenReturn(false);
        when(associateRepository.save(any(Associate.class))).thenAnswer(i -> i.getArgument(0));

        associateService.createAssociate(associateDTO);

        verify(associateRepository).save(any(Associate.class));
    }

    @Test
    void testCreateAssociate_Duplicate()
    {
        AssociateDTO associateDTO = AssociateStub.createAssociateDTOWithId();

        doNothing().when(cpfValidator).isCPFValid(anyString());
        when(associateRepository.existsByCpf(anyString())).thenReturn(true);

        assertThrows(DuplicateObjectException.class, () -> associateService.createAssociate(associateDTO));
    }

    @Test
    void testFindAssociateDTO_Success()
    {
        Associate associate = AssociateStub.createAssociateWithId();

        when(associateRepository.findByCpf(anyString())).thenReturn(Optional.of(associate));

        AssociateDTO result = associateService.findAssociateDTO("01964652065");

        assertEquals(associate.getId(), result.getId());
        assertEquals(associate.getName(), result.getName());
        assertEquals(associate.getCpf(), result.getCpf());
        assertEquals(associate.getAssociateStatusEnum(), result.getStatus());
    }

    @Test
    void testFindAssociateDTO_NotFound()
    {
        when(associateRepository.findByCpf(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> associateService.findAssociateDTO("01964652065"));
    }
}