
CREATE TABLE `node` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contact` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;



CREATE TABLE `graduated` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `node_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgjiwu77jcgsyw3b21xu69d72x` (`node_id`),
  CONSTRAINT `FKgjiwu77jcgsyw3b21xu69d72x` FOREIGN KEY (`node_id`) REFERENCES `node` (`id`)
) ENGINE=InnoDB;


CREATE TABLE `interview` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comentarios` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `graduated_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn2fk6g8eem4m48trru0r8rcji` (`company_id`),
  KEY `FK4isknufpp0gkgy33qppcmgk32` (`graduated_id`),
  CONSTRAINT `FK4isknufpp0gkgy33qppcmgk32` FOREIGN KEY (`graduated_id`) REFERENCES `graduated` (`id`),
  CONSTRAINT `FKn2fk6g8eem4m48trru0r8rcji` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB;

