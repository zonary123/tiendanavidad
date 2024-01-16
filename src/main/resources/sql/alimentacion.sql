USE tienda;

INSERT IGNORE INTO usuarios (idusuario, username, password, nombre, apellidos, email, imagen, lenguaje, permisos,
                             roles,
                             activacion, codigo)
VALUES (1, 'test', '$2a$12$ETWmKHOOG.ZVX5HvdBcXfudIRNpiar39SWoIs7MYIJXdxly/C/266', 'test', 'test',
        'carlos.varalo@educa.jcyl.es', NULL, 'es_ES', NULL, '["user"]', 1, NULL);
INSERT IGNORE INTO usuarios (idusuario, username, password, nombre, apellidos, email, imagen, lenguaje, permisos,
                             roles,
                             activacion, codigo)
VALUES        (2, 'testadmin', '$2a$12$TaF/Ylk6DZAJqNxLaKGweuQ.84zQxGMW1Qu5.Aen0dBv3WaVSsDRq', 'testadmin', 'testadmin',
        'carlosvarasalonso10@gmail.com', NULL, 'es_ES', NULL, '["user"]', 1, NULL);

INSERT IGNORE INTO productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (1, 'Play', 'Play 4', 2000.00, 0.00, 'Consola', NULL, 10),
       (2, 'Play', 'Play 4', 2000.00, 0.00, 'Consola', NULL, 10),
       (3, 'Play', 'Play 4', 2000.00, 0.00, 'Consola', NULL, 10),
       (4, 'Play', 'Play 4', 2000.00, 0.00, 'Consola', NULL, 10),
       (5, 'Play', 'Play 4', 2000.00, 0.00, 'Consola', NULL, 10),
       (6, 'Play', 'Play 4', 2000.00, 0.00, 'Consola', NULL, 10),
       (7, 'Play', 'Play 4', 2000.00, 0.00, 'Consola', NULL, 10),
       (8, 'Play', 'Play 4', 2000.00, 0.00, 'Consola', NULL, 10);
