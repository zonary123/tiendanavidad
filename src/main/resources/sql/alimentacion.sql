-- Test Usuario
INSERT INTO usuarios (idusuario, username, password, nombre, apellidos, email, imagen, lenguaje, permisos, roles,
                      activacion,
                      codigo) VALUE (1, 'test', '$2a$12$iONMT82mekOunGBjSQoJn.eUmdqMCbUp0EtFEO.ipreqSgDWFFbAy',
                                     'test',
                                     'test', 'carlos.varalo@educa.jcyl.es', NULL, 'es_ES', NULL, NULL, 1,
                                     NULL);
-- Usuarios

-- Productos
INSERT INTO productos (nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES ('Cama', 'Cama de 3 plaza', 200.00, 0.00, 'mueble', NULL, 10);
