# Abogados Backend API - Spring Boot

Este es el backend de una aplicación de gestión de casos para abogados. La API está desarrollada usando **Spring Boot**, que maneja la autenticación, gestión de casos, y operaciones CRUD (Crear, Leer, Actualizar, Eliminar) con **JPA** para la persistencia de datos en una base de datos **MySQL**.

## Características

- **Registro y autenticación**: Los abogados pueden registrarse e iniciar sesión. La autenticación se maneja con **Spring Security** y **JWT (JSON Web Token)**.
- **Gestión de casos**: Los abogados pueden registrar, consultar, actualizar y eliminar casos.
- **Roles de usuario**: Sistema de roles basado en diferentes tipos de usuarios, como abogado y administrador.
- **Notificaciones**: Los usuarios reciben mensajes de éxito y error a lo largo de la interacción con la API.

## Tecnologías utilizadas

- **Spring Boot** para el desarrollo de la API.
- **Spring Data JPA** para la interacción con la base de datos.
- **Spring Security** para la autenticación y autorización.
- **JWT (JSON Web Token)** para la autenticación basada en tokens.
- **MySQL** como base de datos.
- **Lombok** para reducir el código repetitivo en las entidades.

## Instalación y ejecución

### Prerrequisitos

- **JDK 17+**
- **Maven 3.6+**
- **MySQL**

## Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/marciales34/BackendSextoSemestre.git
