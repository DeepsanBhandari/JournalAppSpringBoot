# 📓 Journal REST API - Spring Boot & MongoDB

> Secure journaling backend with mood tracking and encrypted entries

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![MongoDB](https://img.shields.io/badge/MongoDB-7.0-blue)
![JWT](https://img.shields.io/badge/Security-JWT-orange)

## 🚀 Quick Start

```bash
# Clone & run
git clone https://github.com/DeepsanBhandari/journal-api.git
cd journal-api

# Run with Docker
docker-compose up -d

# Or run locally
mvn spring-boot:run
```

**API Base URL:** `http://localhost:8080/api`

## 📡 API Endpoints

### 🔐 Authentication
```
POST   /api/auth/register  # Create account
POST   /api/auth/login     # Get JWT token
POST   /api/auth/refresh   # Refresh token
```

### 📝 Journal Entries
```
POST   /api/entries        # Create encrypted entry
GET    /api/entries        # List entries (paginated)
GET    /api/entries/{id}   # Get specific entry
PUT    /api/entries/{id}   # Update entry
DELETE /api/entries/{id}   # Delete entry
```

### 🔍 Search & Analytics
```
GET    /api/entries/search?q={query}     # Full-text search
GET    /api/entries/by-date?date={date}  # Filter by date
GET    /api/entries/by-mood?mood={mood} # Filter by mood
GET    /api/analytics/mood-timeline      # Mood trends
GET    /api/analytics/writing-stats      # Writing habits
```

### 📤 Export
```
GET    /api/export/pdf/{entryId}     # Export to PDF
GET    /api/export/json              # Backup all entries
```

## 🛠️ Tech Stack

- **Spring Boot 3.x** - REST API framework
- **MongoDB** - NoSQL database with encryption
- **Spring Security + JWT** - Authentication
- **Spring Data MongoDB** - Data access
- **iText PDF** - PDF generation
- **Docker** - Containerization

## 🔒 Security Features

- AES-256 entry encryption at application layer
- JWT tokens with refresh mechanism
- Password hashing with BCrypt
- Rate limiting on authentication endpoints
- Input validation and sanitization

## 📊 Database Schema

```json
{
  "users": {
    "_id": "ObjectId",
    "username": "string",
    "email": "string",
    "password": "hashed",
    "createdAt": "timestamp"
  },
  "entries": {
    "_id": "ObjectId",
    "userId": "ObjectId",
    "encryptedContent": "string",
    "mood": "string",
    "tags": ["string"],
    "createdAt": "timestamp",
    "updatedAt": "timestamp"
  }
}
```

## 🐳 Docker Setup

```yaml
version: '3.8'
services:
  mongodb:
    image: mongo:7
    ports:
      - "27017:27017"
    volumes:
      - mongodb_data:/data/db
  
  journal-api:
    build: .
    ports:
      - "8080:8080"
    environment:
      - MONGO_URI=mongodb://mongodb:27017/journaldb
      - JWT_SECRET=your-secret-key
    depends_on:
      - mongodb


**A simple, secure backend for your thoughts**  
*No frontend, no complexity - just a clean API for journaling*
