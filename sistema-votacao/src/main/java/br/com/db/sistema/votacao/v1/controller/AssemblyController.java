package br.com.db.sistema.votacao.v1.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.db.sistema.votacao.config.swaggers.AssemblySwagger;
import br.com.db.sistema.votacao.v1.model.dto.AssemblyDTO;
import br.com.db.sistema.votacao.v1.service.AssemblyService;
import lombok.AllArgsConstructor;

@RequestMapping("v1/assembly")
@AllArgsConstructor
@RestController
public class AssemblyController
    implements
        AssemblySwagger
{
    private final AssemblyService assemblyService;

    @PostMapping
    public ResponseEntity<String> createAssembly( @RequestBody AssemblyDTO assemblyDTO )
    {
        try 
        {
            assemblyService.createAssembly(assemblyDTO);
            return ResponseEntity.ok( "Assembly created successfully" );
        } 
        catch( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }

    @GetMapping
    public ResponseEntity<List<AssemblyDTO>> getAllAssemblies()
    {
        List<AssemblyDTO> assemblies = assemblyService.findAll();
        return ResponseEntity.ok( assemblies );
    }
}
