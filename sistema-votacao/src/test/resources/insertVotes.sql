
-- Inserindo nova associado
INSERT INTO tb_associate ( id, name, cpf, associate_status_enum )
VALUES (1,'João Kipper', '01964652065', 'ABLE_TO_VOTE');

-- Inserindo nova assembleia
INSERT INTO tb_assembly (id, start, end) VALUES (1, '2000-02-19 14:00:00.000000', '2000-02-19 14:00:00.000000');

-- Inserindo nova Pauta
INSERT INTO tb_agenda ( id, description, start, end ) VALUES (1, 'Nova Pauta', '2030-02-19 14:00:00.000000', '2030-02-19 17:00:00.000000');
INSERT INTO tb_agenda ( id, description, start, end ) VALUES (2, 'Nova Pauta', '2000-02-19 14:00:00.000000', '2000-02-19 17:00:00.000000');

-- Inserindo nova Pauta na Assembleia
INSERT INTO assembly_agenda ( assembly_id, agenda_id ) VALUES (1, 2);
INSERT INTO assembly_agenda ( assembly_id, agenda_id ) VALUES (1, 1);

-- Inserindo voto na Pauta
INSERT INTO tb_vote ( id, agenda_id, associate_id, value ) VALUES (1, 1, 1, 'SIM');