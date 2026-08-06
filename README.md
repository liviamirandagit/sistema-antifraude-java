# Sistema Antifraude de Transações (Java)

Projeto desenvolvido em Java para análise de risco em transações financeiras e integração com banco de dados MySQL via JDBC.

## Tecnologias Utilizadas
* Linguagem: Java (JDK 26)
* Driver JDBC: MySQL Connector/J
* IDE: IntelliJ IDEA
* Banco de Dados: MySQL

## Estrutura do Repositório
* src/: Código-fonte do projeto (Main, regras de negócio e modelos).
* sql/: Scripts de criação e estrutura do banco de dados (schema.sql).

## Como Executar
1. Execute o script sql/schema.sql no banco MySQL.
2. Copie o arquivo src/config.properties.example para src/config.properties e preencha com suas credenciais do banco local.
3. Certifique-se de que a biblioteca mysql-connector-j está adicionada ao classpath do projeto no IntelliJ.
4. Execute a classe Main.java.