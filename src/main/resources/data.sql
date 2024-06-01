-- src/main/resources/data.sql


INSERT INTO pagamento (payer, payee, amount, status, created, transacao_id, callback, forma_pagamento) VALUES ('Alice', 'Bob', 100.00, 'CONCLUIDA', NOW(), 'TX123','http://localhost:8080/retorno-pagamento','SALDO');
INSERT INTO pagamento (payer, payee, amount, status, created, transacao_id, callback, forma_pagamento) VALUES ('Charlie', 'Dave', 200.50, 'PROCESSANDO', NOW(), 'TX124','http://localhost:8080/retorno-pagamento','SALDO');
INSERT INTO pagamento (payer, payee, amount, status, created, transacao_id, callback, forma_pagamento) VALUES ('Eve', 'Frank', 300.75, 'RECUSADA', NOW(), 'TX125','http://localhost:8080/retorno-pagamento','SALDO');
INSERT INTO pagamento (payer, payee, amount, status, created, transacao_id, callback, forma_pagamento) VALUES ('Grace', 'Heidi', 400.25, 'CONCLUIDA', NOW(), 'TX126','http://localhost:8080/retorno-pagamento','SALDO');