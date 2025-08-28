CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

CREATE TABLE IF NOT EXISTS lector (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS libro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    disponible BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS prestamo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    libro_id BIGINT NOT NULL,
    lector_id BIGINT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE NULL,

    CONSTRAINT fk_prestamo_libro FOREIGN KEY (libro_id) REFERENCES libro(id),
    CONSTRAINT fk_prestamo_lector FOREIGN KEY (lector_id) REFERENCES lector(id)
);

INSERT IGNORE INTO lector (id, nombre, correo) VALUES
(1, 'Ana López', 'ana@example.com'),
(2, 'Carlos Pérez', 'carlos@example.com');

INSERT IGNORE INTO libro (id, titulo, autor, disponible) VALUES
(1, 'Cien años de soledad', 'Gabriel García Márquez', TRUE),
(2, 'El Principito', 'Antoine de Saint-Exupéry', TRUE);