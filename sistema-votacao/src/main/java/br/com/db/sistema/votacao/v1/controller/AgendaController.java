package br.com.db.sistema.votacao.v1.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.db.sistema.votacao.v1.model.dto.AgendaDTO;
import br.com.db.sistema.votacao.v1.service.AgendaService;
import lombok.AllArgsConstructor;

@RequestMapping("v1/agenda")
@RestController
@AllArgsConstructor
public class AgendaController
{
    private final AgendaService agendaService;

    @PostMapping
    public ResponseEntity<String> createAgenda( @RequestBody AgendaDTO agendaDTO )
    {
        try 
        {
            agendaService.createAgenda( agendaDTO );
            return ResponseEntity.ok( "Agenda created successfully" );
        } 
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDTO> getAgendaById( @PathVariable Long id )
    {
        try 
        {
            return ResponseEntity.ok( agendaService.findDTOById( id ) );
        } 
        catch(Exception e) 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AgendaDTO>> getAllAgendas()
    {
        List<AgendaDTO> agendas = agendaService.findAll();
        return ResponseEntity.ok(agendas);
    }

}
