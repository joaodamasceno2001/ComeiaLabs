# ComeiaLabs

Este projeto foi desenvolvido como parte de um teste técnico para o cargo de desenvolvedor Java Pleno.

## Requisitos para executar a aplicação

Para iniciar a aplicação, você precisa ter:
- Java versão 21 instalada
- Docker
- Um editor de código de sua preferência

## Como iniciar

Primeiro, execute o comando `docker-compose up --build` para inicializar uma imagem do banco de dados PostgreSQL. (A porta na qual esta imagem estará rodando será a 5434)

Em seguida, você pode iniciar a aplicação Java, que estará disponível na porta 8085.

Nota: Para obter mais informações sobre determinadas credenciais, consulte o arquivo `docker-compose.yml` ou `application.properties`.

## Documentação da API com OpenAPI

Você pode acessar a documentação da API através do seguinte link: [http://localhost:8085/swagger-ui/index.html#/](http://localhost:8085/swagger-ui/index.html#/).

Lá estarão listados todos os endpoints criados para o funcionamento desta aplicação.
