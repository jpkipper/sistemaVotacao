<p align="center">
  <h1 align="center">Sistema Votação de Pautas em Assembleias</h1>
</p>

<p align="center">

[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
<img src="https://img.shields.io/badge/Version-1.0.0-brightgreen.svg"/>
<img src="https://img.shields.io/badge/PRs-welcome-brightgreen.svg"/>
</p>

## Tabela de Conteúdo

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Swagger](#swagger)
- [Modelo de Dados](#modelo-de-dados)
- [Estrutura do projeto](#estrutura-do-projeto)


## Sobre o Projeto

Esse projeto tem como objetivo implementar um sistema para gerenciar a realização de assembleias, pautas e votações por associados.


## Funcionalidades

O sistema permite:

- Cadastrar e consultar associates
- Cadastrar e consultar assembleias
- Cadastrar e consultar pautas
- Registrar votos dos associados nas pautas durante as assembleias


## Tecnologias

O sistema foi desenvolvido utilizando as seguintes tecnologias:

| Objetivo | Tecnologia |
| ------ | ------ |
| Linguagem de programação | Java 17 |
| Banco de Dados | MySql |
| Framework | SpringBoot  |
| Documentação | Swagger  |
| Cobertura de Testes | Maven  |
| Testes de Integração | MockMvc  |


## [Swagger](http://localhost:8080/swagger-ui/index.html)

  - **LINK PARA A DOCUMENTAÇÃO:** http://localhost:8080/swagger-ui/index.html


## Modelo de Dados

CREATE TABLE tb_associate
(
    id        serial PRIMARY KEY,
    name      VARCHAR(150) NOT NULL,
    cpf       VARCHAR(14)  NOT NULL,
    status    VARCHAR(40)  NOT NULL,
    UNIQUE (cpf)
);

CREATE TABLE tb_assembly
(
    id        serial PRIMARY KEY,
    dt_start  timestamp with time zone NOT NULL,
    dt_end    timestamp with time zone NOT NULL
);

CREATE TABLE tb_agenda
(
    id          serial PRIMARY KEY,
    descricao   text                     NOT NULL,
    dt_start    timestamp with time zone NOT NULL,
    dt_end      timestamp with time zone NOT NULL
);

CREATE TABLE tb_vote
(
    id            serial PRIMARY KEY,
    agenda_id     integer     NOT NULL,
    associate_id  integer     NOT NULL,
    value         VARCHAR(10) NOT NULL,
    FOREIGN KEY (agenda_id) REFERENCES agenda (id),
    FOREIGN KEY (associate_id) REFERENCES associate (id)
);

CREATE TABLE assembly_agenda
(
    assembly_id  bigint NOT NULL REFERENCES assembly (id) ON DELETE CASCADE,
    agenda_id    bigint NOT NULL REFERENCES agenda (id) ON DELETE CASCADE,
    PRIMARY KEY (assembly_id, agenda_id)
);

CREATE TABLE agenda_votation
(
    agenda_id  bigint NOT NULL REFERENCES agenda (id) ON DELETE CASCADE,
    votes_id   bigint NOT NULL REFERENCES vote (id) ON DELETE CASCADE,
    PRIMARY KEY (agenda_id, votes_id)
);


## Estrutura do projeto

# Controllers
  - Responsáveis por processar as requisições HTTP, chamada aos **SERVIÇOS**, transformar dados recebidos na requisição HTTP em objetos de modelo apropriados ou DTOs.
  - Implementa uma interface `Swagger.java`, que possui a documentação do swagger para cada classe.

# Models
  - Centraliza os objetos usados para transferência de dados entre camadas da aplicação **DTO**, tipos de dados especiais que consistem em um conjunto fixo de constantes pré-definidas **ENUMS** e as **ENTITY** que representam objetos persistentes em um banco de dados relacional. Elas são mapeadas para tabelas no banco de dados e contêm os atributos e comportamentos que descrevem uma instância específica do objeto.

# Services
  - Encapsula a lógica de negócios e coordena as operações entre diferentes partes do sistema.

# Repository
  - Responsável pela interação com o banco de dados. Ele contém interfaces que estendem JpaRepository ou outras interfaces do Spring Data JPA, fornecendo métodos para realizar operações de CRUD (Create, Read, Update, Delete) e consultas customizadas.

# Tratamento de erros e exceções
  - Criadas **EXCEPTIONS** e **HANDLERS** personalizadas que estendem as classes de exceção padrão em Java, como Exception ou RuntimeException, para representar condições de erro específicas dentro da aplicação.