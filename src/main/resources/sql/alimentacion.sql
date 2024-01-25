USE tienda;

INSERT INTO tienda.usuarios (idusuario, username, password, nombre, apellidos, email, imagen, lenguaje, permisos, roles,
                             activacion, codigo)
VALUES (1, 'test', '$2a$12$kvTNgYyI/lgSpOVxcEjghu7mrSwoqIUqDnhTmgd7N2br86QgbT9rq', 'test', 'test',
        'carlos.varalo@educa.jcyl.es', NULL, 'en_US', NULL, '["user"]', 1, NULL);
INSERT INTO tienda.usuarios (idusuario, username, password, nombre, apellidos, email, imagen, lenguaje, permisos, roles,
                             activacion, codigo)
VALUES (2, 'testadmin', '$2a$12$vvQLNDjsqjEHRYVBlBEpselwiATJFjS64RpBRtwsjdI.6w3UOLSem', 'testadmin', 'testadmin',
        'carlosvarasalonso10@gmail.com', NULL, 'es_ES', NULL, '["admin"]', 1, NULL);
INSERT INTO tienda.usuarios (idusuario, username, password, nombre, apellidos, email, imagen, lenguaje, permisos, roles,
                             activacion, codigo)
VALUES (142, 'asda', '$2a$12$FyTQJq6B/uvsDP1XMj5vYuQ2TdgJQqmWUKH761OOsUsgPGShmj1WW', 'asd', '', 'asdas@gmail.com', NULL,
        'es_ES', NULL, '["user"]', 1, NULL);
INSERT INTO tienda.usuarios (idusuario, username, password, nombre, apellidos, email, imagen, lenguaje, permisos, roles,
                             activacion, codigo)
VALUES (143, 'asdd', '$2a$12$35NRSFhTo1bzU8DSxkm6m.TYDtXpbssSWlS2TtuAJC4U.wWs0c.qi', 'asdd', '', 'asd@gmail.com', NULL,
        'es_ES', NULL, '["user"]', 1, NULL);

INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (1, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (2, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (3, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (4, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (5, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (6, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (7, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (8, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (9, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (10, 'Cama', 'Play 4', 2000, 0, 'Monitor', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (11, 'Televisor', 'Play 4', 2000, 0, 'Pc', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (12, 'Monitor', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (13, 'Play', 'Play 4', 2000, 0, 'Gaming', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (14, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (15, 'Cama', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (16, 'Televisor', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (17, 'Monitor', 'Play 4', 2000, 0, 'Hogar', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (18, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (19, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (20, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (21, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (23, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (24, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (25, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (26, 'Play', 'Play 4', 2000, 0, 'Consola', NULL, 10);
