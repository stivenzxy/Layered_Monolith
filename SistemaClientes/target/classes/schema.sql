CREATE TABLE IF NOT EXISTS clientes (
    Id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_documento VARCHAR(50) UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100),
    email VARCHAR(255) UNIQUE
);

MERGE INTO clientes (numero_documento, nombres, apellidos, email)
    KEY(email)
    VALUES ('10203040', 'Ana', 'García', 'ana.garcia@example.com');

MERGE INTO clientes (numero_documento, nombres, apellidos, email)
    KEY(email)
    VALUES ('50607080', 'Carlos', 'Pérez', 'carlos.perez@example.com');

MERGE INTO clientes (numero_documento, nombres, apellidos, email)
    KEY(email)
    VALUES ('90102030', 'Laura', 'Martínez', 'laura.martinez@example.com');
