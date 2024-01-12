-- Test Usuario
INSERT INTO usuarios (idusuario, username, password, nombre, apellidos, email, imagen, lenguaje, permisos, roles,
                      activacion,
                      codigo) VALUE (1, 'test', '$2a$12$r7KpmujbFdsp4Xy1lvie0edS86Xd3ooFdYwdhKXlBhziavQo6t9DO',
                                     'test',
                                     'test', 'carlos.varalo@educa.jcyl.es', NULL, 'es_ES', NULL, NULL, 1,
                                     NULL);
-- Usuarios

-- Productos
INSERT INTO productos (nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES ('Cama', 'Cama de 3 plaza', 2000000.00, 0.00, 'mueble', NULL, 10);
