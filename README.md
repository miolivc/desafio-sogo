# Desafio Backend Java - SOGO Tecnologia
Repositório destinado a solução do desafio imposto pela SOGO para a vaga de Backend Java

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
