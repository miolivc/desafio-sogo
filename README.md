# Desafio Backend Java - SOGO Tecnologia
Repositório destinado a solução do desafio para a vaga de Backend Java na SOGO Tecnologia

## Execução do Projeto:

* **Ambiente Local:**
    - realizar o download `.zip` ou clone deste repositório;
    - navegar até o diretório desta aplicação;
    - alterar o arquivo `application-local.properties` colocando os valores do datasource referente ao seu banco de dados postgresql;
    - Executar no diretório da aplicação o seguinte comando:  
        `gradle :bootRun -Dspring.profiles.active=local`.

    **Observação:** Necessário necessário possuir `java` e `gradle` configurados; Não é necessário executar o script de criação do *schema*.

* **Docker Compose:**
    - realizar o download `.zip` ou clone deste repositório;
    - Executar no diretório da aplicação o seguinte comando:  
        `docker-compose up -d` ou `sudo docker-compose up -d`.

    **Observação:** Necessário possuir `docker` e `docker-compose` instalados.

## Documentação da API:

* **JSON endpoint:** `http://localhost:8080/api/v1/api-docs`
* **Swagger UI:** `http://localhost:8080/api/v1/swagger-ui/`

## Autenticação para utilizaçao da API:

* É necessário realizar chamada POST ao endpoint `/authenticate` usando as credenciais no body:

```
{
	"username": "gestor", 
	"password": "gestor@123"
}
```

* Para demais requisições adicionar o cabeçalho `Authentication: Bearer <your_generated_token>`, utilizando o token retornado pelo `/authenticate`

## Pesquisa avançada:

* Implementada na entidade `Person` no endpoint: `/personQuery` seguindo o JPA Specification Query Language (uso do `spring-search` para abstrair QueryBindings)

### Observações: 
* Para auxiliar nas requisições a API existe um arquivo `Insomnia_requests.json` que pode utilizado na realizaçao dos testes