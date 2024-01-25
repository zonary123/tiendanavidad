CREATE DATABASE IF NOT EXISTS tienda;

USE tienda;

CREATE TABLE IF NOT EXISTS productos (
  idproducto  INT AUTO_INCREMENT PRIMARY KEY,
  nombre      VARCHAR(50)  NOT NULL,
  descripcion VARCHAR(255) NOT NULL,
  precio      DOUBLE       NOT NULL,
  descuento   DOUBLE       NULL,
  categoria   VARCHAR(30)  NULL,
  imagen      LONGBLOB     NULL,
  stock       INT          NULL,
  CHECK (`precio` > 0)
);

CREATE TABLE IF NOT EXISTS usuarios (
  idusuario  INT AUTO_INCREMENT PRIMARY KEY,
  username   VARCHAR(30)                  NULL,
  password   VARCHAR(100)                 NOT NULL,
  nombre     VARCHAR(30)                  NOT NULL,
  apellidos  VARCHAR(50)                  NULL,
  email      VARCHAR(75)                  NOT NULL,
  imagen     LONGBLOB                     NULL,
  lenguaje   VARCHAR(6) DEFAULT 'es_ES'   NOT NULL,
  permisos   VARCHAR(255)                 NULL,
  roles      LONGTEXT COLLATE utf8mb4_bin NULL,
  activacion TINYINT(1)                   NOT NULL,
  codigo     VARCHAR(255)                 NULL,
  CONSTRAINT email UNIQUE (email),
  CONSTRAINT username UNIQUE (username),
  CHECK (JSON_VALID(`roles`))
);

CREATE TABLE IF NOT EXISTS carrito (
  idusuario  INT NOT NULL,
  idproducto INT NOT NULL,
  cantidad   INT NULL,
  PRIMARY KEY (idusuario, idproducto),
  CONSTRAINT carrito_ibfk_1 FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario) ON DELETE CASCADE,
  CONSTRAINT carrito_ibfk_2 FOREIGN KEY (idproducto) REFERENCES productos (idproducto) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idproducto ON carrito (idproducto);

CREATE TABLE IF NOT EXISTS compras (
  idcompra     INT AUTO_INCREMENT,
  idusuario    INT                              NOT NULL,
  idproducto   INT                              NOT NULL,
  cantidad     INT                              NOT NULL,
  fechacompra  DATE DEFAULT CURRENT_TIMESTAMP() NULL,
  fechaentrega DATETIME                         NULL,
  PRIMARY KEY (idcompra, idusuario, idproducto),
  CONSTRAINT compras_ibfk_1 FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario),
  CONSTRAINT compras_ibfk_2 FOREIGN KEY (idproducto) REFERENCES productos (idproducto)
);

CREATE INDEX IF NOT EXISTS idproducto ON compras (idproducto);

CREATE INDEX IF NOT EXISTS idusuario ON compras (idusuario);

CREATE TABLE IF NOT EXISTS historialusuarios (
  idusuario         INT                                  NOT NULL,
  fechainiciosesion DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL,
  fechafinsesion    DATETIME                             NULL,
  PRIMARY KEY (idusuario, fechainiciosesion),
  CONSTRAINT historialusuarios_ibfk_1 FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario)
);
