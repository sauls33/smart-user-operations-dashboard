Smart User Operations Dashboard

AI-powered full-stack dashboard for monitoring users, operational risk, and smart activity insights.

This project evolves a CRUD application into a capstone-style product using Spring Boot, Angular, and GitHub Codespaces.

---

Product Mission

Smart User Operations Dashboard helps teams manage internal users, monitor operational risk, and generate intelligent recommendations based on user activity.

---

Tech Stack

Backend:
- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database

Frontend:
- Angular
- TypeScript
- Angular Signals
- Angular @defer

Workflow:
- GitHub Codespaces
- Dev Containers

AI Feature:
- Smart summary generation
- Risk analysis
- Recommendation engine

---

Run Backend

cd spring/user-management-api
mvn spring-boot:run

---

Run Frontend

cd angular/user-management-ui
npm install
npm start

---

API Endpoints

GET    /api/users
POST   /api/users
PUT    /api/users/{id}
DELETE /api/users/{id}

GET    /api/ai/user-summary
POST   /api/ai/user-summary

---

Demo Flow

1. Start backend
2. Start frontend
3. Create user
4. Edit user
5. Delete user
6. Generate AI summary

---

Capstone Value

- Full-stack architecture
- Cloud-ready environment
- AI-inspired insights
- Product-focused design
