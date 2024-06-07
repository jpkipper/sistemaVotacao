package br.com.db.sistema.votacao.v1.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.db.sistema.votacao.v1.exception.exceptions.BadRequestException;
import br.com.db.sistema.votacao.v1.exception.exceptions.NotFoundException;
import br.com.db.sistema.votacao.v1.model.dto.AgendaDTO;
import br.com.db.sistema.votacao.v1.model.dto.AssemblyDTO;
import br.com.db.sistema.votacao.v1.model.entity.Agenda;
import br.com.db.sistema.votacao.v1.model.entity.Assembly;
import br.com.db.sistema.votacao.v1.repository.AssemblyRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssemblyService
{
    private final AssemblyRepository assemblyRepository;

    public void createAssembly( AssemblyDTO assemblyDTO )
    {
        validateDate( assemblyDTO.getStart(), assemblyDTO.getEnd() );
        
        Assembly assembly = convertToAssembly( assemblyDTO );

        saveAssembly( assembly );
    }

    private Assembly convertToAssembly( AssemblyDTO assemblyDTO )
    {
        List<Agenda> agendas = assemblyDTO.getAgendas().stream()
            .map(x -> {
               Agenda a = new Agenda();
               return a;
            })
            .collect(Collectors.toList());

        Assembly assembly = new Assembly( assemblyDTO, agendas );

        return assembly;
    }

    private AssemblyDTO convertToAssemblyDTO( Assembly assembly )
    {
        AssemblyDTO assemblyDTO = new AssemblyDTO();

        assemblyDTO.setId(assembly.getId());
        assemblyDTO.setStart(assembly.getStart());
        assemblyDTO.setEnd(assembly.getEnd());

        List<AgendaDTO> agendaDTOs = assembly.getAgendas().stream()
            .map(x -> {
                AgendaDTO a = new AgendaDTO();
                return a;
            })
            .collect(Collectors.toList());

        assemblyDTO.setAgendas( agendaDTOs );

        return assemblyDTO;
    }

    public List<AssemblyDTO> findAll()
    {
        List<Assembly> assemblies = assemblyRepository.findAll();

        return assemblies.stream()
            .map(this::convertToAssemblyDTO)
            .collect(Collectors.toList());  
    }

    protected Assembly findById( Long id )
    {
        Optional<Assembly> assembly = assemblyRepository.findById( id );

        if( assembly.isEmpty() )
        {
            throw new NotFoundException( "Assembly with the id: " + id + " not found!" );
        }

        return assembly.get();
    }

    protected Assembly saveAssembly( Assembly assembly )
    {
        return assemblyRepository.save( assembly );
    }

    private void validateDate( LocalDateTime start, LocalDateTime end )
    {
        if ( end.isBefore( start ) || start.isBefore( LocalDateTime.now() ) )
            throw new BadRequestException("Data inicio não pode ser superior a data fim e inferior a data atual");
    }
}
