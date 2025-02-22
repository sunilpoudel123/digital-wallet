## Digital Wallet System

This is a Digital Wallet System composed of four main microservices:
### Payment, 
### Notification,
### Wallet,
### and Report. 
The system employs a microservices architecture, uses Eureka Server for service discovery, 
and integrates Apache Kafka as a messaging broker to enable reliable and decoupled communication between services.


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
