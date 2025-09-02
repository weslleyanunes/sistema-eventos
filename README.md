# Sistema de Eventos

Este projeto é um sistema de cadastro e gerenciamento de eventos desenvolvido em **Java 21**, rodando em **console (CLI)**.

Ele permite o cadastro de usuários e eventos, participação em eventos, cancelamento de participação e consulta de
eventos já ocorridos ou em andamento.  
O projeto segue o paradigma **Orientado a Objetos** e foi organizado em pacotes seguindo convenções de boas práticas.

---

## Tabela de Conteúdos

- [Projeto](#projeto)
- [Estrutura](#estrutura)
- [Pacotes](#pacotes)
- [Aprendizados](#aprendizados)
- [Executar](#executar)
- [Futuramente](#futuramente)
- [License](#license)

---

## Projeto

### Usuários

- Cadastro com **nome, CPF, telefone e email**.
- Consulta de todos os usuários cadastrados.
- Edição/atualização de usuários existentes.

### Eventos

- Cadastro com **nome, endereço, categoria, data/hora e descrição**.
- Categorias delimitadas (ex.: Festa, Show, Esporte).
- Consulta de eventos **ordenados por data/hora**.
- Indicação de eventos **já ocorridos** ou **em andamento**.
- Edição de eventos cadastrados.

### Participação

- Usuário pode **confirmar participação** em um evento.
- Cancelar participação em eventos já confirmados.
- Consultar **“Meus eventos”** por CPF.

### Persistência

- Dados de usuários são salvos em [`data/usuarios.data`](data/usuarios.data).
- Dados de eventos são salvos em [`data/eventos.data`](data/eventos.data).
- Ao iniciar o programa, os dados são carregados automaticamente.

---

## Estrutura

```
sistema-eventos/
├── data/
│   ├── usuarios.data
│   └── eventos.data
└── src/
    └── br/com/weslleyanunes/eventoseusuarios/
        ├── Main.java
        ├── model/
        │   ├── Usuario.java
        │   └── Evento.java
        ├── service/
        │   ├── UsuarioService.java
        │   └── EventoService.java
        └── view/
            └── Menu.java
```

- [`model/`](src/br/com/weslleyanunes/eventoseusuarios/model) → entidades principais ([
  `Usuario`](src/br/com/weslleyanunes/eventoseusuarios/model/Usuario.java), [
  `Evento`](src/br/com/weslleyanunes/eventoseusuarios/model/Evento.java)).
- [`service/`](src/br/com/weslleyanunes/eventoseusuarios/service) → regras de negócio e persistência ([
  `UsuarioService`](src/br/com/weslleyanunes/eventoseusuarios/service/UsuarioService.java), [
  `EventoService`](src/br/com/weslleyanunes/eventoseusuarios/service/EventoService.java)).
- [`view/`](src/br/com/weslleyanunes/eventoseusuarios/view) → interação com o usuário pelo console ([
  `Menu`](src/br/com/weslleyanunes/eventoseusuarios/view/Menu.java)).
- [`Main.java`](src/br/com/weslleyanunes/eventoseusuarios/Main.java) → ponto de entrada do programa.

---

## Pacotes

- `java.util`
    - List, ArrayList
    - Scanner
    - Optional, Stream API (`filter`, `findFirst`, `forEach`)
- `java.io`
    - File, FileInputStream, FileOutputStream
    - ObjectInputStream, ObjectOutputStream
    - IOException
- `java.time`
    - LocalDateTime
    - DateTimeFormatter

---

## Aprendizados

Durante o desenvolvimento, foram enfrentados e resolvidos os seguintes pontos:

- **Organização de pacotes** → diferenças entre convenções no Eclipse e IntelliJ.
- **Persistência de dados** → escolha entre manter arquivos separados (`usuarios.data`, `eventos.data`).
- **Menu em console** → criação de menus intuitivos, uso de **índices numéricos** para seleção de eventos.
- **Controle de horário** → uso de `LocalDateTime` e `DateTimeFormatter` para validar, ordenar e exibir eventos.
- **Git e GitHub** → dúvidas sobre commits, `merge` vs `rebase`, exclusão de arquivos antigos e configuração de
  identidade (`user.name`, `user.email`).
- **IDE** → diferenças entre **Eclipse** e **IntelliJ**, uso de **autoformat**.
- **Java moderno** → entendimento de `record class` no Java 21 e quando usar em vez de `class`.

---

## Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/weslleyanunes/sistema-eventos.git
   ```
2. Abra no **IntelliJ IDEA** ou **Eclipse**.
3. Compile e execute a classe:
   ```bash
   src/br/com/weslleyanunes/eventoseusuarios/Main.java
   ```
4. Interaja pelo menu no console.

---

## Futuramente

- Migrar para um projeto **Maven** para melhor gerenciamento de dependências.
- Criar **testes unitários** para serviços (`UsuarioService`, `EventoService`).
- Melhorar persistência usando **JSON** ou **SQLite** no lugar de `.data`.
- Criar interface gráfica ou versão web.

---

## License

[MIT](LICENSE) © [@weslleyanunes](https://github.com/weslleyanunes)
