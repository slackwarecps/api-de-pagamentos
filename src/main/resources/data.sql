-- src/main/resources/data.sql


INSERT INTO pagamento (payer, payee, amount, status, created, transacao_id) VALUES ('Alice', 'Bob', 100.00, 'CONCLUIDA', NOW(), 'TX123');
INSERT INTO pagamento (payer, payee, amount, status, created, transacao_id) VALUES ('Charlie', 'Dave', 200.50, 'PROCESSANDO', NOW(), 'TX124');
INSERT INTO pagamento (payer, payee, amount, status, created, transacao_id) VALUES ('Eve', 'Frank', 300.75, 'RECUSADA', NOW(), 'TX125');
INSERT INTO pagamento (payer, payee, amount, status, created, transacao_id) VALUES ('Grace', 'Heidi', 400.25, 'CONCLUIDA', NOW(), 'TX126');