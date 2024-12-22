
# Employee Management System

This is a simple application for employee CRUD operations.

We can add new employee record, view employee list, edit employee details and delete employee record.

The project is created in Spring Boot (Backend), My SQL (Database), Angular (Frontend).

## Running the Project

Follow these steps to set up and run the application:

1. Clone the Repository:
```bash
git clone employeeManagement
```

2. Navigate to the Project Directory:
```bash
cd ems
```

3. Navigate to the Employee Directory:
```bash
cd backend
```

4. Start the Spring Boot Server:
```bash
mvn spring-boot:run
```

The Spring Boot server is now running.

5. Navigate to the EMS Directory:
```bash
cd ..
cd frontend
```

6. Install Angular Dependencies and Start the Angular Server:
```bash
npm install
ng serve --open
```
The Angular server is now running on `localhost:4200`.

7. Set Up the MySQL Database:
Run the SQL queries in a MySQL database. Ensure the database is set up on `localhost:3306`.
