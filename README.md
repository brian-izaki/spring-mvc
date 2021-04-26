# Projeto de gerenciamento de cobranças
É um projeto do workshop da AlgaWorks chamado iniciando com Spring MVC. Nele será criado uma aplicação Web que vai realizar o CRUD completo para as cobranças que uma pessoa receber. Poderá ser visto o valor, se foi pago e o nome da pessoa/empresa que tem a cobrança pendente.

## Tecnologias utilizadas
- Spring Web
- Thymeleaf (template engine)
  - thymeleaf-layout-dialect (para poder reutilizar fragmentos de layout)
- Spring Data JPA (para realizar comandos de persistencia de dados utilizando Spring Data com Hibernate)
- MySQL
- Spring Boot DevTools (para agilizar o recarregamento do servidor quando alterar algo)
- Maven (para gerenciamento de dependências)

## Requisitos para iniciar projeto
- Ter o Java instalado e configurado
- Versão do Java 1.8 no mínimo.
- no eclipse deve ter o [Spring Tools](https://spring.io/tools) instalado. 

## Iniciar projeto
1. Deve clonar/baixar este repositório
2. Abrir este projeto no IDE Eclipse (recomendo ele pois foi quem utilizei).
3. Em seguida, deve ir para o arquivo `CobrancaApplication.java` (no diretório `src/main/java` na package `com.algaworks.cobranca`)
4. Ao abrir o arquivo na IDE deve clicar no ícone de start.
5. Agora basta apenas abrir o navegador e ir para a seguinte URL: localhost:8080/cobranca

## Anotações das aulas
- [Spring](https://spring.io/)
  - é um conjunto de vários frameworks
  - Cada framework possui um foco de especialidade (dados, arquitetura orientada a serviço, web, entre outros)

- Foi utilizado o padrão **MVC (Model View Controller)**. 
  - A _Controller_ no Spring será o responsável por gerenciar as rotas da aplicação e então enviar a sua respectiva _View_ com dados que deseja do banco de dados ou que receberam algum tratamento com a regra de negócio.
  - a Model fica responsável pelo modelo de dados, que então com as Notations do Spring + Hibernate se tornam tabelas no banco de dados. Além claro, de se ser um Objeto que pode ser instanciado em tempo de execução. Nela vai ter algumas formatações de estilos que o próprio Spring fornece.
	
- **Framework Action Based**
  - isto é, na camada de Controller - do mvc - ele vai empurrar dados para a camada View, nela será montado o layout com base nesses dados empurrados. 

- **Maven**
  - o arquivo `pom.xml` é onde ficam as dependências que serão utilizadas no pojeto, por ele que o Eclipse sabe quais serão baixadas.
  	- as dependências ficam dentro da tag `<dependencies></dependencies>`
  	  - dentro dela deve ter tags de `<groupId>` (nome da empresa responsavel) e `<artifactId>` (nome da dependencia)
  - arquivo `application.properties`.
    - possibilita alterar as configurações padrões do Spring.
    - permite criar variaveis de ambiente.
    - alterar a porta padrão da aplicação.
    - `logging.level.root=DEBUG`: serve para ver no console o debug da parte Front (requisições, respostas) enquanto a aplicação está em execução.
  - as dependências podem ser encontradas no site [maven repository](https://mvnrepository.com/))

- **Notations do Spring**
  - `@AutoWired`: serve para fazer injeção de dependencia, ele busca o objeto a ser instanciado dentro do projeto, assim, não tendo a necessidade de fazer uma intanciação dele com o `new`.

- **Thymeleaf**
  - É uma template engine (auxilia na construção de layout baseada nos dados que receberá da aplicação) 
  - É possível utilizar estruturas de repetição, variaveis, métodos, entre outros.
  - Para utilizar ele é necessário passar `xmlns:th="https://www.thymeleaf.org"` como atributo da tag `<html>`
  - As páginas HTML que utilizam o thymeleaf estão no diretório `src/main/resources/templates` (possuem exemplos de utilização).
  - Existem **fragmentos** de elementos, com eles pode apenas adicionar uma parte de um código sem a necessidade de repetir código HTML.
  
- **Banco H2**
  - é um Banco de dados em memória, ou seja, não tem a necessidade de instalar um banco de dados relacional.
  - ele apenas armazena os dados enquanto a aplicação está em execução. Quando ela para, os dados são apagados.
  - foi utilizado apenas no inicio do projeto para não ter a necessidade de realizar configurações com o banco de dados MySQL.
  
---

## Reference
Para futuras referências, veja os seguintes links:

- [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-developing-web-applications)
- [Template engines com Spring](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
- [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-jpa-and-spring-data)
- [Spring initializr](https://start.spring.io/)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/)
- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/#build-image)

- Guias
	- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
	- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
	- [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
	- [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
	- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)