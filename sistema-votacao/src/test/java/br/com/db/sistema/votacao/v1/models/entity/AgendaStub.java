package br.com.db.sistema.votacao.v1.models.entity;

import java.time.LocalDateTime;

import br.com.db.sistema.votacao.v1.model.dto.AgendaDTO;
import br.com.db.sistema.votacao.v1.model.entity.Agenda;

public class AgendaStub
{
    public static Agenda createAgendaWithId()
    {
        Agenda agenda = Agenda.builder()
                            .id( 1L )
                            .description( "Agenda Test" )
                            .start( LocalDateTime.now().plusDays( 1 ) )
                            .end( LocalDateTime.now().plusDays( 2 ) )
                            .build();
        return agenda;
    }

    public static Agenda createAgendaWithWrongDates()
    {
        Agenda agenda = Agenda.builder()
                            .description( "Agenda Test" )
                            .start( LocalDateTime.now().minusDays( 1 ) )
                            .end( LocalDateTime.now().plusMinutes( 1 ) )
                            .build();
        return agenda;
    }

    public static AgendaDTO createAgendaDTOWithtId()
    {
        AgendaDTO agendaDTO = AgendaDTO.builder()
                            .id( 1L )
                            .description("Agenda Test")
                            .assemblyId( 1L )
                            .start( LocalDateTime.now().plusDays( 1 ) )
                            .end( LocalDateTime.now().plusDays( 2 ) )
                            .build();

        return agendaDTO;
    }

    public static AgendaDTO createAgendaDTOWithoutId()
    {
        AgendaDTO agendaDTO = AgendaDTO.builder()
                            .description("Agenda Test")
                            .assemblyId( 1L )
                            .start( LocalDateTime.now().plusDays( 1 ) )
                            .end( LocalDateTime.now().plusDays( 2 ) )
                            .build();

        return agendaDTO;
    }

    public static AgendaDTO createAgendaDTOWithWrongDates()
    {
        AgendaDTO agendaDTO = AgendaDTO.builder()
                            .description("Aenda Test")
                            .assemblyId( 1L )
                            .start( LocalDateTime.now().minusDays( 1 ) )
                            .end( LocalDateTime.now().plusMinutes( 1 ) )
                            .build();

        return agendaDTO;
    }
}
