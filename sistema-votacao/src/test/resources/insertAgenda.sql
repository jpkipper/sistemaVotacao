DELETE FROM tb_agenda;

-- Inserindo nova assembleia
INSERT INTO tb_assembly (id, start, end) VALUES (1, '2000-02-19 14:00:00.000000', '2000-02-19 14:00:00.000000');

-- Inserindo nova Pauta
INSERT INTO tb_agenda ( id, description, start, end ) VALUES (1, 'Nova Pauta', '2030-02-19 14:00:00.000000', '2030-02-19 17:00:00.000000');
INSERT INTO tb_agenda ( id, description, start, end ) VALUES (2, 'Nova Pauta2', '2000-02-19 14:00:00.000000', '2000-02-19 17:00:00.000000');

-- Inserindo nova Pauta na Assembleia
INSERT INTO assembly_agenda ( assembly_id, agenda_id ) VALUES (1, 2);
INSERT INTO assembly_agenda ( assembly_id, agenda_id ) VALUES (1, 1);