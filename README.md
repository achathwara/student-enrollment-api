# Student Enrollment Management API

A RESTful backend service built with Spring Boot for managing students, courses, and enrollments.

## 🚀 Tech Stack
* **Java 17+**
* **Spring Boot 3.x**
* **Spring Data JPA**
* **H2 Database** (In-memory for easy testing)
* **Spring Security** (Basic Auth)
* **Lombok**

## 🔒 Authentication
To protect data integrity, all **POST**, **PUT**, and **DELETE** endpoints require Basic Authentication.

* **Username:** `admin`
* **Password:** `admin123`

*Note: GET requests are public and do not require authentication.*

---

## 📁 API Endpoints

### 1. Student Module
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| GET | `/students?page=0&size=10&search=john&is_active=true` | List students (Paginated & Filtered) |
| POST | `/students` | Create a new student (Auth Required) |
| GET | `/students/{id}` | Get student by UUID |
| PUT | `/students/{id}` | Update student details (Auth Required) |
| DELETE | `/students/{id}` | Soft delete student (sets `isActive=false`) |

### 2. Course Module
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/courses` | Create a new course (Auth Required) |
| GET | `/courses` | List all courses |
| GET | `/courses/{id}` | Get course by ID |
| PUT | `/courses/{id}` | Update course details (Auth Required) |
| DELETE | `/courses/{id}` | Delete a course |

### 3. Enrollment Module
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/students/{studentId}/enroll/{courseId}` | Enroll a student in a course (Auth Required) |
| GET | `/students/{studentId}/courses` | View all courses for a specific student |

---
## 🛠️ Setup & Installation
1. **Clone the repository:**
   ```bash
   git clone <your-repo-url>
   cd enrollment-api
2. **Build the project:**
   ```bash
   ./mvnw clean install
3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run

The server will start at http://localhost:8080.

## 🧪 Testing
**The project includes JUnit 5 test cases for the Service layer. Run them using:**
   ```bash
   ./mvnw test


