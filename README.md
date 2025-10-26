# CRUD de Produtos em Java com MySQL

Este projeto foi desenvolvido como exercício da disciplina de Programação Orientada a Objetos (POO),
com o objetivo de implementar um sistema CRUD completo acessando um banco de dados MySQL.

## Tecnologias Utilizadas
- Java (JDBC)
- NetBeans
- MySQL
- Git & GitHub

## Funcionalidades
-Inserir produtos  
-Atualizar produtos  
-Excluir produtos  
-Listar produtos cadastrados  

## Estrutura do Projeto
- **Produto.java** → Classe modelo (atributos id, nome, quantidade, valorUnitario)
- **Conexao.java** → Configuração e conexão com o banco
- **ProdutoDAO.java** → Métodos CRUD utilizando JDBC
- **ProdutoMain.java** → Aplicação console com menu interativo

## Banco de Dados
Script SQL utilizado:
```sql
CREATE DATABASE IF NOT EXISTS db_ads;
USE db_ads;

CREATE TABLE IF NOT EXISTS produto (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  quantidade INT NOT NULL,
  valorUnitario DECIMAL(10,2) NOT NULL,
  PRIMARY KEY(id)
);
