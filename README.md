#  Shop API - Spring Boot E-Commerce Backend

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)
![Java](https://img.shields.io/badge/Java-17-blue.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

Eine vollständige RESTful E-Commerce API entwickelt mit Spring Boot, die alle wesentlichen Funktionen eines Online-Shops bereitstellt. Die API bietet Endpunkte für Produktverwaltung, Warenkörbe, Benutzer und Bestellungen mit einer modernen, skalierbaren Architektur.

##  Features

- **Produktverwaltung** - CRUD-Operationen für Produkte mit Katalogfunktionen
- **Warenkorb** - Persistenter Warenkorb mit Artikelverwaltung und Preisberechnung
- **Benutzerverwaltung** - Registrierung, Authentifizierung und Profilverwaltung
- **Bestellabwicklung** - Vom Warenkorb zur Bestellung mit Statusverfolgung
- **Datenvalidierung** - Umfassende Validierung auf DTO-Ebene
- **Fehlerbehandlung** - Konsistente Fehlerantworten und Statuscodes


##  Technologie-Stack

| Technologie | Version | Zweck |
|-------------|---------|-------|
| **Java** | 17 | Hauptprogrammiersprache |
| **Spring Boot** | 3.x | Framework für REST-APIs |
| **Spring Data JPA** | 3.x | Datenbankzugriff und ORM |
| **MySQL** | 8.x | Produktionsdatenbank |
| **Lombok** | 1.18.x | Reduzierung von Boilerplate Code |
| **Jakarta Validation** | 3.x | Datenvalidierung |
| **Maven** | 3.8+ | Build-Automatisierung |


##  Projektstruktur

```
shop-api/
├── src/main/java/com/amjad/shopapi/
│   ├── benutzer/                     # Benutzermanagement
│   │   ├── controller/               # BenutzerController.java
│   │   ├── service/                  # Business-Logik
│   │   ├── repository/               # Datenbank-Zugriff
│   │   ├── dto/                      # Data Transfer Objects
│   │   │   ├── BenutzerAnfrage.java  # Request DTO
│   │   │   └── BenutzerAntwort.java  # Response DTO
│   │   ├── mapper/                   # Mapping zwischen Entity/DTO
│   │   └── modell/                   # Entity-Klassen
│   ├── produkt/                      # Produktmanagement
│   ├── warenkorb/                    # Warenkorb-System
│   ├── bestellung/                   # Bestellverwaltung
│   ├── sicherheit/                   # Authentifizierung & Autorisierung
│   └── ShopApiApplication.java       # Hauptklasse
├── src/main/resources/
│   ├── application.properties        # Konfiguration
│
│  
└── pom.xml                           # Maven Konfiguration
```

##  Schnellstart

### Voraussetzungen

- **Java 17** oder höher
- **Maven 3.8+**
- **Git**

### Installation

1. **Repository klonen**
```bash
git clone https://github.com/amjad/shop-api.git
cd shop-api
```

2. **Mit H2-Datenbank starten (Entwicklung)**
```bash
mvn spring-boot:run
```


4. **Anwendung testen**
```bash
curl http://localhost:8080/api/products
```

##  Konfiguration

### Anwendungseigenschaften (`application.properties`)

```properties
# Server-Konfiguration
server.port=8080
server.servlet.context-path=/api

# Datenbank (H2 für Entwicklung)
spring.datasource.url=jdbc:h2:mem:shopdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# H2 Console (nur für Entwicklung)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Logging
logging.level.com.amjad.shopapi=DEBUG
logging.level.org.springframework.web=INFO
```

##  API Dokumentation

### Base URL
```
http://localhost:8080/api
```

### Authentifizierung
*Hinweis: Authentifizierung ist in Entwicklung*

### API Endpoints

####  **Produkte** (`/products`)

| Endpoint | Methode | Beschreibung | Authentifizierung |
|----------|---------|-------------|-------------------|
| `/products` | `GET` | Alle Produkte abrufen | Optional |
| `/products/{id}` | `GET` | Einzelnes Produkt abrufen | Optional |
| `/products` | `POST` | Neues Produkt erstellen | Erforderlich |
| `/products/{id}` | `PUT` | Produkt aktualisieren | Erforderlich |
| `/products/{id}` | `DELETE` | Produkt löschen | Erforderlich |



####  **Warenkorb** (`/carts`)

| Endpoint | Methode | Beschreibung | Authentifizierung |
|----------|---------|-------------|-------------------|
| `/carts` | `POST` | Warenkorb erstellen | Erforderlich |
| `/carts/{id}` | `GET` | Warenkorb abrufen | Erforderlich |
| `/carts/user/{userId}` | `GET` | Warenkorb nach User ID | Erforderlich |
| `/carts/{id}` | `PUT` | Warenkorb aktualisieren | Erforderlich |
| `/carts/{cartId}/items/{productId}` | `DELETE` | Artikel entfernen | Erforderlich |
| `/carts/{id}` | `DELETE` | Warenkorb löschen | Erforderlich |



####  **Benutzer** (`/users`)

| Endpoint | Methode | Beschreibung | Authentifizierung |
|----------|---------|-------------|-------------------|
| `/users` | `POST` | Benutzer registrieren | Optional |
| `/users` | `GET` | Alle Benutzer (Admin) | Erforderlich (Admin) |
| `/users/{id}` | `GET` | Benutzerprofil | Erforderlich |
| `/users/{id}` | `PUT` | Profil aktualisieren | Erforderlich |
| `/users/{id}` | `DELETE` | Account löschen | Erforderlich |

####  **Bestellungen** (`/orders`)

| Endpoint | Methode | Beschreibung | Authentifizierung |
|----------|---------|-------------|-------------------|
| `/orders` | `POST` | Bestellung aufgeben | Erforderlich |
| `/orders` | `GET` | Bestellungen abrufen | Erforderlich |
| `/orders/{id}` | `GET` | Bestelldetails | Erforderlich |
| `/orders/{id}/status` | `PATCH` | Status aktualisieren | Erforderlich (Admin) |

## ️ Datenbankschema

### Entity-Beziehungen

```sql
-- Produkte
CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL CHECK (price > 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Benutzer
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Warenkörbe
CREATE TABLE carts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Warenkorbpositionen
CREATE TABLE cart_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cart_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Bestellungen
CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_number VARCHAR(50) UNIQUE NOT NULL,
    user_id BIGINT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    shipping_address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Bestellpositionen
CREATE TABLE order_items (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```


### Validation Error (400)
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validierungsfehler",
  "errors": [
    {
      "field": "price",
      "message": "Preis muss positiv sein"
    }
  ],
  "path": "/api/products"
}
```

### Resource Not Found (404)
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Produkt nicht gefunden mit ID: 999",
  "path": "/api/products/999"
}
```

### Internal Server Error (500)
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "Ein unerwarteter Fehler ist aufgetreten",
  "path": "/api/products"
}
```

##  Entwicklung

### Setup Entwicklungsumgebung

1. **IDE einrichten**
    - IntelliJ IDEA oder VS Code mit Java-Erweiterungen
    - Lombok Plugin installieren

2. **Projekt importieren**
```bash
mvn clean install
```

3. **Code Style**
```bash
mvn spotless:apply
```


##  Beitragen

Beiträge sind willkommen! Bitte folge diesen Schritten:

1. Fork das Repository
2. Erstelle einen Feature-Branch (`git checkout -b feature/AmazingFeature`)
3. Committe deine Änderungen (`git commit -m 'Add some AmazingFeature'`)
4. Push zum Branch (`git push origin feature/AmazingFeature`)
5. Öffne einen Pull Request



##  Sicherheit

### Empfohlene Ergänzungen
- JWT-basierte Authentifizierung
- OAuth2.0 Integration
- API-Key Management
- Audit-Logging
- Input-Validierung
- SQL-Injection Protection
- XSS Protection
- CSRF Protection
- Rate Limiting (in Planung)

##  Weitere Ressourcen

### Dokumentation
- [Spring Boot Dokumentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [REST API Best Practices](https://restfulapi.net/)

### Tools
- [Postman Collection](/postman/shop-api.postman_collection.json)
- [Swagger UI](http://localhost:8080/swagger-ui.html) (in Planung)
- [H2 Console](http://localhost:8080/h2-console)

##  Kontakt

**Amjad Ibrahim** - [Email](amjad.ibrahim0909@gmail.com)

---




