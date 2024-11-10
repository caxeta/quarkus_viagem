# QuarkusAgViagem
Confraria Dev Quarkus Project

Mono2Micro Masterclass
Descrição do Projeto:
Este projeto derivado do projeto explora a transição de uma arquitetura monolítica para uma baseada em microsserviços usando o framework Quarkus. Ele faz parte de uma masterclass de Elder Moraes sobre arquitetura de software, abordando as principais vantagens, desafios e técnicas envolvidos na migração de um monolito para microsserviços.

Índice
Introdução/Objetivo do Projeto
Pré-requisitos
Instalação e Configuração
Estrutura do Projeto
Exemplos de Uso
Fluxo da Transição
Contribuição
Licença
Links Úteis e Referências
Contato e Suporte
1. Introdução/Objetivo do Projeto
Este projeto foi desenvolvido como um exercício prático de migração arquitetural para a masterclass "Mono2Micro". O objetivo é demonstrar como uma aplicação monolítica pode ser decomposta em microsserviços, abordando:

Motivação: Aumentar a escalabilidade, modularidade e facilitar a manutenção do código.
Relevância: Muitos sistemas monolíticos enfrentam dificuldades de crescimento e flexibilidade. A transição para microsserviços permite uma maior independência e adaptabilidade das partes do sistema.

2. Pré-requisitos
Para executar este projeto, você precisará:

* Java JDK: Versão 11 ou superior;
* Quarkus CLI (opcional, mas recomendada);
* Maven: para gerenciamento de dependências;
* Docker (opcional, para execução de containers de serviços auxiliares);
* Postman ou curl: para testes de API; e
* Git: para clonar o repositório.
  
3. Instalação e Configuração
Clone o repositório:

bash
Copiar código
git clone xpto
Navegue para o diretório do projeto:

bash
Copiar código
cd xpto
Compile o projeto e inicie o servidor de desenvolvimento:

bash
Copiar código
./mvnw compile quarkus:dev
Verifique se o servidor está em execução acessando http://localhost:8080.

Para utilizar o modo microsserviços, siga as instruções no diretório específico (microservices) para iniciar cada serviço individualmente.

4. Estrutura do Projeto
O projeto está dividido em duas partes principais:

monolithic: Contém a versão inicial do projeto em arquitetura monolítica. Neste diretório, você encontrará todos os módulos e funcionalidades implementadas em um único projeto.

microservices: Contém a versão migrada do projeto para microsserviços. Cada módulo foi separado em um serviço independente com sua própria configuração e dependências.

5. Exemplos de Uso
Para interagir com o projeto, você pode usar as seguintes chamadas de API:

Obter lista de produtos
GET /api/products

Exemplo de uso com curl:

bash
Copiar código
curl -X GET http://localhost:8080/api/products
Adicionar um novo produto
POST /api/products

Exemplo de uso:

bash
Copiar código
curl -X POST http://localhost:8080/api/products -H "Content-Type: application/json" -d '{"name":"Produto Teste","price":100.0}'
Consulte a documentação de cada serviço (disponível no diretório docs) para ver todos os endpoints e parâmetros disponíveis.

6. Fluxo da Transição
A transição de arquitetura foi realizada em etapas:

Identificação de Domínios: Identificar partes da aplicação monolítica que poderiam ser isoladas em serviços independentes.
Separação de Dados e Funcionalidades: Dividir a lógica de negócio e reorganizar o banco de dados para acomodar uma arquitetura distribuída.
Implementação de Comunicação entre Serviços: Uso de APIs RESTful para comunicação entre microsserviços.
Orquestração e Monitoramento: Implementação de ferramentas para garantir a integridade da comunicação entre os serviços.
Os detalhes de cada etapa estão documentados no diretório docs para consulta.

7. Contribuição
Contribuições são bem-vindas! Para contribuir:

Faça um fork do repositório.
Crie uma nova branch para suas modificações (git checkout -b nome-da-branch).
Faça o commit das suas alterações (git commit -m 'Descrição das alterações').
Envie para a branch principal (git push origin nome-da-branch).
Abra uma Pull Request para revisão.

8. Licença
Este projeto é licenciado sob a Licença Apache 2.0. Consulte o arquivo LICENSE para mais detalhes.

text
Copiar código
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

9. Links Úteis e Referências
Documentação do Quarkus
Artigos sobre Arquitetura de Microsserviços
Exemplo de API RESTful com Quarkus
Curso Mono2Micro no GitHub

11. Contato e Suporte
Se você tiver dúvidas, sugestões ou quiser entrar em contato, envie um e-mail para analista.caxeta@gmail.com ou abra uma issue diretamente no repositório.

Esse README fornece uma visão completa, com informações detalhadas para qualquer colaborador ou usuário que queira entender e usar o projeto.






