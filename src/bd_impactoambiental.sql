-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-09-2022 a las 22:00:38
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `impactoambiental`
--
CREATE DATABASE IF NOT EXISTS `impactoambiental` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `impactoambiental`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agentesectorial`
--

CREATE TABLE `agentesectorial` (
  `id_agente` int(100) NOT NULL,
  `razonSocial` varchar(255) NOT NULL,
  `territorio` varchar(255) NOT NULL,
  `tipo` enum('Departamento','Ministerio','Provincia') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

CREATE TABLE `direccion` (
  `id_direccion` int(100) NOT NULL,
  `id_espacio` int(100) NOT NULL,
  `altura` int(100) NOT NULL,
  `calle` varchar(255) NOT NULL,
  `tipo_espacio` enum('Trabajo','Vivienda','Otro') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espacio`
--

CREATE TABLE `espacio` (
  `id_espacio` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estacion`
--

CREATE TABLE `estacion` (
  `id_estacion` int(100) NOT NULL,
  `id_espacio` int(100) NOT NULL,
  `numero_Estacion` int(100) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `distancia_siguiente` float NOT NULL,
  `distancia_anterior` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factoremision`
--

CREATE TABLE `factoremision` (
  `id_factor` int(100) NOT NULL,
  `valor` int(100) NOT NULL,
  `id_usuario_update` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mediotransporte`
--

CREATE TABLE `mediotransporte` (
  `id_transporte` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `miembro`
--

CREATE TABLE `miembro` (
  `id_miembro` int(100) NOT NULL,
  `id_persona` int(100) NOT NULL,
  `id_sector` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `miembroxtrayecto`
--

CREATE TABLE `miembroxtrayecto` (
  `id_miembroxtrayecto` int(100) NOT NULL,
  `id_miembro` int(100) NOT NULL,
  `id_trayecto` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `organizacion`
--

CREATE TABLE `organizacion` (
  `id_organizacion` int(100) NOT NULL,
  `razonSocial` varchar(255) NOT NULL,
  `tipo` enum('Gubernamental','ONG','Empresa','Institucion') NOT NULL,
  `id_Agente` int(100) NOT NULL,
  `clasificacion` enum('Ministerio','Universidad','Escuela','EmpresaDelSectorPrimario','EmpresaDelSectorSecundario') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paradatransportepublico`
--

CREATE TABLE `paradatransportepublico` (
  `id_parada` int(100) NOT NULL,
  `id_transporte` int(100) NOT NULL,
  `id_estacion` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id_persona` int(100) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `tipoDocumento` varchar(255) NOT NULL,
  `nroDocumento` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reporteagentesectorial`
--

CREATE TABLE `reporteagentesectorial` (
  `id_reporteagente` int(100) NOT NULL,
  `id_agente` int(100) NOT NULL,
  `mes` int(100) NOT NULL,
  `anio` int(100) NOT NULL,
  `calculohc` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportehcexcel`
--

CREATE TABLE `reportehcexcel` (
  `id_reportehcexcel` int(100) NOT NULL,
  `id_organizacion` int(100) NOT NULL,
  `calculohc` int(100) NOT NULL,
  `periodicidad` enum('Mensual','Anual') NOT NULL,
  `fechaCarga` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportehcorganizacion`
--

CREATE TABLE `reportehcorganizacion` (
  `id_ReporteOrganizacion` int(100) NOT NULL,
  `id_organizacion` int(100) NOT NULL,
  `calculoHC` int(100) NOT NULL,
  `mes` int(100) NOT NULL,
  `anio` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sector`
--

CREATE TABLE `sector` (
  `id_sector` int(100) NOT NULL,
  `id_espacio` int(100) NOT NULL,
  `id_organizacion` int(100) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tramo`
--

CREATE TABLE `tramo` (
  `id_tramo` int(100) NOT NULL,
  `puntoSalida` int(100) NOT NULL,
  `puntoLlegada` int(100) NOT NULL,
  `id_transporte` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transportepublico`
--

CREATE TABLE `transportepublico` (
  `id_transporte` int(100) NOT NULL,
  `tipoTransporte` enum('Tren','Colectivo','Subte') NOT NULL,
  `linea` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trayecto`
--

CREATE TABLE `trayecto` (
  `id_trayecto` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trayectoxtramo`
--

CREATE TABLE `trayectoxtramo` (
  `id_trayectoxtramo` int(100) NOT NULL,
  `id_tramo` int(100) NOT NULL,
  `id_trayecto` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(100) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `contrasenia` varchar(100) NOT NULL,
  `rol` enum('Admin','Usuario','Miembro') NOT NULL,
  `id_persona` int(100) NOT NULL,
  `id_config` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculoparticular`
--

CREATE TABLE `vehiculoparticular` (
  `id_transporte` int(100) NOT NULL,
  `tipoVehiculo` enum('Moto','Auto','Camioneta','Uber','Cabify','Taxi','Remis','BiciPie') NOT NULL,
  `tipoCombustible` enum('GNC','Nafta','Electrico','Gasoil','NoConsume') NOT NULL,
  `cantidadCompartido` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agentesectorial`
--
ALTER TABLE `agentesectorial`
  ADD PRIMARY KEY (`id_agente`);

--
-- Indices de la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD PRIMARY KEY (`id_direccion`),
  ADD KEY `direccion_id_espacio_espacio` (`id_espacio`);

--
-- Indices de la tabla `espacio`
--
ALTER TABLE `espacio`
  ADD PRIMARY KEY (`id_espacio`);

--
-- Indices de la tabla `estacion`
--
ALTER TABLE `estacion`
  ADD PRIMARY KEY (`id_estacion`),
  ADD KEY `estacion_id_espacio_espacio` (`id_espacio`);

--
-- Indices de la tabla `factoremision`
--
ALTER TABLE `factoremision`
  ADD PRIMARY KEY (`id_factor`),
  ADD KEY `factor_id_usuario_usuario` (`id_usuario_update`);

--
-- Indices de la tabla `mediotransporte`
--
ALTER TABLE `mediotransporte`
  ADD PRIMARY KEY (`id_transporte`);

--
-- Indices de la tabla `miembro`
--
ALTER TABLE `miembro`
  ADD PRIMARY KEY (`id_miembro`),
  ADD KEY `miembro_id_persona_persona` (`id_persona`),
  ADD KEY `miembro_id_sector_sector` (`id_sector`);

--
-- Indices de la tabla `miembroxtrayecto`
--
ALTER TABLE `miembroxtrayecto`
  ADD PRIMARY KEY (`id_miembroxtrayecto`),
  ADD KEY `miembroxtrayecto_id_trayecto_trayecto` (`id_trayecto`),
  ADD KEY `miembroxtrayecto_id_miembro_miembro` (`id_miembro`);

--
-- Indices de la tabla `organizacion`
--
ALTER TABLE `organizacion`
  ADD PRIMARY KEY (`id_organizacion`),
  ADD KEY `org_id_Agente_agente` (`id_Agente`);

--
-- Indices de la tabla `paradatransportepublico`
--
ALTER TABLE `paradatransportepublico`
  ADD PRIMARY KEY (`id_parada`),
  ADD KEY `parada_id_transporte_mediotransporte` (`id_transporte`),
  ADD KEY `parada_id_transporte_estacion` (`id_estacion`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id_persona`);

--
-- Indices de la tabla `reporteagentesectorial`
--
ALTER TABLE `reporteagentesectorial`
  ADD PRIMARY KEY (`id_reporteagente`),
  ADD KEY `reporte_id_agente_agente` (`id_agente`);

--
-- Indices de la tabla `reportehcexcel`
--
ALTER TABLE `reportehcexcel`
  ADD PRIMARY KEY (`id_reportehcexcel`),
  ADD KEY `reporte_id_organizacion_org` (`id_organizacion`);

--
-- Indices de la tabla `reportehcorganizacion`
--
ALTER TABLE `reportehcorganizacion`
  ADD PRIMARY KEY (`id_ReporteOrganizacion`),
  ADD KEY `reporteorg_id_organizacion_org` (`id_organizacion`);

--
-- Indices de la tabla `sector`
--
ALTER TABLE `sector`
  ADD PRIMARY KEY (`id_sector`),
  ADD KEY `sector_id_espacio_espacio` (`id_espacio`),
  ADD KEY `sector_id_org_org` (`id_organizacion`);

--
-- Indices de la tabla `tramo`
--
ALTER TABLE `tramo`
  ADD PRIMARY KEY (`id_tramo`),
  ADD KEY `trampo_id_Transporte_mediotransporte` (`id_transporte`);

--
-- Indices de la tabla `transportepublico`
--
ALTER TABLE `transportepublico`
  ADD PRIMARY KEY (`id_transporte`);

--
-- Indices de la tabla `trayecto`
--
ALTER TABLE `trayecto`
  ADD PRIMARY KEY (`id_trayecto`);

--
-- Indices de la tabla `trayectoxtramo`
--
ALTER TABLE `trayectoxtramo`
  ADD PRIMARY KEY (`id_trayectoxtramo`),
  ADD KEY `tramoxtrayecto_id_tramo_tramo` (`id_tramo`),
  ADD KEY `tramoxtrayecto_id_tramo_trayecto` (`id_trayecto`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `usuario_id_persona_persona` (`id_persona`);

--
-- Indices de la tabla `vehiculoparticular`
--
ALTER TABLE `vehiculoparticular`
  ADD PRIMARY KEY (`id_transporte`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD CONSTRAINT `direccion_id_espacio_espacio` FOREIGN KEY (`id_espacio`) REFERENCES `espacio` (`id_espacio`);

--
-- Filtros para la tabla `estacion`
--
ALTER TABLE `estacion`
  ADD CONSTRAINT `estacion_id_espacio_espacio` FOREIGN KEY (`id_espacio`) REFERENCES `espacio` (`id_espacio`);

--
-- Filtros para la tabla `factoremision`
--
ALTER TABLE `factoremision`
  ADD CONSTRAINT `factor_id_usuario_usuario` FOREIGN KEY (`id_usuario_update`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `miembro`
--
ALTER TABLE `miembro`
  ADD CONSTRAINT `miembro_id_persona_persona` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`),
  ADD CONSTRAINT `miembro_id_sector_sector` FOREIGN KEY (`id_sector`) REFERENCES `sector` (`id_sector`);

--
-- Filtros para la tabla `miembroxtrayecto`
--
ALTER TABLE `miembroxtrayecto`
  ADD CONSTRAINT `miembroxtrayecto_id_miembro_miembro` FOREIGN KEY (`id_miembro`) REFERENCES `miembro` (`id_miembro`),
  ADD CONSTRAINT `miembroxtrayecto_id_trayecto_trayecto` FOREIGN KEY (`id_trayecto`) REFERENCES `trayecto` (`id_trayecto`);

--
-- Filtros para la tabla `organizacion`
--
ALTER TABLE `organizacion`
  ADD CONSTRAINT `org_id_Agente_agente` FOREIGN KEY (`id_Agente`) REFERENCES `agentesectorial` (`id_agente`);

--
-- Filtros para la tabla `paradatransportepublico`
--
ALTER TABLE `paradatransportepublico`
  ADD CONSTRAINT `parada_id_transporte_estacion` FOREIGN KEY (`id_estacion`) REFERENCES `estacion` (`id_estacion`),
  ADD CONSTRAINT `parada_id_transporte_mediotransporte` FOREIGN KEY (`id_transporte`) REFERENCES `mediotransporte` (`id_transporte`);

--
-- Filtros para la tabla `reporteagentesectorial`
--
ALTER TABLE `reporteagentesectorial`
  ADD CONSTRAINT `reporte_id_agente_agente` FOREIGN KEY (`id_agente`) REFERENCES `agentesectorial` (`id_agente`);

--
-- Filtros para la tabla `reportehcexcel`
--
ALTER TABLE `reportehcexcel`
  ADD CONSTRAINT `reporte_id_organizacion_org` FOREIGN KEY (`id_organizacion`) REFERENCES `organizacion` (`id_organizacion`);

--
-- Filtros para la tabla `reportehcorganizacion`
--
ALTER TABLE `reportehcorganizacion`
  ADD CONSTRAINT `reporteorg_id_organizacion_org` FOREIGN KEY (`id_organizacion`) REFERENCES `organizacion` (`id_organizacion`);

--
-- Filtros para la tabla `sector`
--
ALTER TABLE `sector`
  ADD CONSTRAINT `sector_id_espacio_espacio` FOREIGN KEY (`id_espacio`) REFERENCES `espacio` (`id_espacio`),
  ADD CONSTRAINT `sector_id_org_org` FOREIGN KEY (`id_organizacion`) REFERENCES `organizacion` (`id_organizacion`);

--
-- Filtros para la tabla `tramo`
--
ALTER TABLE `tramo`
  ADD CONSTRAINT `trampo_id_Transporte_mediotransporte` FOREIGN KEY (`id_transporte`) REFERENCES `mediotransporte` (`id_transporte`);

--
-- Filtros para la tabla `transportepublico`
--
ALTER TABLE `transportepublico`
  ADD CONSTRAINT `transportePublico_id_transporte_medioTransporte` FOREIGN KEY (`id_transporte`) REFERENCES `mediotransporte` (`id_transporte`);

--
-- Filtros para la tabla `trayectoxtramo`
--
ALTER TABLE `trayectoxtramo`
  ADD CONSTRAINT `tramoxtrayecto_id_tramo_tramo` FOREIGN KEY (`id_tramo`) REFERENCES `tramo` (`id_tramo`),
  ADD CONSTRAINT `tramoxtrayecto_id_tramo_trayecto` FOREIGN KEY (`id_trayecto`) REFERENCES `trayecto` (`id_trayecto`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_id_persona_persona` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`);

--
-- Filtros para la tabla `vehiculoparticular`
--
ALTER TABLE `vehiculoparticular`
  ADD CONSTRAINT `vehiculoParticular_id_Transporte_medioTransporte` FOREIGN KEY (`id_transporte`) REFERENCES `mediotransporte` (`id_transporte`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
