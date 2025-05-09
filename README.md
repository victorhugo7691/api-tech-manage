# TechManage API

API RESTful desenvolvida com **Spring Boot 3** e **Java 17** para gerenciamento de usuários. 
Este projeto faz parte de um desafio técnico para realizar o desenvolvimento de aplicação back-end. Através da aplicação é possível realizar operações CRUD completas sobre uma entidade `User`.

---

## Sobre a Aplicação

A **TechManage** é uma API que possibilita:
- ✅ Criar um novo usuário
- ✅ Buscar todos os usuários
- ✅ Buscar um usuário por ID
- ✅ Atualizar dados de um usuário
- ✅ Excluir um usuário

Com validações via **Bean Validation** e persistência em **PostgreSQL** usando **Spring Data JPA**.

---

## Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Maven
- JUnit 5 / Mockito
- Docker

---

## Estrutura de Endpoints

| Método | Endpoint               | Descrição                         |
|:--------|:----------------------|:----------------------------------|
| `POST`   | `/api/users`         | Cadastrar novo usuário             |
| `GET`    | `/api/users`         | Listar todos os usuários           |
| `GET`    | `/api/users/{id}`    | Buscar usuário por ID              |
| `PUT`    | `/api/users/{id}`    | Atualizar dados do usuário         |
| `DELETE` | `/api/users/{id}`    | Remover usuário                    |

---

## Como rodar o projeto localmente

### Pré-requisitos

- Java 17 instalado
- Maven 3.9.x
- PostgreSQL (Está disponível na núvem)

A seguir estão as credenciais de acesso ao banco de dados:

```bash
DB_HOST= dpg-d0daovjuibrs73f535og-a.oregon-postgres.render.com
DB_NAME= db_tech_manage 
DB_PASSWORD= X9t4KiunzxOBAPtZ8r634jyxuWnf2bh2 
DB_PORT= 5432 
DB_USER= app_tech_manage
```

Os scripts de criação do banco de dados estão disponíveis no seguinte repositório:
https://github.com/victorhugo7691/db-tech-manage

### Clone o projeto

```bash

git clone https://github.com/victorhugo7691/api-tech-manage.git
cd api-tech-manage

```
Dentro da pasta principal sugiro executar os seguintes comandos:

```bash

mvn clean
mvn install

```
```bash
DB_HOST=dpg-d0daovjuibrs73f535og-a.oregon-postgres.render.com DB_NAME=db_tech_manage DB_PASSWORD=X9t4KiunzxOBAPtZ8r634jyxuWnf2bh2 DB_PORT=5432 DB_USER=app_tech_manage mvn spring-boot:run
```

A API também está disponível em um ambiente de núvem, também é possível realizar chamadas através da url:
https://api-tech-manage.onrender.com/api/users

Estou utilizando servidores gratuitos, o serviço pode demorar responder e ou parar de funcionar em alguns momentos.

As collections para chamada via Postman estão disponíveis em https://drive.google.com/drive/folders/18K5f0wF07xuH4JobQq8JK2Kd0S68X4yb?usp=sharing

Formato do Body JSON

```bash
{
    "fullName":"Kaua Teixeira",
    "email": "kauateixeira@email.com",
    "phone": "+55 89 9960-8769",
    "birthDate": "27/06/1994",
    "userType": "VIEWER"
}
```

Este foi um projeto legal em desenvolver, que possibilita utilizar os conhecimentos de programação, banco de dados, técnicas de desenvolvimento e diversos outros conhecimentos.
