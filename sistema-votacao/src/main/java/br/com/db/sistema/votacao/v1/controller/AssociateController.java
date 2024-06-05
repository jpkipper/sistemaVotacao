package br.com.db.sistema.votacao.v1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.db.sistema.votacao.v1.service.AssociateService;

@RequestMapping("v1/associate")
@RestController
public class AssociateController
{
    public AssociateService associateService;



    
}
