# Employee Management System

It is a full-stack Employee Management System designed to make handling employee data a breeze. The backend is powered by Spring Boot (Java), while the frontend delivers a modern experience using Vue 3 + Vite.

## Features
- Employee CRUD (Create, Read, Update, Delete)
- Position management
- RESTful API (Spring Boot)
- Modern UI with Vue 3, Bootstrap, and Vue Router

---

## Project Structure
```
employee-management/
├── HELP.md                # Spring Boot help and documentation
├── pom.xml                # Maven configuration for backend
├── src/                   # Backend source code (Java)
│   └── main/
│       ├── java/com/refi/employee_management/
│       │   ├── controller/    # REST controllers
│       │   ├── model/         # JPA entities
│       │   ├── repository/    # Spring Data JPA repositories
│       │   └── service/       # Business logic
│       └── resources/         # Application properties
├── fe/                    # Frontend (Vue 3 + Vite)
│   ├── package.json        # Frontend dependencies
│   ├── src/                # Vue components, views, router
│   └── public/             # Static assets
└── ...
```

---

## Backend (Spring Boot)
- Java 17, Spring Boot 3.x
- Data stored in PostgreSQL (see `src/main/resources/application.properties`)
- Main entry: `EmployeeManagementApplication.java`
- API endpoints:
  - `/employees` (CRUD for employees)
  - `/positions` (list positions)

### Running Backend
1. Ensure PostgreSQL is running and update DB credentials in `src/main/resources/application.properties`.
2. From the project root, run:
   ```sh
   ./mvnw spring-boot:run
   ```
   or on Windows:
   ```sh
   mvnw.cmd spring-boot:run
   ```
3. Backend runs on [http://localhost:3001](http://localhost:3001)

---

## Frontend (Vue 3 + Vite)
- Located in the `fe/` directory
- Uses Vue 3, Bootstrap 5, Vue Router, Axios

### Running Frontend
1. Open a terminal in the `fe/` directory:
   ```sh
   cd fe
   npm install
   npm run dev
   ```
2. Frontend runs on [http://localhost:5173](http://localhost:5173) by default

---

## Usage
- Access the frontend in your browser.
- The frontend communicates with the backend API at `http://localhost:3001`.
- You can add, edit, and delete employees, and view positions.

---

## License
This project is for educational/demo purposes.
