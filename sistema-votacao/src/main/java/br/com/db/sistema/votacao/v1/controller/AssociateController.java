package br.com.db.sistema.votacao.v1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.db.sistema.votacao.v1.exception.AssociateNotFoundException;
import br.com.db.sistema.votacao.v1.model.dto.AssociateDTO;
import br.com.db.sistema.votacao.v1.service.AssociateService;
import lombok.AllArgsConstructor;

@RequestMapping("v1/associate")
@RestController
@AllArgsConstructor
public class AssociateController
{
    private final AssociateService associateService;

    @PostMapping
    public ResponseEntity<String> createAssociate( @RequestBody AssociateDTO associateDTO )
    {
        try
        {
            associateService.createAssociate( associateDTO );
            return ResponseEntity.ok( "Associate created successfully" );
        }
        catch( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<AssociateDTO> getAssociateByCpf( @PathVariable String cpf )
    {
        try
        {
            AssociateDTO associateDTO = associateService.findAssociateDTO( cpf );
            return ResponseEntity.ok( associateDTO );
        }
        catch( AssociateNotFoundException e )
        {
            return ResponseEntity.notFound().build();
        }
    }
}
