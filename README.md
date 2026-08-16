
# 📋 Projeto Final - API REST estilo Trello (Java + Spring Boot)

Este projeto consiste na construção de uma API REST para gerenciamento de quadros, grupos de tarefas e tarefas, inspirada no funcionamento do Trello. É parte do trabalho final da disciplina de **Orientação a Objetos 2**.

---

## 📌 Objetivo

O sistema simula um gerenciador de projetos estilo Trello, permitindo:

- Criar e gerenciar **Boards** (quadros de tarefas)
- Criar e gerenciar **TaskGroups** (grupos/colunas de tarefas)
- Criar e gerenciar **Tasks** (tarefas dentro dos grupos)

Os dados são armazenados em banco de dados em memória **H2**, com persistência e mapeamento utilizando **Spring Data JPA**.

---

## 🧰 Tecnologias Utilizadas

| Tecnologia | Finalidade |
|------------|------------|
| Java 21 | Linguagem principal |
| Spring Boot 3.5.0 | Framework principal |
| Spring Web | Criação de APIs REST |
| Spring Data JPA | Mapeamento objeto-relacional |
| H2 Database | Banco de dados em memória |
| Lombok | Redução de boilerplate |
| Spring Boot Validation | Validações com anotações |
| Spring Boot DevTools | Reload automático |
| Spring Boot Starter Test | Testes automatizados (JUnit) |

---

## 🔄 Relacionamento das Entidades

- **Board**
    - `id`, `name`, `description`
    - Contém uma lista de `TaskGroups`

- **TaskGroup**
    - `id`, `name`, `boardId`
    - Contém uma lista de `Tasks`

- **Task**
    - `id`, `title`, `description`, `status`, `taskGroupId`

---

## ✅ Funcionalidades Mínimas

### Boards
- [x] Criar board
- [x] Listar todos os boards
- [x] Buscar board por ID
- [x] Atualizar board
- [x] Deletar board

### TaskGroups
- [x] Criar task group vinculado a um board
- [x] Listar task groups
- [x] Buscar task group por ID
- [x] Atualizar task group
- [x] Deletar task group

### Tasks
- [x] Criar task vinculada a um task group
- [x] Listar tasks
- [x] Buscar task por ID
- [x] Atualizar task
- [x] Deletar task

---

## 🔍 Validações e Regras de Negócio

- Nome de `Board` e `TaskGroup` deve conter no mínimo 3 caracteres
- `Task` deve conter:
    - Título obrigatório
    - Status válido (`TODO`, `IN_PROGRESS`, `DONE`)
- Não é permitido criar `TaskGroup` sem `Board`
- Não é permitido criar `Task` sem `TaskGroup`

---

## 🧪 Testes

O projeto deve conter:

- [x] Pelo menos 1 teste de unidade (em `service`)
- [x] Pelo menos 1 teste de integração (em `controller`, com `@SpringBootTest`)

---

## ▶️ Como Executar o Projeto

### Pré-requisitos
- Java 21 instalado
- Maven 3.6.3 ou superior

### Passos

```bash
# 1. Clone o repositório
git clone https://github.com/ViniciusDuranteBagio/ApiTrello
cd ApiTrello

# 2. Execute o projeto
mvn clean install
mvn spring-boot:run
```

### Acesse:
- **API REST**: http://localhost:8080

---

## 🔁 Exemplos de Endpoints

### Criar um Board
POST /boards
``
{
  "name": "Projeto Final",
  "description": "Projeto da disciplina OO2"
}
``

### Criar um TaskGroup
POST /task-groups
``
{
  "name": "A Fazer",
  "boardId": 1
}
``

### Criar uma Task
POST /tasks
``
{
  "title": "Implementar DTOs",
  "description": "Usar DTOs nas respostas da API",
  "status": "TODO",
  "taskGroupId": 1
}
``

---

## 🧑‍🏫 Avaliação

| Critério | Pontos |
|---------|--------|
| Funcionalidade dos Endpoints | 3 pts |
| Modelagem correta das entidades e relacionamentos | 2 pts |
| Uso correto do Spring Boot e suas anotações | 1 pts |
| Validações e regras de negócio | 2 pts |
| Estrutura e organização do projeto | 1 pts |
| Testes automatizados | 1 pts |

---

## 📅 Prazo de Entrega

**27/06/2025**

---

## 💡 Dica

Utilize ferramentas como **Postman** ou **Insomnia** para testar os endpoints da API de forma simples.
