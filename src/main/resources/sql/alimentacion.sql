INSERT INTO usuarios (username, password, nombre, apellidos, email, imagen, lenguaje, permisos, roles, activacion,
                      codigo) VALUE ('carlos', '$2a$10$0fcXwlQMI03zneKt54YAyusoNeJk5vAG802HcklKANzmUobDwADY.', 'carlos',
                                     'perez', 'a@a.com', NULL, 'es_ES', NULL, NULL, 1,
                                     NULL);
SELECT * FROM usuarios;