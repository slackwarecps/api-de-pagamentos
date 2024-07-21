# Api-de-pagamentos
Api de pagamentos Mock. Este projeto é uma API de pagamentos mock, desenvolvida em Java 17 e Maven 3.8.8, que simula o processamento de pagamentos com diferentes formas de pagamento e estados de transação.
Este resumo fornece uma visão geral do projeto, incluindo os requisitos, regras de negócio, endpoints principais e instruções para acessar a documentação da API e configurar o ambiente de desenvolvimento.

## Requisitos
Java 17
Maven 3.8.8


# Regras de Negocio 
Todos os valores de pagamento maiores que 200 serão recusados.

# Maquinas da estado 
1 PROCESSANDO
2 RECUSADO
3 APROVADO

# Formas de pagamentos
1 PIX
2 CARTAO CREDITO
3 SALDO

# Endpoints

## comando para criar pagamento
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
    "formaPagamento":"PIX",
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

## Buscar Status da Transação
* Método: GET
* URL: /api/v1/pagamento/transacao/{transacaoId}
* Exemplo de Retorno:
````
GET 

http://localhost:8080/api/v1/pagamento/transacao/1b51736b-2e32-4934-8036-078275e0c162

{
  "id": 1,
  "payer": "Alice",
  "payee": "Bob",
  "amount": 100.00,
  "status": "CONCLUIDA",
  "created": "2024-06-01 08:34:40.331697",
  "transacaoId": "TX123",
  "callback": "https://open-bingo.wiremockapi.cloud/pagbank/notificacao",
  "formaPagamento": "SALDO"
}
````

## Documentacao da API 
Para acessar a documentação da API, execute o projeto e abra o Swagger:
```
$ cd raiz-do-projeto
$ mvn spring-boot:run
```

* Swagger URL: http://localhost:8080/swagger-ui/index.html#/

## Configuração do VSCode
Para configurar o VSCode para executar o projeto, use o seguinte arquivo launch.json:
````
//launch.json
{
    // Use IntelliSense to learn about possible attributes.
    // Hover to view descriptions of existing attributes.
    // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Current File",
            "request": "launch",
            "mainClass": "${file}"
        },
        {
            "type": "java",
            "name": "Bingo-Pay",
            "request": "launch",
            "mainClass": "br.com.fabioalvaro.pagamento.PagamentoApplication",
            "projectName": "pagamento",
            "args": "--spring.profiles.active=local"
        }
    ]
}
````

# Collection do ThunderClient 
Para testar os metodos existe uma collection json que pode ser acessada na pasta /collection
* instale a extensao ThunderCliente do vscode ou importe a collection no postman.

