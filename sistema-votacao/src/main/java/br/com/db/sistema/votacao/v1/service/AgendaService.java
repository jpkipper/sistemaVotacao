package br.com.db.sistema.votacao.v1.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.db.sistema.votacao.v1.model.dto.AgendaDTO;
import br.com.db.sistema.votacao.v1.model.entity.Agenda;
import br.com.db.sistema.votacao.v1.model.entity.Assembly;
import br.com.db.sistema.votacao.v1.repository.AgendaRepository;

@Service
public class AgendaService
{
    private final AgendaRepository agendaRepository;
    private final AssemblyService assemblyService;

    public AgendaService( AgendaRepository agendaRepository, AssemblyService assemblyService )
    {
        this.agendaRepository = agendaRepository;
        this.assemblyService = assemblyService;
    }

    public void createAgenda( AgendaDTO agendaDTO ) throws Exception
    {
        validateDate( agendaDTO.getStart(), agendaDTO.getEnd() );
        
        Assembly assembly = assemblyService.findById( agendaDTO.getAssemblyId() );
        validateDate( assembly.getStart(), assembly.getEnd() );

        Agenda agenda = convertToAgenda( agendaDTO );

        assembly.getAgendas().add( agenda );
        assemblyService.saveAssembly(assembly);
        save( agenda );
    }

    public AgendaDTO findDTOById( Long id ) throws Exception
    {       
        return convertToAgendaDTO( findById( id ) );
    }

    public Agenda findById( Long id ) throws Exception
    {
        Optional<Agenda> agenda = agendaRepository.findById( id );

        if( agenda.isEmpty() )
        {
            throw new Exception( "Agenda with the id: " + id + " not found!" );
        }
        
        return agenda.get();
    }

    public List<AgendaDTO> findAll()
    {
        List<Agenda> agendas = agendaRepository.findAll();

        return agendas.stream()
            .map(this::convertToAgendaDTO)
            .collect(Collectors.toList());
    }

    protected Agenda save( Agenda agenda )
    {
        return agendaRepository.save( agenda );
    }

    private Agenda convertToAgenda( AgendaDTO agendaDTO )
    {
        Agenda agenda = new Agenda();

        agenda.setId( agendaDTO.getId() );
        agenda.setDescription( agendaDTO.getDescription() );
        agenda.setStart( agendaDTO.getStart() );
        agenda.setEnd( agendaDTO.getEnd() );

        return agenda;
    }

    private AgendaDTO convertToAgendaDTO( Agenda agenda )
    {
        AgendaDTO agendaDTO = new AgendaDTO();

        agendaDTO.setId( agenda.getId() );
        agendaDTO.setDescription( agenda.getDescription() );
        agendaDTO.setStart( agenda.getStart() );
        agendaDTO.setEnd( agenda.getEnd() );

        return agendaDTO;
    }

    private void validateDate( LocalDateTime start, LocalDateTime end ) throws Exception
    {
        if (end.isBefore(start)) 
            throw new Exception("End date cannot be before start date");

        if( start.isBefore( LocalDateTime.now() ) ) 
            throw new Exception("Start date cannot be in the past");
    }
}
