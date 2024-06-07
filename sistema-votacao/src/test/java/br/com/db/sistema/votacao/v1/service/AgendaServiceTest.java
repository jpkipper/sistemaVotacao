package br.com.db.sistema.votacao.v1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.db.sistema.votacao.v1.exception.exceptions.NotFoundException;
import br.com.db.sistema.votacao.v1.model.dto.AgendaDTO;
import br.com.db.sistema.votacao.v1.model.entity.Agenda;
import br.com.db.sistema.votacao.v1.model.entity.Assembly;
import br.com.db.sistema.votacao.v1.models.entity.AgendaStub;
import br.com.db.sistema.votacao.v1.repository.AgendaRepository;

@ExtendWith(MockitoExtension.class)
public class AgendaServiceTest
{
    @InjectMocks
    private AgendaService agendaService;
    
    @Mock
    private AgendaRepository agendaRepository;

    @Mock
    private AssemblyService assemblyService;

    private Agenda agenda;
    private AgendaDTO agendaDTO;
    private Assembly assembly;

    @BeforeEach
    void setUp()
    {
        agendaDTO = new AgendaDTO();
        agendaDTO.setId(1L);
        agendaDTO.setDescription("Test Description");
        agendaDTO.setStart(LocalDateTime.now().plusDays(1));
        agendaDTO.setEnd(LocalDateTime.now().plusDays(1));
        agendaDTO.setAssemblyId(1L);

        assembly = new Assembly();
        assembly.setId(1L);
        assembly.setStart(LocalDateTime.now().plusDays(1));
        assembly.setEnd(LocalDateTime.now().plusDays(5));

        agenda = new Agenda();
        agenda.setId(1L);
        agenda.setDescription("Test Description");
        agenda.setStart(LocalDateTime.now().plusDays(1));
        agenda.setEnd(LocalDateTime.now().plusDays(2));
    }

    @Test
    void shouldCreateAgenda_Success() throws Exception
    {
        agendaDTO.setAssemblyId(1L);
        agendaDTO.setDescription("Test Description");
        when(assemblyService.findById(1L)).thenReturn( assembly );
        when(agendaRepository.save(any(Agenda.class))).thenReturn( agenda );

        agendaService.createAgenda(agendaDTO);

        verify(assemblyService, times(1)).saveAssembly(any(Assembly.class));
        verify(agendaRepository, times(1)).save(any(Agenda.class));
    }

    @Test
    void shouldCreateAgenda_InvalidDates()
    {
        AgendaDTO agendaDTO = AgendaStub.createAgendaDTOWithWrongDates();
        // agendaDTO.setStart(LocalDateTime.now().minusDays(1));

        Exception exception = assertThrows(Exception.class, () -> {
            agendaService.createAgenda(agendaDTO);
        });

        assertEquals("Data inicio não pode ser superior a data fim e inferior a data atual", exception.getMessage());
    }

    @Test
    void shouldFindById_NotFound()
    {
        when(agendaRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            agendaService.findById(1L);
        });

        assertEquals("Agenda with the id: 1 not found!", exception.getMessage());
    }

    @Test
    void shouldFindDTOById_Success() throws Exception
    {
        Agenda agenda = AgendaStub.createAgendaWithId();
        when(agendaRepository.findById(1L)).thenReturn(Optional.of(agenda));

        AgendaDTO foundAgendaDTO = agendaService.findDTOById(1L);

        assertEquals(agenda.getId(), foundAgendaDTO.getId());
        assertEquals(agenda.getDescription(), foundAgendaDTO.getDescription());
    }

    @Test
    void shouldFindDTOById_NotFound()
    {
        when(agendaRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> agendaService.findDTOById(1L));
    }

    @Test
    void shouldFindAll()
    {
        List<Agenda> agendas = List.of(AgendaStub.createAgendaWithId());
        
        when(agendaRepository.findAll()).thenReturn(agendas);

        List<AgendaDTO> result = agendaService.findAll();

        assertEquals(1, result.size());
        assertEquals(agendas.get(0).getId(), result.get(0).getId());
    }
}