# Bank Server for Self-Checkout Simulation

This repository is part of a project that simulates a shop self-checkout system. It provides the backend services for handling payments, including card and BLIK transactions.

---

## Key Features

- **Payment Processing**: Supports card and BLIK payment handling with validation.
- **Database Integration**: Utilizes PostgreSQL for storing and managing account, card, and transaction data.
- **REST API**: Exposes endpoints for managing clients, accounts, and transactions.

---

## How to Run

### Prerequisites
1. **Java**: Install JDK 18 or higher.
2. **Database**: Set up a PostgreSQL database.
3. **Build Tool**: Ensure Maven is installed.

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/milosz-20/projektbank.git
   cd projektbank
   ```

2. Configure the database:
   - Update `src/main/resources/application.properties` with your database credentials.

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

---

## How It Works

1. **Main Application**: The `BankApplication` class initializes the Spring Boot app.
2. **REST Controllers**:
   - `KlientController`: Manages client-related operations.
   - `KontoController`: Handles account operations like checking balance and linked cards.
3. **Payment Service**:
   - Validates card details, expiry dates, and CVV.
   - Processes payments and updates account balances.
4. **Data Access**:
   - Repositories like `BlikRepository` and `KartaRepository` interact with the database to fetch and persist data.

---

## Endpoints Overview

1. **Client Management**: 
   - `GET /api/klienci`: Retrieve all clients.

2. **Account Management**:
   - `GET /api/konto/{kontoId}/saldo`: Check account balance.
   - `GET /api/konto/{kontoId}/karty`: Retrieve linked cards.

3. **Payment Processing**:
   - Handled via `PaymentService` with validation for card and BLIK payments.

---

This repository is publicly accessible and can be integrated with the checkout system for a complete self-checkout simulation https://github.com/Milosz-20/projektKasa
