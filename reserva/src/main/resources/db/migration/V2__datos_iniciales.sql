INSERT INTO departamento (numero, piso, estado) VALUES
('101', 1, 'DISPONIBLE'),
('102', 1, 'OCUPADO'),
('201', 2, 'MANTENCION');

INSERT INTO reserva (nombre_residente, fecha_inicio, fecha_fin, estado, departamento_id) VALUES
('Juan Perez', '2026-05-20', '2026-05-21', 'ACTIVA', 1),
('Maria Lopez', '2026-05-22', '2026-05-23', 'CANCELADA', 2);