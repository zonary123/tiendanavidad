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
  roles      JSON  NULL,
  activacion TINYINT(1)                   NULL,
  codigo VARCHAR,
  CONSTRAINT email UNIQUE (email),
  CONSTRAINT username UNIQUE (username),
  CHECK (JSON_VALID(roles))
);

CREATE TABLE carrito (
  idusuario  INT NOT NULL,
  idproducto INT NOT NULL,
  cantidad   INT NULL,
  PRIMARY KEY (idusuario, idproducto),
  FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario) ON DELETE CASCADE,
  FOREIGN KEY (idproducto) REFERENCES productos (idporducto) ON DELETE CASCADE
);

CREATE TABLE compras (
  idcompra     INT AUTO_INCREMENT,
  idusuario    INT                              NOT NULL,
  idproducto   INT                              NOT NULL,
  cantidad     INT                              NOT NULL,
  fechacompra  DATE DEFAULT CURRENT_TIMESTAMP() NULL,
  fechaentrega DATETIME                         NULL,
  PRIMARY KEY (idcompra, idproducto),
  FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario) ON DELETE CASCADE,
  FOREIGN KEY (idproducto) REFERENCES productos (idporducto) ON DELETE CASCADE
);

CREATE TABLE historialusuarios (
  idusuario         INT                                  NOT NULL,
  fechainiciosesion DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL,
  fechafinsesion    DATETIME                             NULL,
  PRIMARY KEY (idusuario, fechainiciosesion),
  FOREIGN KEY (idusuario) REFERENCES usuarios (idusuario) ON DELETE CASCADE
);
