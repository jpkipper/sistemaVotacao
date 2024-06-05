package br.com.db.sistema.votacao.v1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.db.sistema.votacao.v1.service.AssemblyService;

@RequestMapping("v1/assembly")
@RestController
public class AssemblyController 
{
    public AssemblyService assemblyService;




}
