## Digital Wallet System
The Smart Wallet System is a digital wallet application built using Spring Boot and a microservices architecture. It provides payment, wallet, reporting, and notification services, orchestrated via Eureka Server for service discovery and Apache Kafka for asynchronous, decoupled communication. An API Gateway routes requests, while Cloud Config ensures centralized configuration management.

This is a Digital Wallet System composed of four main microservices:
Payment, Notification, Wallet, and Report. 

The system employs a microservices architecture, uses Eureka Server for service discovery, 
and integrates Apache Kafka as a messaging broker to enable reliable and decoupled communication between services.

## Event Flow

1. **User initiates a payment** → API Gateway → Payment Service.
2. **Payment Service validates & processes** transaction → updates Wallet Service.
3. **Wallet Service publishes wallet update events** → Kafka topics.
4. **Notification Service listens & sends alerts** → user receives SMS/Email.
5. **Report Service consumes events** → updates analytics and reports.

## Tech Stack

* **Backend:** Spring Boot, Spring Cloud, Spring Data JPA
* **Service Discovery:** Eureka
* **Messaging:** Apache Kafka
* **Database:** MySQL / PostgreSQL
* **Configuration:** Spring Cloud Config
* **API Gateway:** Spring Cloud Gateway
* **Build/CI/CD:** Maven, Jenkins, Docker, Kubernetes

To build the project
--
```
docker build -t api-gateway ./api-gateway'
docker build -t cloud-config ./cloud-config
docker build -t notification-service ./notification-service
docker build -t payment-service ./payment-service
docker build -t report-service ./report-service
docker build -t user-service ./user-service
docker build -t wallet-service ./wallet-service
```
to start the services
--
```
docker-compose up -d
```
