# 📚 Library Loan API

API RESTful built with **Spring Boot** for managing a library system, including book registration, user management, and loan/devolution control.

---

## 🚀 Features

- 📖 Register, update and delete books
- 👤 Register and list users
- 📦 Create and manage book loans
- ✅ Mark loans as returned
- 🔍 Error handling with custom exceptions
- 🧱 Layered architecture (Controller, Service, Repository)

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database (dev)
- Maven
- Swagger/OpenAPI

---

## 📁 Project Structure
````bash
src/
├── controller/
├── dto/
├── model/
├── repository/
├── service/
└── exception/
````

---

## ⚙️ Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/cleosilva/library-spring-boot.git
cd library-spring-boot
```
```bash
2. Build the project
./mvnw clean install
```
````bash
3. Run the application
./mvnw spring-boot:run
````

The API will be available at:
http://localhost:8080

🔄 Endpoints Overview 
📘 Books

GET /book – List all books

POST /book – Register a new book

PUT /book/{id} – Update a book

DELETE /book/{id} – Delete a book

👥 Users
GET /users – List all users

POST /users – Register a user

📦 Loans
POST /loan – Create a new loan

PUT /loan/{id}/return – Return a loaned book

GET /loan – List all loans

📄 Swagger Documentation
http://localhost:8080/swagger-ui.html

❗ Error Handling

Custom exceptions with clear HTTP responses:

LivroIndisponivelException → 400 Bad Request

UsuarioNaoEncontradoException → 404 Not Found

Global handler for generic errors

✅ Future Improvements

🔐 Add authentication (Spring Security + JWT)

🧪 Add unit/integration tests

👩‍💻 Author
Cleo Silva
Java & Node.js Developer
LinkedIn https://www.linkedin.com/in/cleo-silva/

📝 License

This project is licensed under the MIT License.

---
