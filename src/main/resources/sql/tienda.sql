-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-02-2024 a las 19:01:31
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tienda`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `idusuario` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `idcompra` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fechacompra` date DEFAULT current_timestamp(),
  `fechaentrega` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historialusuarios`
--

CREATE TABLE `historialusuarios` (
  `idusuario` int(11) NOT NULL,
  `fechainiciosesion` datetime NOT NULL DEFAULT current_timestamp(),
  `fechafinsesion` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `historialusuarios`
--

INSERT INTO `historialusuarios` (`idusuario`, `fechainiciosesion`, `fechafinsesion`) VALUES
(1, '2024-02-11 18:46:19', NULL),
(1, '2024-02-11 18:49:13', NULL),
(1, '2024-02-11 18:50:54', NULL),
(1, '2024-02-11 18:51:22', '2024-02-11 18:52:02'),
(1, '2024-02-11 18:52:24', '2024-02-11 18:53:14'),
(1, '2024-02-11 18:53:26', '2024-02-11 18:53:50'),
(1, '2024-02-11 18:54:24', NULL),
(1, '2024-02-11 18:55:39', '2024-02-11 18:56:03'),
(1, '2024-02-11 18:56:21', '2024-02-11 18:56:49'),
(1, '2024-02-11 18:57:18', '2024-02-11 18:58:37'),
(1, '2024-02-11 18:58:52', NULL),
(1, '2024-02-11 18:59:21', NULL),
(1, '2024-02-11 18:59:48', '2024-02-11 19:00:39'),
(2, '2024-02-11 18:53:55', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `idproducto` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `precio` double NOT NULL,
  `descuento` double DEFAULT NULL,
  `categoria` varchar(30) DEFAULT NULL,
  `imagen` longblob DEFAULT NULL,
  `stock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idproducto`, `nombre`, `descripcion`, `precio`, `descuento`, `categoria`, `imagen`, `stock`) VALUES
(1, 'PlayStation 4', 'Consola de videojuegos PlayStation 4', 299.99, 0, 'Videojuegos', 0x2f696d672f70726f647563746f732f312e706e67, 10),
(2, 'PC Gaming', 'Ordenador personal optimizado para juegos', 999.99, 0, 'Hardware', 0x2f696d672f70726f647563746f732f322e706e67, 10),
(3, 'Xbox Series X', 'Consola de videojuegos Xbox Series X', 499.99, 0, 'Videojuegos', 0x2f696d672f70726f647563746f732f312e706e67, 10),
(4, 'Nintendo Switch', 'Consola híbrida Nintendo Switch', 349.99, 0, 'Videojuegos', 0x2f696d672f70726f647563746f732f312e706e67, 10),
(5, 'PlayStation 5', 'Consola de videojuegos PlayStation 5', 499.99, 0, 'Videojuegos', 0x2f696d672f70726f647563746f732f312e706e67, 10),
(6, 'Smart TV Samsung', 'Televisor inteligente Samsung de 55 pulgadas', 799.99, 0, 'Electrónica', 0x2f696d672f70726f647563746f732f332e706e67, 10),
(7, 'Monitor LG', 'Monitor de computadora LG de 27 pulgadas', 249.99, 0, 'Electrónica', 0x2f696d672f70726f647563746f732f322e706e67, 10),
(8, 'Cama King Size', 'Cama tamaño king con colchón de espuma viscoelástica', 899.99, 0, 'Hogar', 0x2f696d672f70726f647563746f732f322e706e67, 10),
(9, 'Silla Gamer', 'Silla ergonómica para juegos con soporte lumbar', 199.99, 0, 'Muebles', 0x2f696d672f70726f647563746f732f312e706e67, 10),
(10, 'Refrigerador Whirlpool', 'Refrigerador de acero inoxidable con dispensador de agua', 1499.99, 0, 'Electrodomésticos', 0x2f696d672f70726f647563746f732f312e706e67, 10),
(11, 'Laptop HP', 'Laptop HP Pavilion con procesador Intel Core i7', 899.99, 0, 'Hardware', 0x2f696d672f70726f647563746f732f332e706e67, 10),
(12, 'Barra de Sonido Sony', 'Barra de sonido Sony con subwoofer inalámbrico', 299.99, 0, 'Electrónica', 0x2f696d672f70726f647563746f732f312e706e67, 10),
(13, 'Gafas de Realidad Virtual', 'Gafas de realidad virtual Oculus Rift para juegos inmersivos', 399.99, 0, 'Electrónica', 0x2f696d672f70726f647563746f732f322e706e67, 10),
(14, 'Cocina de Gas', 'Cocina de gas de acero inoxidable con 4 quemadores', 699.99, 0, 'Electrodomésticos', 0x2f696d672f70726f647563746f732f322e706e67, 10),
(15, 'Lavadora Samsung', 'Lavadora de carga frontal Samsung con capacidad de 5.2 pies cúbicos', 899.99, 0, 'Electrodomésticos', 0x2f696d672f70726f647563746f732f332e706e67, 10),
(16, 'Aspiradora Dyson', 'Aspiradora sin bolsa Dyson V11 con tecnología de succión potente', 499.99, 0, 'Electrodomésticos', 0x2f696d672f70726f647563746f732f312e706e67, 10),
(17, 'Impresora Canon', 'Impresora multifuncional Canon PIXMA con conexión Wi-Fi', 149.99, 0, 'Electrónica', 0x2f696d672f70726f647563746f732f312e706e67, 10),
(18, 'Mesa de Comedor', 'Mesa de comedor de madera maciza con capacidad para 6 personas', 399.99, 0, 'Muebles', 0x2f696d672f70726f647563746f732f322e706e67, 10),
(19, 'Sofá Seccional', 'Sofá seccional de cuero genuino con asientos reclinables', 1299.99, 0, 'Muebles', 0x2f696d672f70726f647563746f732f322e706e67, 10),
(20, 'Escritorio de Oficina', 'Escritorio de oficina con estantes y cajones de almacenamiento', 249.99, 0, 'Muebles', 0x2f696d672f70726f647563746f732f312e706e67, 10),
(21, 'Cámara DSLR Canon', 'Cámara réflex digital Canon EOS Rebel T7 con lente EF-S 18-55mm', 599.99, 0, 'Electrónica', 0x2f696d672f70726f647563746f732f322e706e67, 10),
(22, 'Bicicleta de Montaña', 'Bicicleta de montaña Diamondback con cuadro de aluminio', 449.99, 0, 'Deportes', 0x2f696d672f70726f647563746f732f312e706e67, 10),
(23, 'Patineta Eléctrica', 'Patineta eléctrica Razor con motor de 250W', 299.99, 0, 'Deportes', 0x2f696d672f70726f647563746f732f332e706e67, 10),
(24, 'Balón de Fútbol', 'Balón de fútbol Adidas con diseño de la UEFA Champions League', 19.99, 0, 'Deportes', 0x2f696d672f70726f647563746f732f322e706e67, 10),
(25, 'Raqueta de Tenis', 'Raqueta de tenis Wilson Pro Staff con tecnología Countervail', 199.99, 0, 'Deportes', 0x2f696d672f70726f647563746f732f332e706e67, 10),
(26, 'Tabla de Surf', 'Tabla de surf BIC Sport con núcleo de espuma y cubierta de fibra de vidrio', 599.99, 0, 'Deportes', 0x2f696d672f70726f647563746f732f312e706e67, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idusuario` int(11) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  `email` varchar(75) NOT NULL,
  `imagen` varchar(50) DEFAULT NULL,
  `lenguaje` varchar(6) NOT NULL DEFAULT 'en_US',
  `permisos` varchar(255) DEFAULT NULL,
  `roles` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `activacion` tinyint(1) NOT NULL,
  `codigo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idusuario`, `username`, `password`, `nombre`, `apellidos`, `email`, `imagen`, `lenguaje`, `permisos`, `roles`, `activacion`, `codigo`) VALUES
(1, 'test', '$2a$12$kvTNgYyI/lgSpOVxcEjghu7mrSwoqIUqDnhTmgd7N2br86QgbT9rq', 'test', 'test', 'carlos.varalo@educa.jcyl.es', NULL, 'es_ES', NULL, '[\"user\"]', 1, NULL),
(2, 'testadmin', '$2a$12$dYkyf1d6k/Z6kKCXGxg/Z.7ZbA0JLcdUMfonuHTm03BI6dUKLzqBi', 'testadmin', 'testadmin', 'carlosvarasalonso10@gmail.com', NULL, 'es_ES', NULL, '[\"admin\"]', 1, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`idusuario`,`idproducto`),
  ADD KEY `idproducto` (`idproducto`);

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`idcompra`,`idusuario`,`idproducto`),
  ADD KEY `idproducto` (`idproducto`),
  ADD KEY `idusuario` (`idusuario`);

--
-- Indices de la tabla `historialusuarios`
--
ALTER TABLE `historialusuarios`
  ADD PRIMARY KEY (`idusuario`,`fechainiciosesion`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`idproducto`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idusuario`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `idcompra` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `idproducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD CONSTRAINT `carrito_ibfk_1` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`) ON DELETE CASCADE,
  ADD CONSTRAINT `carrito_ibfk_2` FOREIGN KEY (`idproducto`) REFERENCES `productos` (`idproducto`) ON DELETE CASCADE;

--
-- Filtros para la tabla `compras`
--
ALTER TABLE `compras`
  ADD CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`),
  ADD CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`idproducto`) REFERENCES `productos` (`idproducto`);

--
-- Filtros para la tabla `historialusuarios`
--
ALTER TABLE `historialusuarios`
  ADD CONSTRAINT `historialusuarios_ibfk_1` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
