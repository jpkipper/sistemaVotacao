package br.com.db.sistema.votacao.v1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.db.sistema.votacao.config.swaggers.VoteSwagger;
import br.com.db.sistema.votacao.v1.model.dto.VoteDTO;
import br.com.db.sistema.votacao.v1.service.VoteService;
import lombok.AllArgsConstructor;

@RequestMapping("v1/vote")
@RestController
@AllArgsConstructor
public class VoteController
    implements
        VoteSwagger
{
    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<String> saveVote( @RequestBody VoteDTO voteDTO )
    {
        try
        {
            voteService.saveVote( voteDTO );
            return ResponseEntity.ok( "Vote saved successfully" );
        } 
        catch ( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }
}
