# api-de-pagamentos
Api de pagamentos Mock

## Requisitos
Java 17
Maven 3.8.8


# Regra de Negocio 
todos os valores maiores que 200 serao recusados

# Maquina da estado 
1 PROCESSANDO
2 RECUSADO
3 APROVADO

# Forma de pagamentos
1 PIX
2 CARTAO CREDITO
3 SALDO

# comando para criar pagamento
````
# POST /api/v1/pagamento
Header: 
{}
Body:
{
    "id":123,
    "payer":"Fabio",
    "payee":"Tatiana",
    "amount": 125.98,
    "batata-frita":3333,
    "callback":"http://localhost:8080/nada"
}
Retorno:
{
  "id": 6,
  "payer": "Fabio",
  "payee": "Tatiana",
  "amount": 125.98,
  "status": "PROCESSANDO",
  "created": "2024-06-01 06:12:50",
  "transacaoId": "f38496c6-17bd-448f-9f41-864a89d51443",
  "callback": "http://localhost:8080/nada"
}
````

## Exemplo de callback enviado para o webhook informado no campo callback
````
{
  "data":{
    "transacaoId":"0f9527f4-9625-4c9c-bf1c-a0980d0e9508",
    "status":"APROVADO"
    }
}


````

