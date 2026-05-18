# RecipeBook
Projeto de gerenciamento de receitas culinarias desenvolvido para a atividade de Construcao de Software e GCS.

O sistema permite listar, buscar, cadastrar, visualizar e excluir receitas. O backend fornece a API REST e o frontend consome essa API em uma interface web.

## Tecnologias
- Java 17
- Spring Boot
- H2 Database
- Angular 17
- GitHub Actions

## Como executar

### Backend
Na pasta do projeto, entre no backend:

```powershell
cd backend
```

Se o Maven estiver instalado na maquina:

```powershell
mvn spring-boot:run
```

Ou usando o Maven Wrapper:

```powershell
.\mvnw.cmd spring-boot:run
```

Acesse: http://localhost:8080

### Frontend
Em outro terminal, entre na pasta do frontend:

```powershell
cd frontend
npm.cmd install --no-package-lock
npm.cmd start
```

Acesse: http://localhost:4200

## Dupla
- Caio Caetano Marques
- Pedro Lucas Borges Barbosa
