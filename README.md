# Projeto de gerenciamento de cobranças
É um projeto do workshop da AlgaWorks chamado iniciando com Spring MVC. Nele será criado uma aplicação Web que vai realizar o CRUD completo para as cobranças que uma pessoa receber. Poderá ser visto o valor, se foi pago e o nome da pessoa/empresa que tem a cobrança pendente.

## Tecnologias utilizadas
- Back-end:
  - Spring Web
  - Spring Data JPA (para realizar comandos de persistencia de dados utilizando Spring Data com Hibernate)
  - MySQL
  - Spring Boot DevTools (para agilizar o recarregamento do servidor quando alterar algo)
  - Maven (para gerenciamento de dependências)
  
-Front-end:
  - Thymeleaf (template engine)
  - thymeleaf-layout-dialect (para poder reutilizar fragmentos de layout)
  - Bootstrap (ver. 4.6)
  - Jquery

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

- Padrão **MVC (Model View Controller)**. 
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
  - `@AutoWired`: serve para fazer injeção de dependencia, ele busca o objeto a ser instanciado dentro do projeto, assim, não tendo a necessidade de fazer uma intanciação dele com o `new`. Não é mágica, ele está pegado da interface criada e instanciando no repository com o autoWired

- [**Thymeleaf**](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)
  - É uma template engine (auxilia na construção de layout baseada nos dados que receberá da aplicação) 
  - É possível utilizar estruturas de repetição, variaveis, métodos, entre outros.
  - Para utilizar ele é necessário passar `xmlns:th="https://www.thymeleaf.org"` como atributo da tag `<html>`
  - As páginas HTML que utilizam o thymeleaf estão no diretório `src/main/resources/templates` (possuem exemplos de utilização).
  - Existem **fragmentos** de elementos, com eles pode apenas adicionar uma parte de um código sem a necessidade de repetir código HTML.
  - [Sintaxe de Expressões](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#standard-expression-syntax)
  - `@`: usado para montar links. `@{/rota/{var}(var=${valor})}`
    - no projeto ajudou a criar uma URL dinâmica
- **Validações**
  - O Spring fornece validações para os dados.
  - É necessário utilizar a dependencia `spring-boot-starter-validation` no pom.xml, pois ele é uma dependencia separada.
  - Para utilizar as validações, é necessário **utilizar Annotations em cada atributo na Model**.
    ```Java
    @Entity
    class MinhaEntidadeDaModel {
		@NotBlank(message = "Descrição é obrigatória")
		@Size(max = 60, message = "A descrição não pode ter mais de 60 caracteres")
		private String description;
		
		// getters e setters
	} 
    ```
    - Note o uso da annotation `@Entity`, ela serve para o Spring reconhecer a classe como entidade, ou seja, ela vai representar uma tablela no Banco de dados.
    
  - Existem diferentes validações, como pode ser [visto na documentação](https://javaee.github.io/javaee-spec/javadocs/javax/validation/constraints/package-summary.html).
  - Para que as validações da model sejam feitas, é necessário passar a Annotation `@Validated` **na frente do parametro**
  	```Java
  	public ModelAndView meuMetodo(@Validated MeuObjeto meuObjeto, Errors errors) {
		ModelAndView mv = new ModelAndView("NomeDaViewHtml");
		if (errors.hasErrors()) {
			return mv;
		}
		// codigos para persistir dados.
		return mv;
	}
  	```
  	
  	- O tipo `Errors` vai ser **preenchido de forma automática pelo Spring** e é possível verificar se teve erro com métodos do próprio objeto.
  	- na view, o objeto que vai ser enviado, pode pegar o atributo `message` que vai conter as mensagens de erros (padrão ou customizadas lá na nossa **model**)

- **Classes do Spring**
  - `RedirectAttributes`: ela auxilia no redirecionamento de página. Ao redirecionar de página, auxilia no envio de dados com o método `addFlashAttribute` que tem a mesma funcionalidade do `addObject` do tipo `ModelAndView`. A diferença é que ao redirecionar para uma página, ela atualiza nesse processo, o que faz os dados enviados com a ModelAndView sejam perdidos no processo. Já com o RedirectAttributtes, ele envia mesmo com a página atualizando.
  - Para tratamento de erros: 
    - **`DataIntegrityViolationException`**: foi utilizado para tratamento de erro do tipo date para o caso de ser uma data inválida.
- **CRUD**
  - Foi utilizado o JPARepository para realizar as querys na aplicação (Ver o arquivo na package "repository").
  - Create
    - utilizado o método `save(Objeto)`, caso o Objeto **não possua um id**.
  - Read
    - utilizado o método `findAll()` e `getOne(paramDeBusca)`
  - Update
    - utiliado o mesmo método do Create, mas o Objeto **deve possuir um id**.

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