# Análise Símio
Projeto que faz a análise de cadeias de DNAs, para identificar se é um DNA Símio.

## dependencias

Foi utilizado o lombok para facilitar getters, setters e builders, sendo no eclipse obrigatório configurar ele no ambiente:
[lombok](https://projectlombok.org/setup/eclipse)

O projeto é no formato maven, podendo ser importado utilizando esse modelo de projeto.

Ele utiliza um banco de dados H2 em memória para facilitar a súbida e alguns testes, pela simplicidade de gerenciar esse banco, não sendo recomendado para outros ambientes, já que ao efetuar o down da aplicação os dados são perdidos.

o projeto está configurado inicialmente para rodar na porta 8080.

##desafios
Nível 1:
Desenvolva uma API que esteja de acordo com os requisitos propostos acima, que seja capaz de validar uma
sequência de DNA e identificar corretamente símios e humanos.
Para atender o nível 1 foi criada o endpoint simian que pode ser acessado usando a URL localhost:8080/simian

Nível 2:
Use um banco de dados de sua preferência para armazenar os DNAs verificados pela API. Esse banco deve
garantir a unicidade, ou seja, apenas 1 registro por DNA.
Disponibilizar um outro endpoint "/stats" que responde um HTTP GET. A resposta deve ser um Json que
retorna as estatísticas de verificações de DNA, onde deve informar a quantidade de DNA’s símios,
quantidade de DNA’s humanos, e a proporção de símios para a população humana. Segue exemplo da
resposta:
Para atender o nível 1 foi criada o endpoint stats que pode ser acessado usando a URL localhost:8080/stats
Observação: não deve ser utilizado sem cadastrar inicialmente um DNA.

