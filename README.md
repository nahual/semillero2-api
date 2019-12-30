# Semillero API 

[![Build Status](https://travis-ci.org/nahual/semillero2-api.svg?branch=development)](https://travis-ci.org/nahual/semillero2-api) [![Maintainability](https://api.codeclimate.com/v1/badges/a99a88d28ad37a79dbf6/maintainability)](https://codeclimate.com/github/codeclimate/codeclimate/maintainability) [![Test Coverage](https://api.codeclimate.com/v1/badges/a99a88d28ad37a79dbf6/test_coverage)](https://codeclimate.com/github/codeclimate/codeclimate/test_coverage)

## Introducción

API que usamos en Nahual para hacer un seguimiento de los estudiantes que pasan por el taller y el estado en qué se encuentra su búsqueda laboral.

Está desarrollada en Java, más específicamente con [Spring Boot](https://hub.docker.com/_/mariadb/). Usa la base de datos [MariaDB](https://hub.docker.com/_/mariadb/).

## Instalación DEV

La instalación de los containers nos instala una base MariaDB.
De hecho, hay un `docker-compose.yml` para tener solo la base dockerizada y o

El esquema se crea automaticamente (ver los archivos de compose) en base a `src/main/resources/sql-migrations/000000000000_empty_schema.sql`. El archivo es copiado a un directorio que el proceso (en el container) de la DB va a revisar y ejecutar.

Para borrar imagenes, containers, volumenes y networks del docker-compose, usar `docker-compose down -v --remove-orphans`.
Es util para todo lo relacionado con alguna prueba, y arrancar "de cero".

### Solo base de datos dockerizada (y API local)

Ejecutando `docker-compose up` (que por default usa el archivo `docker-compose.yml`) levanta un MariaDB en un container.
Se puede entrar con `docker exec -it semillero2-api_semillero-db_1 bash`. Luego, con `mysql` te conectas a la DB.

La API se puede correr local (por consola o con tu IDE preferido) con el comando `./mvnw spring-boot:run`. De esa manera se va a correr el changelog de Liquibase (donde se crean las tablas si no existen).

TODO: revisar que levanta bien!

### Base de datos y API dockerizadas

Ejecutando `docker-compose -f docker-compose-ambas.yml up` se ejecutan las dos partes en sus respectivos containers.

### Creación de algunos datos de prueba

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


