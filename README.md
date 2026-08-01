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

## 🚀 Como Executar o Projeto

### Pré-requisitos
Certifique-se de ter instalado em sua máquina:
* **Java Development Kit (JDK):** Versão 8 ou superior.
* **PostgreSQL:** Versão 9 ou superior (recomendado 17/18).
* Uma IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code).

### 1. Configuração do Banco de Dados
1. Abra o seu gerenciador do PostgreSQL (como o pgAdmin).
2. Crie um novo banco de dados chamado `sistema_BD`.
3. Verifique a porta em que seu PostgreSQL está rodando (o padrão é `5432`, mas pode variar para `5433` caso tenha mais de uma versão instalada).

### 2. Configuração das Credenciais
No projeto, navegue até a classe `ConnectionFactory` (localizada no pacote `util`) e atualize as credenciais para baterem com as do seu banco de dados local:
* `URL`: Ajuste a porta se necessário (ex: `jdbc:postgresql://localhost:5433/sistema_BD`).
* `USUARIO`: O seu usuário do Postgres (padrão: `postgres`).
* `SENHA`: A sua senha configurada na instalação.

### 3. Configuração do Driver JDBC
O projeto utiliza JDBC puro e precisa do driver do PostgreSQL para conectar ao banco:
1. Faça o download do driver JDBC mais recente (.jar) no [Site Oficial do PostgreSQL JDBC](https://jdbc.postgresql.org/download/).
2. Na sua IDE, adicione o arquivo `.jar` baixado como uma dependência do projeto.
    * *No IntelliJ:* `File > Project Structure > Modules > Dependencies > botão de + > JARs or Directories`.

### 4. Executando a Aplicação
Após as configurações acima, basta executar a classe `Main.java` e interagir com o sistema através do terminal!

## 👨‍💻 Desenvolvedor
**Gabriel Henrique Dalmoro**
* [LinkedIn](https://www.linkedin.com/in/gabriel-henrique-dalmoro)