# API Gestión de Pedidos

## Overview

Este microservicio se encarga de la gestión de **pedidos** y **clientes**, contiene operaciones CRUD para interactuar con el cliente y los datos del pedido.


## Features

- **Clientes:**
  - Esta API permite crear un cliente, obtener todos los clientes o un cliente en específico, actualizar la información del cliente y eliminar un cliente
  - La información que maneja un cliente es nombre, apellido paterno, apellido materno, correo y dirección de envío.
  
- **Pedidos:**
  - Esta API permite crear un pedido, obtener todos los pedidos, un pedido en específico, o los pedidos de un cliente en específico, actualizar la cantidad de un producto de
    un pedido y eliminar un pedido.
  - La información que maneja un pedido es código del producto, cantidad, precio y el cliente que lo realizó.

## Tecnologías utilizadas

- **Spring Boot**
- **Spring Data JPA**
- **Swagger**
- **MySql**

## Instalación

### Requisitos

- **Java 17**
- **Maven**
- **MySql**

### Pasos para ejecutar el servicio de manera local

1. **Clonar el Repositorio:**

    ```bash
    git clone https://github.com/Ivanjp/gestor-pedidos.git
    cd gestor-pedidos
    ```

2. **Configurar la Base De Datos:**

   Se tiene que modificar el archivo `application.properties` para que sea posible acceder a la BD:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/prueba
    spring.datasource.username={coloca aquí tu usuario}
    spring.datasource.password={coloca aquí tu contraseña}
    spring.datasource.driver-class=com.mysql.cj.jdbc.Driver
    ```

    En MySql ejecuta el archivo `gestor.sql` para que se cree la BD, las tablas y los datos de prueba.

3. **Ejecutar Servicio:**

   Ejecuta estos comandos para levantar el servicio:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. **Documentación**

    Para poder visualizar la documentación, accede a esta url desde un navegador:

    ```
    http://localhost:8080/swagger-ui.html
    ```

### Ejecutar servicio de forma remota

Para probar el servicio se debe de hacer mediante la siguiente uri:

 ```
    https://glistening-solace-production.up.railway.app/
  ```
Para poder visualizar la documentación, accede a esta url desde un navegador:

```
    https://glistening-solace-production.up.railway.app/swagger-ui.html
  ```

## Endpoints

### Clientes

- `GET /customer` - Obtenr los clientes.
- `GET /customer/{id}` - Obtener un cliente por su ID.
- `POST /customer` - Crear un cliente.
- `PUT /customer/{id}` - Actualizar información de un cliente.
- `DELETE /customer/{id}` - Eliminar un cliente.

### Pedidos

- `GET /order` - Obtener los pedidos.
- `GET /order/{id}` - Obtener un pedido por su id.
- `GET /order/customer/{id}` - Obtener los pedidos de un cliente.
- `POST /order` - Crear un pedido.
- `PUT /order/{id}/quantity` - Actualizar la cantidad de un producto de un pedido.
- `DELETE /order/{id}` - Eliminar un pedido.
