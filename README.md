# Semillero API 

[![Build Status](https://travis-ci.org/nahual/semillero2-api.svg?branch=development)](https://travis-ci.org/nahual/semillero2-api) [![Maintainability](https://api.codeclimate.com/v1/badges/a99a88d28ad37a79dbf6/maintainability)](https://codeclimate.com/github/codeclimate/codeclimate/maintainability) [![Test Coverage](https://api.codeclimate.com/v1/badges/a99a88d28ad37a79dbf6/test_coverage)](https://codeclimate.com/github/codeclimate/codeclimate/test_coverage)

## Introducción

API que usamos en Nahual para hacer un seguimiento de los estudiantes que pasan por el taller y el estado en qué se encuentra su búsqueda laboral.

Está desarrollada en Java, más específicamente con Spring Boot.

## Instalación DEV

### Base de datos

#### Antes que nada: docker volume para la base

Estamos compartiendo el volumen donde se guardan los datos entre el docker-compose de semillero en general y el de la api en particular.

En el caso de la API, va a pedir el volumen creado por el otro docker-compose general `semillero_semillero-mariadb`

#### Creación del esquema + creación de tablas

La instalación de los containers nos instala una base MariaDB. 
De hecho, hay un docker-compose.yml solo para este proyecto, por si no querés levantar los otros containers y solo te levante la base de datos.

El esquema lo tenés que crear a mano, corriendo el archivo `000000000000_empty_schema.sql`.

Luego, ya se puede levantar la aplicación con el comando `./mvnw spring-boot:run`, de esa manera se va a correr el changelog de Liquibase.

Eso nos va a crear todas las tablas necesarias

#### Creación de algunos datos de prueba

Ya con la aplicación arriba, usamos la API para crear 2 nodos de prueba y un CSV que se usa en los tests.

    curl --user semillero:semillero -X POST -H "Content-Type: application/json" --data '{"name":"Capital","address":"capi1"}' http://localhost:8080/node
    curl --user semillero:semillero -X POST -H "Content-Type: application/json" --data '{"name":"Paternal","address":"paternal1"}' http://localhost:8080/node
    curl --user semillero:semillero -i -X POST -H "Content-Type: multipart/form-data" -F "csv=@src/test/resources/data.csv" http://localhost:8080/student/bulk


### Cómo desarrollamos? 

Mientras estamos desarrollando podemos usar la base dockerizada pero correr la aplicación desde nuestro IDE preferido (lo único que deberíamos cambiar es el datasource y apuntarlo a nuestro local dockerizado, en vez de al host dockerizado)

## Documentación API
    
La documentación de la API está escrita en RAML 0.8. 

El HTML generado se **podía** ver en el siguiente [link](https://rawgit.com/nahual/semillero2-api/development/apiV2.html).

**Update**

Rawgit fue dado de baja y hay que migrar esto a otro sistema (github pages, por ejemplo)


