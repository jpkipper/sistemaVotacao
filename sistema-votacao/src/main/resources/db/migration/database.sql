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