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
  CHECK (precio > 0)
);

CREATE TABLE usuarios (
  idusuario  INT AUTO_INCREMENT PRIMARY KEY,
  username   VARCHAR(30)                  NULL UNIQUE,
  password   VARCHAR(100)                 NOT NULL,
  nombre     VARCHAR(30)                  NOT NULL,
  apellidos  VARCHAR(50)                  NULL,
  email      VARCHAR(75)                  NOT NULL UNIQUE,
  imagen     LONGBLOB                     NULL,
  lenguaje   VARCHAR(6)   DEFAULT 'es_ES' NOT NULL,
  permisos   VARCHAR(255)                 NULL,
  roles      JSON                         NULL,
  activacion TINYINT(1)                   NOT NULL,
  codigo     VARCHAR(255) DEFAULT NULL
);

CREATE TABLE carrito (
  idusuario  INT NOT NULL,
  idproducto INT NOT NULL,
  cantidad   INT NULL,
  PRIMARY KEY (idusuario, idproducto),
  FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario),
  FOREIGN KEY (idproducto) REFERENCES productos (idproducto)
);

CREATE TABLE compras (
  idcompra     INT AUTO_INCREMENT,
  idusuario    INT                              NOT NULL,
  idproducto   INT                              NOT NULL,
  cantidad     INT                              NOT NULL,
  fechacompra  DATE DEFAULT CURRENT_TIMESTAMP() NULL,
  fechaentrega DATETIME                         NULL,
  PRIMARY KEY (idcompra, idusuario, idproducto),
  FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario),
  FOREIGN KEY (idproducto) REFERENCES productos (idproducto)
);


CREATE TABLE historialusuarios (
  idusuario         INT                                  NOT NULL,
  fechainiciosesion DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL,
  fechafinsesion    DATETIME                             NULL,
  PRIMARY KEY (idusuario, fechainiciosesion),
  FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario)
);

SHOW TABLES;
