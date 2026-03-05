# Club de Vinilos - Sistema de Gestión

Plataforma web dinámica desarrollada en Java para la administración de un catálogo de vinilos, gestión de socios y préstamos, estructurada bajo el patrón de arquitectura MVC.

## Stack Tecnológico

* **Backend:** Java 17+, Jakarta EE (Servlets 6.0)
* **Frontend:** JSP, JSTL 3.0, HTML5
* **Persistencia:** JDBC puro, Patrón DAO
* **Base de Datos:** MariaDB
* **Servidor de Aplicaciones:** Apache Tomcat 11.0.x
* **Construcción:** Maven

## Funcionalidades del Sistema

* **Autenticación:** Control de acceso para socios registrados.
* **Catálogo Dinámico:** Visualización de vinilos con control de disponibilidad en tiempo real.
* **Gestión de Préstamos:** Registro de solicitudes y control de stock (un vinilo prestado no puede volver a solicitarse).
* **Módulo de Devoluciones:** Liberación de vinilos y cierre de transacciones de préstamo.

## Configuración y Despliegue

### 1. Preparación de la Base de Datos
Ejecutar el siguiente script para crear el esquema completo y cargar datos iniciales:

```sql
CREATE DATABASE IF NOT EXISTS club_vinilos;
USE club_vinilos;

-- Tabla de Socios
CREATE TABLE socios (
    id_socio INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Tabla de Vinilos (con control de disponibilidad)
CREATE TABLE vinilos (
    id_vinilo INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    artista VARCHAR(150) NOT NULL,
    anio_lanzamiento INT NOT NULL,
    genero VARCHAR(50),
    disponible BOOLEAN DEFAULT TRUE
);

-- Tabla de Préstamos
CREATE TABLE prestamos (
    id_prestamo INT AUTO_INCREMENT PRIMARY KEY,
    id_socio INT NOT NULL,
    id_vinilo INT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE,
    FOREIGN KEY (id_socio) REFERENCES socios(id_socio),
    FOREIGN KEY (id_vinilo) REFERENCES vinilos(id_vinilo)
);

-- Carga de Datos Iniciales
INSERT INTO socios (nombre, email, password) VALUES ('Usuario Test', 'test@club.cl', '123456');

INSERT INTO vinilos (titulo, artista, anio_lanzamiento, genero) VALUES 
('The Dark Side of the Moon', 'Pink Floyd', 1973, 'Rock Progresivo'),
('Abbey Road', 'The Beatles', 1969, 'Rock'),
('Kind of Blue', 'Miles Davis', 1959, 'Jazz');