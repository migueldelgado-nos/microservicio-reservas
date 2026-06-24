CREATE TABLE departamento (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    numero VARCHAR(255) NOT NULL UNIQUE,
    piso INT,
    estado VARCHAR(50) NOT NULL
);

CREATE TABLE reserva (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre_residente VARCHAR(255) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    estado VARCHAR(50) NOT NULL,
    departamento_id BIGINT NOT NULL,

    CONSTRAINT fk_reserva_departamento
    FOREIGN KEY (departamento_id)
    REFERENCES departamento(id)
);