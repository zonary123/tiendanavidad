USE tienda;

INSERT INTO tienda.usuarios (idusuario, username, password, nombre, apellidos, email, imagen, lenguaje, permisos, roles,
                             activacion, codigo)
VALUES (1, 'test', '$2a$12$kvTNgYyI/lgSpOVxcEjghu7mrSwoqIUqDnhTmgd7N2br86QgbT9rq', 'test', 'test',
        'carlos.varalo@educa.jcyl.es', NULL, 'en_US', NULL, '["user"]', 1, NULL);
INSERT INTO tienda.usuarios (idusuario, username, password, nombre, apellidos, email, imagen, lenguaje, permisos, roles,
                             activacion, codigo)
VALUES (2, 'testadmin', '$2a$12$dYkyf1d6k/Z6kKCXGxg/Z.7ZbA0JLcdUMfonuHTm03BI6dUKLzqBi', 'testadmin', 'testadmin',
        'carlosvarasalonso10@gmail.com', NULL, 'es_ES', NULL, '["admin"]', 1, NULL);

INSERT INTO productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (1, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/1.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (2, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/3.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (3, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/1.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (4, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/1.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (5, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/1.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (6, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/3.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (7, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/2.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (8, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/2.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (9, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/1.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (10, 'Cama', 'Play 4', 2000, 0, 'Monitor', '/img/productos/1.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (11, 'Televisor', 'Play 4', 2000, 0, 'Pc', '/img/productos/3.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (12, 'Monitor', 'Play 4', 2000, 0, 'Consola', '/img/productos/1.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (13, 'Play', 'Play 4', 2000, 0, 'Gaming', '/img/productos/2.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (14, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/2.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (15, 'Cama', 'Play 4', 2000, 0, 'Consola', '/img/productos/3.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (16, 'Televisor', 'Play 4', 2000, 0, 'Consola', '/img/productos/1.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (17, 'Monitor', 'Play 4', 2000, 0, 'Hogar', '/img/productos/1.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (18, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/2.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (19, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/2.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (20, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/1.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (21, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/2.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (23, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/3.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (24, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/2.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (25, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/3.png', 10);
INSERT INTO tienda.productos (idproducto, nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES (26, 'Play', 'Play 4', 2000, 0, 'Consola', '/img/productos/1.png', 10);
