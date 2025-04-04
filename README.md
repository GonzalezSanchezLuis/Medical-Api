<h1>Api para gestión de citas medicas</h1>
<hr>
<p>This API connects users with doctors, facilitating efficient appointment management. Developed with Java and Spring Boot, it implements JWT authentication and role-based access control, following the MVC pattern.</p>

<h2> 🚀 Technologies used</h2>

- Java 8
- Spring Boot
- Spring MVC
- Spring Security (con JWT)
- MySQL
- Lombok
- Swagger/OpenAPI
- Environment variables (for sensitive configuration)


<h2>🛠️ Local installation and execution</h2>

- Java 8
- Maven
- MySQL
- Git

<h2>🧾 Clonar el repositorio</h2>

```bash
https://github.com/GonzalezSanchezLuis/Medical-Api
```

<h2>⚙️ Environment Configuration</h2>

Create a .env file in the project root with the following environment variables.
```bash
DB_URL=jdbc:mysql://localhost:3306/medical_api
DB_USERNAME=your_user
DB_PASSWORD=your_password
```

## 📚 Main Endpoints

The API is fully documented with Swagger at:  
🔗 `http://localhost:8080/swagger-ui/index.html`

Below are some of the main endpoints:

### 🔐 Authentication
| Method | Endpoint                | Description                                   |
|--------|-------------------------|-----------------------------------------------|
| POST   | `/api/v1/auth/login`    | Log in and receive a JWT                      |
| POST   | `/api/v1/auth/register` | Register a new user                           |
| POST   | `/api/v1/auth/logout`   | To log out of the system                      |

### 👤 Users
| Method | Endpoint             | Description                                        |
|--------|----------------------|----------------------------------------------------|
| GET    | `/api/v1/current`    | Get the currently authenticated user               |
| GET    | `/api/v1/users/{id}` | List all users (admin only)    register            |

### 🩺 Doctors
| Method | Endpoint                   | Description                                    |
|--------|----------------------------|------------------------------------------------|
| POST   | `/api/v1/doctors/register` | Register a new doctor                          |
| GET    | `/api/v1/doctors/doctors`  | all registered doctors                         |

### 📅 Appointments
| Method | Endpoint                        | Description                                |
|--------|---------------------------------|--------------------------------------------|
| POST   | `/api/V1/appointments/register` | View current user's appointments           |
| GET    | `/api/v1/appointments/all`      | displays all recorded medical appointments |



