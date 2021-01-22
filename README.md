# api-schedule


● Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de
uma API REST: (● Documentação do código e da API) http://localhost:8091/swagger-ui.html


● Cadastrar uma nova pauta 
● Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default)
propriedade agenda.default.closure no arquivo application.yml
POST /api/agenda/v1.0

● Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta)
POST /api/vote/v1.0

● Contabilizar os votos e dar o resultado da votação na pauta
GET /api/agenda/v2.0

● É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->

● Tarefa Bônus 1 - Integração com sistemas externos
○ Integrar com um sistema que verifique, a partir do CPF do associado, se ele pode votar
org.springframework.web.client.RestTemplate;
URL_VALIDATION_CPF = "https://user-info.herokuapp.com/users";

● Tarefa Bônus 2 - Mensageria e filas
○ O resultado da votação precisa ser informado para o restante da plataforma, isso deve ser feito preferencialmente através de mensageria. Quando a sessão de votação fechar, poste uma mensagem com o resultado da votação
RabbitMQ
EXCHANGE_NAME = AGENDA
QUEUE = AGENDA-CLOSED
ROUTING_KEY = ""


● Tarefa Bônus 3 - Performance
○ Imagine que sua aplicação possa ser usada em cenários que existam centenas de milhares de votos. Ela deve se comportar de maneira performática nesses cenários
○ Testes de performance são uma boa maneira de garantir e observar como sua aplicação se comporta
Jmeter


● Tarefa Bônus 4 - Versionamento da API
○ Como você versionaria a API da sua aplicação? Que estratégia usar?
Classe SwaggerConfig - metodos {agendaApiV1,agendaApiV2,voteApiV1}

Explicação breve do porquê das escolhas tomadas durante o desenvolvimento da solução
● Uso de testes automatizados e ferramentas de qualidade


● Logs da aplicação
org.slf4j.Logger

● Iremos executar a aplicação para testá-la, cuide com qualquer dependência externa e deixe claro caso haja instruções especiais para execução do mesmo
COLACAR AI OS MANIFESTOS DO K8S