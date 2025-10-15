# MongoDB integration quickstart

## 1) Add connection string
Set an environment variable before running:
```bash
set SPRING_DATA_MONGODB_URI=mongodb://localhost:27017/emaildb   # Windows PowerShell: $env:SPRING_DATA_MONGODB_URI="mongodb://localhost:27017/emaildb"
```
Or for MongoDB Atlas, use the SRV URI:
```bash
set SPRING_DATA_MONGODB_URI="mongodb+srv://<user>:<pass>@<cluster>.mongodb.net/emaildb?retryWrites=true&w=majority"
```

## 2) Run the app
```bash
mvn spring-boot:run
```

## 3) Test endpoints
- Send an email (and persist it):
  ```http
  POST /api/email/send
  Content-Type: application/json

  {
    "from": "tu@correo.com",
    "to": "destinatario@correo.com",
    "subject": "Hola Mongo",
    "text": "<b>probando</b>"
  }
  ```
- List stored emails:
  ```http
  GET /api/email
  ```
- Get by id:
  ```http
  GET /api/email/{id}
  ```

## Notes
- @CreatedDate is enabled via `@EnableMongoAuditing` and will fill `createdAt` automatically.
- Move secrets (SMTP password, Mongo URI) to environment variables for safety.
- Default DB name: `emaildb` (from the URI). Change as needed.
