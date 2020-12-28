INSERT INTO empresa(cnpj, data_atualizacao, data_criacao, razao_social)
VALUES('45245766000174', CURRENT_DATE, CURRENT_DATE, 'Nautilus IT');

INSERT INTO funcionario(
    cpf,
    data_atualizacao,
    data_criacao,
    email,
    nome,
    perfil,
    qtd_horas_almoco,
    qtd_horas_trabalho_dia,
    senha,
    valor_hora,
    empresa_id)
VALUES(
    '95854827115',
    CURRENT_DATE,
    CURRENT_DATE,
    'admin@nautilus.com',
    'ADMIN',
    'ROLE_ADMIN',
    NULL,
    NULL,
    '$2a$10$jLBnaja8ZSNh9n1NTWefK.GCa97eo2FNW3nuxgofs3gNdwlpy6Cn.',
    NULL,
    (SELECT ID FROM empresa WHERE CNPJ = '45245766000174'));