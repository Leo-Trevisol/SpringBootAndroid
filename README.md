<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tags do Spring Boot</title>
</head>

<body>

    <h1>Tags do Spring Boot</h1>
    <p>Este documento fornece uma visão geral das tags mais comuns utilizadas no Spring Boot para configuração e personalização de aplicações Java.</p>

    <h2>&lt;@SpringBootApplication&gt;</h2>
    <p>A anotação <code>@SpringBootApplication</code> é usada para marcar a classe principal da aplicação Spring Boot. Ela combina três anotações importantes: <code>@Configuration</code>, <code>@EnableAutoConfiguration</code> e <code>@ComponentScan</code>, facilitando a inicialização e configuração da aplicação.</p>

    <h2>&lt;@Controller&gt;</h2>
    <p>A anotação <code>@Controller</code> é usada para marcar uma classe como um controlador Spring MVC, que lida com solicitações HTTP e retorna respostas.</p>

    <h2>&lt;@RestController&gt;</h2>
    <p>Similar a <code>@Controller</code>, <code>@RestController</code> é usada para criar controladores em uma aplicação Spring Boot, mas ela inclui automaticamente a anotação <code>@ResponseBody</code>, indicando que o resultado do método deve ser serializado diretamente para o corpo da resposta HTTP.</p>

    <h2>&lt;@Autowired&gt;</h2>
    <p>A anotação <code>@Autowired</code> é usada para injetar dependências automaticamente em classes gerenciadas pelo Spring, como controladores, serviços e repositórios.</p>

    <h2>&lt;@Value&gt;</h2>
    <p>A anotação <code>@Value</code> é usada para injetar valores de propriedades do arquivo <code>application.properties</code> ou <code>application.yml</code> diretamente em campos de classe.</p>

    <p>Estas são apenas algumas das tags mais comuns do Spring Boot. Para mais informações sobre outras tags e funcionalidades, consulte a <a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/">documentação oficial do Spring Boot</a>.</p>

</body>

</html>
