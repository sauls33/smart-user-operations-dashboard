# Project1 Fullstack CRUD

Monorepo for the AI-Augmented Full-Stack Engineering training through Phase 4.

This version unifies the previous work into one cohesive application:

- Angular frontend: `user-management-ui`
- Spring Boot backend: `user-management-api`
- Dev Container for Java 21, Node 24, Maven, Angular, and GitHub CLI

## Structure

```text
project1-fullstack-crud/
├── angular/
│   └── user-management-ui/
├── spring/
│   └── user-management-api/
└── .devcontainer/
    └── devcontainer.json
```

## Features Covered

### Phase 2 - Angular Architecture & Deferrable Views

The Angular UI uses:

- Standalone components
- Component-based architecture
- TypeScript, HTML, CSS
- Services for backend communication
- Angular control flow: `@if`, `@for`
- Angular `@defer` for the user stats summary component

### Phase 3 - Full-Stack Nexus

The app includes a complete User Management CRUD:

- Spring Boot REST API
- REST Controller
- Service layer
- JPA Repository
- H2 in-memory database
- Validation
- Angular UI connected to the backend through `HttpClient`

### Phase 4 - Cloud-Native Workflow

The repo includes a `.devcontainer/devcontainer.json` file for GitHub Codespaces or VS Code Dev Containers.

## Runtime Versions

Inside the Dev Container, validate:

```bash
java -version
node -v
npm -v
mvn -v
gh --version
```

Expected:

```text
Java 21
Node 24.x
npm 11.x or compatible
Maven installed
GitHub CLI installed
```

## Run Spring Boot Backend

From the repository root:

```bash
cd spring/user-management-api
mvn spring-boot:run
```

Spring Boot runs on:

```text
http://localhost:8080
```

Useful backend URLs:

```text
http://localhost:8080/
http://localhost:8080/api/users
http://localhost:8080/h2-console
```

H2 credentials:

```text
JDBC URL: jdbc:h2:mem:usersdb
User Name: sa
Password: <empty>
```

## Run Angular Frontend

Open a second terminal from the repository root:

```bash
cd angular/user-management-ui
npm install
npm start
```

Angular runs on:

```text
http://localhost:4200
```

## Suggested Test Flow

1. Start Spring Boot on port `8080`.
2. Open `http://localhost:8080/api/users` and confirm JSON is returned.
3. Start Angular on port `4200`.
4. Open `http://localhost:4200`.
5. Create a user.
6. Edit a user.
7. Delete a user.
8. Confirm the deferred stats block updates.

## Codespaces Validation

After opening the repo in Codespaces:

```bash
java -version
node -v
mvn -v
```

Then run backend and frontend in separate terminals:

```bash
cd spring/user-management-api
mvn spring-boot:run
```

```bash
cd angular/user-management-ui
npm start
```

Use the Codespaces **Ports** tab to open ports `4200` and `8080`.

## API Endpoints

```text
GET    /api/users
GET    /api/users/{id}
POST   /api/users
PUT    /api/users/{id}
DELETE /api/users/{id}
```

Example POST body:

```json
{
  "name": "Sofia Morales",
  "email": "sofia@example.com",
  "active": true
}
```

## GitHub CLI Commands

```bash
gh auth status
gh repo view
gh pr create
```

## Phase 4 Checklist

```text
[ ] Dev Container starts successfully
[ ] Java 21 is available
[ ] Node 24 is available
[ ] Maven is available
[ ] GitHub CLI is available
[ ] Spring Boot runs on port 8080
[ ] Angular runs on port 4200
[ ] Angular UI can create, edit, and delete users through the backend
[ ] Deferred stats component loads in the UI
```
