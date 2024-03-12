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

Foi Deixado também um arquivo chamado `ComeiaLabs - Vendas.postman_collection.json` na raiz do projeto contendo todos end point salvos no postman e as configurações para conseguir o token usando Keycloak

## Configuração do Keycloak

Para que você possa utilizar o KeyCloack deve primeiro instalar e configurar em sua máquina utilizando esse comando em docker `docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:24.0.1 start-dev`

Ao executar este comando, o Keycloak estará disponível com o usuário e senha de administrador definidos como admin, acessível na porta 8080.

Após acessar o menu do Keycloak, siga as etapas de configuração abaixo:
- Criar um Realm (comeia)
- Criar um cliente chamado (app_comeia): Certifique-se de habilitar a opção de Client authentication nas configurações de Capability.
- Criar as roles (ADMIN e USER)
- Criar os usuários (admin_comeia e user_comeia): Preencha todas as informações necessárias do usuário.
- Para obter um token de acesso, é necessário fazer uma requisição POST passando as informações de autorização OAuth 2.0 para o Keycloak na seguinte URL: `http://localhost:8080/realms/comeia/protocol/openid-connect/token/`

Isso retornará um token Bearer Token, que será necessário para utilizar os endpoints da aplicação.

Observação: Apenas o usuário ADMIN tem permissão para fazer requisições de DELETE em Product ou Sale.
