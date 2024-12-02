
# Price API

## Descripción

**Price API** es una pequeña aplicación realizada en Java22 con la finalidad de cumplir una práctica en la que se ha de exponer un servicio que sea capaz de retornar el precio correcto 
de un artículo, de una marca, en una hora concreta. La gracia de la api recae en que la empresa interesada, tiene diferentes rangos de fechas donde un artículo varía su precio y este servicio
siempre ha de retornar el más prioritario.

---

## Estructura del Proyecto

Existen diferentes maneras de plantear una solución para esta práctica, ya que el objetivo es algo simple, no requiere de una complejidad superior, pero al tratarse de una práctica para demostrar conocimientos
y tener libertad creativa, se ha optado por estructurar el proyecto siguiendo principios de arquitectura hexagonal:

```
src/
├── main/
│   ├── java/com/test/priceapi/
│       ├── application/        # Capa de aplicación (Servicios y DTOs)
│       │   ├── dto/            # Dto de price
│       │   ├── port/           # Interfaz para ser consumida desde la capa de infraestructura
│       │   ├── service/        # Implementación del puerto in de dominio
│       ├── domain/             # Capa de dominio (modelo, puertos y servicios de con lógica de negocio)
│       │   ├── model/          # Modelo del objeto de negocio Price
│       │   ├── port/           # Interfaces de entrada y salida del dominio
│       │   ├── service/        # Servicio de dominio que contiene la lógica de negocio
│       ├── infraestructure/    # Capa de infraestructura (adaptadores, controladores, repositorios, configuración)
│           ├── adapter/        # Adaptador para implementar el puerto out del dominio
│           ├── persistence/    # Servicios y entidades relacionados con la base de datos
│           ├── rest/           # API REST (controladores y mapeadores)
│           ├── config/         # Configuración (Creación de beans para desacoplar el dominio del framework)
├── test/                       # Pruebas unitarias y de integración
```

Aun siguiendo la arquitectura hexagonal, hay diferentes maneras de implementarla y decisiones distintas que tomar, por ejemplo, se podría simplificar
mucho más la complejidad del proyecto haciendo que la query sql obtenga directamente los resultados ordenados por orden de prioridad, pero, ya que la forma en la que
se menciona que el campo priority en la guía parece dejar bastante claro que se trata de una regla de negocio, se ha preferido no cargar el cálculo en base de datos y delegarlo en el servidor
de ahí que tengamos un servicio en la capa de dominio que ordena una lista de Price para retornar el más prioritario pensando en la escalabilidad del mismo.


---

## Tecnologías

- **Java 22** [link](https://www.oracle.com/java/technologies/javase/jdk22-archive.html)
- **Spring Boot 3.4** [link](https://spring.io/projects/spring-boot)
- **H2 Database** [link](https://www.h2database.com/html/main.html)
- **Maven** [link](https://maven.apache.org/)

---


## Configuración y Ejecución

### Requisitos Previos

- JDK 22 instalado.
- Maven instalado.

### Pasos para Ejecutar

1. Clonar el repositorio.
    ```
    git clone https://github.com/cdelgado813/api-test.git
    ```

2. Navegar al directorio del proyecto y compilar con:
   ```bash
   cd ./price-api | mvn clean install
   ```
3. Ejecutar la aplicación:
   ```bash
   mvn spring-boot:run
   ```
4. Acceder a la api:

   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html#/Prices/getActivePrice)

---

## Pruebas

Ejecutar las pruebas con:

1. Lanzar los test:
   ```bash
   mvn clean test
   ```
---

## Endpoints

### Obtener Precio Activo
**Descripción:** Retorna el precio activo para un producto dado según marca, producto y fecha.

- **Endpoint:** `/api/v1/activePrice`
- **Método:** `GET`
- **Parámetros:**
    - `priceStartDate` (LocalDateTime): Fecha y hora de inicio.
    - `productId` (Integer): ID del producto.
    - `brandId` (Integer): ID de la marca.
- **Respuesta Exitosa (200):**
  ```json
    {
        "productId": 35455,
        "brandId": 1,
        "priceList": 3,
        "priceStartDate": "2020-06-15 00:00:00",
        "priceEndDate": "2020-06-15 11:00:00",
        "price": 30.5
    }
  ```
- **Errores Comunes:**
    - `404 Not Found`: No se encontró un precio activo.
    - `500 Internal Server Error`: Error interno del servidor.

## Desarrollador

Este proyecto fue desarrollado por Carlos Delgado.

- **Correo electrónico**: cdelgado813@gmail.com
- **GitHub**: [https://github.com/tu-usuario](https://github.com/tu-usuario)
- **LinkedIn**: [https://www.linkedin.com/in/carlos-delgado-garrido/](https://www.linkedin.com/in/carlos-delgado-garrido/)
