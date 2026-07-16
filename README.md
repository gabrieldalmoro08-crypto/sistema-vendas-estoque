# 📦 Sistema de Gerenciamento de Vendas e Estoque

## 💻 Sobre o projeto
O Sistema de Vendas e Estoque é uma aplicação desenvolvida com o objetivo de gerenciar o fluxo de mercadorias, cadastro de clientes e registro de vendas de uma loja. O foco principal deste projeto é a aplicação prática e rigorosa de conceitos sólidos de **Programação Orientada a Objetos (POO)**, **Arquitetura em Camadas** e boas práticas de desenvolvimento no ecossistema Java.

## 🛠 Tecnologias e Padrões
* **Linguagem:** Java
* **Banco de Dados:** PostgreSQL
* **Comunicação com BD:** JDBC Puro (foco na consolidação da base e entendimento de drivers)
* **Arquitetura:** Padrão MVC (Model, View, Controller) com forte separação de responsabilidades.
* **Padrões de Projeto:** DAO (Data Access Object) e injeção de dependências via Services.

## ⚙️ Principais Funcionalidades
O núcleo do sistema foi construído para garantir integridade e segurança dos dados empresariais:
* **Gestão de Entidades:** CRUD completo para Produtos, Vendas, Clientes e Usuários.
* **Regras de Negócio Isoladas:** A camada `Service` atua como o "cérebro" da aplicação, blindando o banco de dados contra inconsistências.
* **Automação de Estoque:** Baixa automática e verificação de disponibilidade de mercadorias durante o registro de vendas.
* **Integridade Referencial:** Proteção ativa contra exclusão de clientes, produtos ou usuários que possuam dependências ou histórico atrelado no sistema.
* **Controle de Acesso:** Fluxo de autenticação estruturado, dividindo permissões e menus dinâmicos entre Administradores e Clientes.

## 🏗️ Evolução e Escalabilidade
A arquitetura do projeto foi desenhada de forma modular para suportar diferentes interfaces. Devido ao isolamento rígido entre a lógica de negócios (`Service`), o acesso a dados (`DAO`) e a interface do usuário (`View`), o sistema está estruturalmente preparado para evoluir de aplicações rodando em terminal local para **APIs RESTful** ou integrações Web completas (via Servlets, JSON e Frontend em HTML/JS) sem necessidade de refatoração do seu núcleo.

## 👨‍💻 Desenvolvedor
**Gabriel Henrique Dalmoro**
* [LinkedIn](https://www.linkedin.com/in/gabriel-henrique-dalmoro)