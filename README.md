# product-service

## Routes

* `GET http://localhost:8082/products` - Retrieve a list of products
* `POST http://localhost:8082/products` - Create a new product
* `PUT http://localhost:8082/products/{id}` - Update an existing product
* `DELETE http://localhost:8082/products/{id}` - Delete an product
* `GET http://localhost:8082/products/{id}` - Retrieve an product by ID

## Cli with makefile

* make dbstart (if already launched in other micro service don't to do it)
* make create_db (create the database if don't exist)
* make start_service (build project with dependencies and run with hot releaod mandatory to already have java and maven)
