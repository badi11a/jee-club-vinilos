# Club de Vinilos - Sistema de Gestión

Plataforma web dinámica desarrollada en Java para la administración de un catálogo de vinilos y la gestión de accesos, estructurada bajo el patrón de arquitectura MVC.

## Stack Tecnológico

* **Backend:** Java 17+, Jakarta EE (Servlets 6.0)
* **Frontend:** JSP, JSTL 3.0, HTML5
* **Persistencia:** JDBC puro, Patrón DAO
* **Base de Datos:** MariaDB
* **Servidor de Aplicaciones:** Apache Tomcat 11.0.x
* **Construcción:** Maven
* **Entorno:** Visual Studio Code

## Arquitectura (MVC)

El proyecto mantiene una estricta separación de responsabilidades:
* **Controlador (`cl.club.vinilos.controlador`):** Servlets que interceptan peticiones HTTP y orquestan el flujo de datos.
* **Modelo (`cl.club.vinilos.modelo`):** Entidades planas (POJOs) que representan el dominio del negocio.
* **Persistencia (`cl.club.vinilos.dao`):** Clases que encapsulan la lógica SQL y la conexión transaccional a la base de datos.
* **Vistas (`src/main/webapp`):** Archivos JSP que renderizan la interfaz mediante etiquetas lógicas JSTL.

## Configuración y Despliegue

### 1. Preparación de la Base de Datos
Ejecutar el siguiente script en el motor MariaDB para crear el esquema y cargar los datos de prueba:

```sql
CREATE DATABASE IF NOT EXISTS club_vinilos;
USE club_vinilos;

CREATE TABLE vinilos (
    id_vinilo INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    artista VARCHAR(150) NOT NULL,
    anio_lanzamiento INT NOT NULL,
    genero VARCHAR(50)
);

INSERT INTO vinilos (titulo, artista, anio_lanzamiento, genero) VALUES 
('The Dark Side of the Moon', 'Pink Floyd', 1973, 'Rock Progresivo'),
('Abbey Road', 'The Beatles', 1969, 'Rock'),
('Kind of Blue', 'Miles Davis', 1959, 'Jazz');