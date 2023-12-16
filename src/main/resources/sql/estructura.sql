CREATE TABLE productos (
  idporducto  INT AUTO_INCREMENT PRIMARY KEY,
  nombre      VARCHAR(255) NOT NULL,
  descripcion VARCHAR(255) NOT NULL,
  precio      FLOAT(10, 2) NOT NULL,
  descuento   FLOAT(3, 2)  NULL,
  categoria   VARCHAR(255) NULL,
  imagen      LONGBLOB     NULL,
  stock       INT          NULL,
  CHECK (`precio` > 0)
);

CREATE TABLE usuarios (
  idusuario  INT AUTO_INCREMENT PRIMARY KEY,
  username   VARCHAR(30)                  NULL,
  password   VARCHAR(100)                 NOT NULL,
  nombre     VARCHAR(50)                  NOT NULL,
  apellidos  VARCHAR(100)                 NULL,
  email      VARCHAR(255)                 NULL,
  imagen     LONGBLOB                     NULL,
  lenguaje   VARCHAR(6) DEFAULT 'es_ES'   NULL,
  permisos   VARCHAR(255)                 NULL,
  roles      LONGTEXT COLLATE utf8mb4_bin NULL,
  activacion TINYINT(1)                   NULL,
  CONSTRAINT email UNIQUE (email),
  CONSTRAINT username UNIQUE (username),
  CHECK (JSON_VALID(`roles`))
);

CREATE TABLE carrito (
  idusuario  INT NOT NULL,
  idproducto INT NOT NULL,
  cantidad   INT NULL,
  PRIMARY KEY (idusuario, idproducto),
  CONSTRAINT carrito_ibfk_1 FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario),
  CONSTRAINT carrito_ibfk_2 FOREIGN KEY (idproducto) REFERENCES productos (idporducto)
);

CREATE INDEX idproducto ON carrito (idproducto);

CREATE TABLE compras (
  idcompra     INT AUTO_INCREMENT,
  idusuario    INT                              NOT NULL,
  idproducto   INT                              NOT NULL,
  cantidad     INT                              NOT NULL,
  fechacompra  DATE DEFAULT CURRENT_TIMESTAMP() NULL,
  fechaentrega DATETIME                         NULL,
  PRIMARY KEY (idcompra, idusuario, idproducto),
  CONSTRAINT compras_ibfk_1 FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario),
  CONSTRAINT compras_ibfk_2 FOREIGN KEY (idproducto) REFERENCES productos (idporducto)
);

CREATE INDEX idproducto ON compras (idproducto);

CREATE INDEX idusuario ON compras (idusuario);

CREATE TABLE historialusuarios (
  idusuario         INT                                  NOT NULL,
  fechainiciosesion DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL,
  fechafinsesion    DATETIME                             NULL,
  PRIMARY KEY (idusuario, fechainiciosesion),
  CONSTRAINT historialusuarios_ibfk_1 FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario)
);
