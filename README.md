# Order & Inventory Microservices Assignment

## A mini microservices project demonstrating:
- Inventory management with batch-level expiry logic
- Order placement with inter-service communication
- REST APIs
- H2 DB for persistence
- Tests (unit + integration)
- Maven multi-module build

## Architecture Overview
### Services
| Service | Port | Description |
|:--------|:----:|------------:|
| inventory-service   | 8081 |    Manages product inventory, batches, expiry, and updates stock. |
| order-service     | 8082 |      Places orders and deducts stock by calling inventory-service. |

### Data Flow (Order Placement)
- Order service receives request
- Order service calls inventory-service → /inventory/update
- Inventory service deducts from batches in FEFO (First Expiry First Out) order
- Order service saves the order in DB
- Response returned

## Tech Stack
- Java 21
- Spring Boot 3+
- Maven (multi-module)
- Spring Data JPA
- H2 database
- RestTemplate (or WebClient if upgraded)
- JUnit 5 + Mockito
- SpringBootTest + TestRestTemplate + MockMvc

## How to build and run
### Build Everything
- mvn clean install

### Run Inventory Service(This will load dummy data in H2 database)
- cd inventory-service
- mvn spring-boot:run

### Run Order Service
- cd order-service
- mvn spring-boot:run

### Testing Flow Using Postman
Hit the following APIs in order:
- Check inventory
```declarative
GET /inventory/P1
```
- Place an order
```declarative
POST /order
```
- Check inventory again
```declarative
GET /inventory/P1
```

## API Documentation
### Inventory Service(8081)
#### Get Inventory
- Gets inventory based on ProductID.
```declarative
GET /inventory/{productId}
```
- Response
```declarative
200 OK → List of batches retrieved successfully
```

#### Update Inventory
- Deducts quantity from batches ordered by expiry date (FEFO).
```declarative
POST /inventory/update
```
- Request
```
{
  "productId": "P1",
  "quantity": 10
}
```
- Response
```declarative
200 OK → Deducted successfully
```

### Order Service(8082)
#### Place order
```declarative
POST /orders
```
- Request
```
{
  "productId": "P1",
  "quantity": 10
}
```
- Response
```declarative
{
    "id": 1,
    "productId": "P1",
    "quantity": 5,
    "orderDate": "2025-11-27T12:00:00Z"
}
```

## Testing Instructions
### Run All Tests
```declarative
mvn test
```

### Unit Tests (Mockito)
1. Includes:
   - InventoryServiceTest
   - DefaultInventoryHandlerTest
   - OrderServiceTest
2. Tests mock dependencies and verify:
   - FEFO batch deduction logic
   - InventoryHandlerFactory delegation
   - Order persistence
   - REST client calls
3. Integration tests:
   - Located under:
   ```declarative
        inventory-service/src/test/java/.../integration/
        order-service/src/test/java/.../integration/
    ```
   - Tests include:
     - @SpringBootTest
     - H2 real database
