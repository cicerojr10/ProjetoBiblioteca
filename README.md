# 🚀 Catálogo de Mídias - Aplicação Web com Java

Aplicação web desenvolvida como projeto para a disciplina de Projetos Computacionais do curso de Ciência da Computação. O sistema permite o gerenciamento completo (CRUD) de um catálogo pessoal de livros, filmes e séries, com uma interface moderna e responsiva.

![Demonstração da Aplicação](URL_DA_IMAGEM_AQUI)
> **Ação:** Tire um print bonito da sua tela de listagem com o tema dark e faça o upload no seu repositório GitHub. Depois, copie o link da imagem e cole no lugar de `URL_DA_IMAGEM_AQUI`.

---

## ✨ Funcionalidades

* **Cadastro, Edição e Exclusão:** Gerenciamento completo dos itens do catálogo.
* **Listagem Dinâmica:** Visualização de todos os itens em uma tabela organizada.
* **Busca Inteligente:** Filtro em tempo real por título ou autor/diretor.
* **Interface Moderna:** Design limpo e agradável com tema claro e escuro, construído com Pico.css.

---

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído com as seguintes tecnologias:

* **Back-end:**
    * Java 17
    * Jakarta Servlets 5.0
    * JDBC (Java Database Connectivity)
* **Front-end:**
    * JSP (JavaServer Pages) & JSTL
    * Pico.css (Framework CSS minimalista)
* **Banco de Dados:**
    * MySQL 8.0
* **Ambiente e Ferramentas:**
    * Apache Tomcat 10.1 (Servidor de Aplicação)
    * Apache Maven (Gerenciamento de Dependências)
    * IntelliJ IDEA (IDE)
    * Git & GitHub (Controle de Versão)

---

## ⚙️ Como Executar o Projeto

Para executar este projeto localmente, siga os passos abaixo:

1.  **Pré-requisitos:**
    * JDK 17 ou superior
    * Apache Maven
    * Servidor MySQL 8.0
    * Apache Tomcat 10.1

2.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/nome-do-repositorio.git](https://github.com/seu-usuario/nome-do-repositorio.git)
    cd nome-do-repositorio
    ```

3.  **Configure o Banco de Dados:**
    * Crie um schema/banco de dados no seu MySQL.
    * Execute o script `CREATE TABLE` (disponível na documentação do projeto ou em um arquivo `.sql` no repositório).
    * Atualize as credenciais do banco no arquivo:
        `src/main/java/br/com/ciseravjorn/catalogo/dao/ConnectionFactory.java`

4.  **Compile e Execute:**
    * Compile o projeto com o Maven:
        ```bash
        mvn clean package
        ```
    * Faça o deploy do arquivo `.war` gerado na pasta `target/` no seu servidor Apache Tomcat.
    * Acesse `http://localhost:8080/[nome-do-seu-projeto]/listar-itens` no seu navegador.

---

## 👨‍💻 Autor

* **Cícero Quintino Junior**
    * [LinkedIn](https://www.linkedin.com/in/cicerojr-techprofessional/)
    * [GitHub](https://github.com/cicerojr10)