package br.com.db.sistema.votacao.v1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.db.sistema.votacao.v1.service.AgendaService;

@RequestMapping("v1/agenda")
@RestController
public class AgendaController
{
    public AgendaService agendaService;



}
