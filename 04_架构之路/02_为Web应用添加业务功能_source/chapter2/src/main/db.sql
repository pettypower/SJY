CREATE TABLE `chapter2`.`customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NULL,
  `contact` varchar(255) NULL,
  `telephone` varchar(255) NULL,
  `email` varchar(255) NULL,
  `remark` text NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `customer` VALUES ('1', 'customer1', 'Jack','13512345678','jack@gmail.com',null);
INSERT INTO `customer` VALUES ('2', 'customer2', 'Rose','13623457896','rose@gmail.com',null);
