# Frontend - Sistema de Viagens

Este é o frontend da aplicação de gestão de viagens, desenvolvido para proporcionar uma interface amigável para reserva de passagens aéreas e hotéis.

## 🚀 Sobre

O objetivo deste painel é permitir que usuários:
- Pesquisem e reservem voos disponíveis.
- Consultem e reservem hospedagens (hotéis).
- Gerenciem seus pedidos de viagem (Travel Orders).

A aplicação conecta-se a microsserviços backend para realizar essas operações de forma integrada.

## 🛠 Tecnologias

- **React**: Biblioteca para construção da interface.
- **Vite**: Ferramenta de build e servidor de desenvolvimento rápido.
- **TypeScript**: Adiciona tipagem estática para maior segurança e manutenibilidade do código.
- **CSS**: Estilização nativa para designs customizados.
- **Axios**: Cliente HTTP para requisições às APIs.

## 📦 Como Executar

### Pré-requisitos
- Node.js (versão 20+ recomendada)
- npm

### Instalação e Execução

1.  Instale as dependências do projeto:
    ```bash
    npm install
    ```

2.  Inicie o servidor de desenvolvimento local:
    ```bash
    npm run dev
    ```
    A aplicação estará disponível geralmente em `http://localhost:5173`.

3.  Para gerar a versão de produção (build):
    ```bash
    npm run build
    ```

## 🔌 Integração com Backend

O frontend utiliza um proxy configurado no Vite (`vite.config.ts`) para redirecionar as chamadas de API para os microsserviços locais:

- **Voos**: `/api/flights` -> `http://localhost:8081`
- **Hotéis**: `/api/hotels` -> `http://localhost:8082`
- **Pedidos**: `/api/travelorder` -> `http://localhost:8083`

Isso evita problemas de CORS durante o desenvolvimento.
