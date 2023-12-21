INSERT INTO usuarios (username, password, nombre, apellidos, email, imagen, lenguaje, permisos, roles, activacion,
                      codigo) VALUE ('asdda', '$2a$10$0fcXwlQMI03zneKt54YAyusoNeJk5vAG802HcklKANzmUobDwADY.', 'carlos',
                                     'perez', 'da@a.com', NULL, 'es_ES', NULL, NULL, 1,
                                     NULL);
SELECT * FROM usuarios;


INSERT INTO productos (nombre, descripcion, precio, descuento, categoria, imagen, stock)
VALUES ('Cama', 'Cama de 3 plaza', 200.00, 0.00, 'Cama', NULL, 10);

SELECT * FROM productos;
