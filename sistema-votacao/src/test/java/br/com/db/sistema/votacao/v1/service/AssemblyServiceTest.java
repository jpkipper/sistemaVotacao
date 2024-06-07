package br.com.db.sistema.votacao.v1.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.db.sistema.votacao.v1.model.dto.AgendaDTO;
import br.com.db.sistema.votacao.v1.model.dto.AssemblyDTO;
import br.com.db.sistema.votacao.v1.model.entity.Agenda;
import br.com.db.sistema.votacao.v1.model.entity.Assembly;
import br.com.db.sistema.votacao.v1.repository.AssemblyRepository;

@ExtendWith(MockitoExtension.class)
public class AssemblyServiceTest
{
    @Mock
    private AssemblyRepository assemblyRepository;

    @InjectMocks
    private AssemblyService assemblyService;

    private AssemblyDTO assemblyDTO;
    private Assembly assembly;
    private Agenda agenda;
    private AgendaDTO agendaDTO;

    @BeforeEach
    void setUp()
    {
        agendaDTO = new AgendaDTO();
        agendaDTO.setId(1L);
        agendaDTO.setDescription("Test Agenda Description");
        agendaDTO.setStart(LocalDateTime.now().plusDays(1));
        agendaDTO.setEnd(LocalDateTime.now().plusDays(2));

        assemblyDTO = new AssemblyDTO();
        assemblyDTO.setId(1L);
        assemblyDTO.setStart(LocalDateTime.now().plusDays(1));
        assemblyDTO.setEnd(LocalDateTime.now().plusDays(5));
        assemblyDTO.setAgendas(List.of(agendaDTO));

        agenda = new Agenda();
        agenda.setId(1L);
        agenda.setDescription("Test Agenda Description");
        agenda.setStart(LocalDateTime.now().plusDays(1));
        agenda.setEnd(LocalDateTime.now().plusDays(2));

        assembly = new Assembly();
        assembly.setId(1L);
        assembly.setStart(LocalDateTime.now());
        assembly.setEnd(LocalDateTime.now().plusDays(5));
        assembly.setAgendas(List.of(agenda));
    }

    @Test
    void testCreateAssembly_Success()
    {
        when(assemblyRepository.save(any(Assembly.class))).thenReturn(assembly);

        assemblyService.createAssembly(assemblyDTO);

        verify(assemblyRepository, times(1)).save(any(Assembly.class));
    }

    @Test
    void testCreateAssembly_InvalidDates()
    {
        assemblyDTO.setStart(LocalDateTime.now().minusDays(1));

        Exception exception = assertThrows(Exception.class, () -> {
            assemblyService.createAssembly(assemblyDTO);
        });

        assertEquals("Data inicio não pode ser superior a data fim e inferior a data atual", exception.getMessage());
    }

    @Test
    void testFindAll()
    {
        when(assemblyRepository.findAll()).thenReturn(List.of(assembly));

        List<AssemblyDTO> assemblies = assemblyService.findAll();

        assertEquals(1, assemblies.size());
        assertEquals(assembly.getId(), assemblies.get(0).getId());
    }

    @Test
    void testFindById_Success()
    {
        when(assemblyRepository.findById(1L)).thenReturn(Optional.of(assembly));

        Assembly foundAssembly = assemblyService.findById(1L);

        assertEquals(assembly.getId(), foundAssembly.getId());
    }

    @Test
    void testFindById_NotFound()
    {
        when(assemblyRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            assemblyService.findById(1L);
        });

        assertEquals("Assembly with the id: 1 not found!", exception.getMessage());
    }
}