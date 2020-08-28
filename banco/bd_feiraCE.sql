-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.13-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para bd_feirace
DROP DATABASE IF EXISTS `bd_feirace`;
CREATE DATABASE IF NOT EXISTS `bd_feirace` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `bd_feirace`;

-- Copiando estrutura para tabela bd_feirace.clientes
DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `codi_clie` bigint(20) NOT NULL AUTO_INCREMENT,
  `cele_clie` varchar(255) DEFAULT NULL,
  `cida_clie` varchar(255) DEFAULT NULL,
  `cpf_clie` varchar(255) DEFAULT NULL,
  `ende_clie` varchar(255) DEFAULT NULL,
  `esta_clie` varchar(255) DEFAULT NULL,
  `nasc_clie` date DEFAULT NULL,
  `nome_clie` varchar(255) DEFAULT NULL,
  `rg_clie` varchar(255) DEFAULT NULL,
  `tele_clie` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codi_clie`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Copiando dados para a tabela bd_feirace.clientes: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
REPLACE INTO `clientes` (`codi_clie`, `cele_clie`, `cida_clie`, `cpf_clie`, `ende_clie`, `esta_clie`, `nasc_clie`, `nome_clie`, `rg_clie`, `tele_clie`) VALUES
	(1, '(16) 99294-1653', 'Orlândia', '507.480.968-17', 'Avenida E - 1355', 'SP', '2002-06-09', 'Caio Silveira', '57.517.297-6', '(16) 3826-2607');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
