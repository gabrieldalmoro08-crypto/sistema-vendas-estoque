# 📦 Sistema de Gerenciamento de Vendas e Estoque

> 🚧 Projeto em Desenvolvimento 🚧

## 💻 Sobre o projeto
O Sistema de Vendas e Estoque é uma aplicação desenvolvida com o objetivo de gerenciar o fluxo de mercadorias, cadastro de clientes e registro de vendas de uma loja. O foco principal deste projeto é aplicar conceitos sólidos de **Programação Orientada a Objetos (POO)**, **Arquitetura em Camadas** e boas práticas de desenvolvimento no ecossistema Java.

## 🛠 Tecnologias e Arquitetura
* **Linguagem:** Java
* **Banco de Dados:** MySQL / PostgreSQL *(Altere para o que você estiver usando)*
* **Comunicação com BD:** JDBC Puro (sem frameworks, para consolidação da base)
* **Padrão de Arquitetura:** Baseado em MVC (Model, View, Controller), com forte separação entre DAOs e Services.

## ⚙️ O que já foi implementado (Fase 1: Core da Aplicação)
- [x] **Camada Model:** Criação das entidades (Produto, Venda, Cliente, Usuário).
- [x] **Camada DAO (Data Access Object):** Isolamento de todas as operações de banco de dados (CRUD) e mapeamento relacional.
- [x] **Camada Service:** Implementação do "cérebro" do sistema, contendo as validações e regras de negócio antes de qualquer persistência.
- [x] **Regras Complexas:** Baixa automática de estoque após vendas e proteção contra exclusão de dados com dependências ativas (Integridade Referencial).

## 🚀 Próximos Passos (Roadmap da Fase 2: Interface Web)
- [ ] Transição dos Controllers atuais para **Java Servlets**.
- [ ] Construção da camada de visualização (Frontend) utilizando **HTML, CSS e JavaScript Vanilla**.
- [ ] Integração entre Frontend e Backend através de comunicação **JSON (Fetch API)**.
- [ ] Sistema de Autenticação e Autorização (Controle de acesso para Administradores vs Clientes).

## 👨‍💻 Desenvolvedor
Gabriel - 
LinkedIn: linkedin.com/in/gabriel-henrique-dalmoro