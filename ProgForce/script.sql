CREATE DATABASE testprogforcedb;
USE testprogforcedb;

CREATE TABLE `market`(
`id` INT AUTO_INCREMENT, 
`title` VARCHAR(25) NOT NULL,
PRIMARY KEY (`id`));

CREATE TABLE `category`(
`id` INT AUTO_INCREMENT, 
`title` VARCHAR(25) NOT NULL,
`market_id` INT NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY(`market_id`) REFERENCES `market`(`id`));

CREATE TABLE `product`(
`id` INT AUTO_INCREMENT, 
`title` VARCHAR(25) NOT NULL,
`price` FLOAT(10,2) NOT NULL,
`status` ENUM('available', 'absent', 'expected'),
`category_id` INT NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY(`category_id`) REFERENCES `category`(`id`));

INSERT INTO `market` (`title`) value ('Mercadona');
INSERT INTO `market` (`title`) value ('Electrotrade');

INSERT INTO `category` (`title`, `market_id`) VALUES
	('seafood', (SELECT `id` FROM `market` WHERE `title`='Mercadona'));
INSERT INTO `category` (`title`, `market_id`) VALUES
	('dairy', (SELECT `id` FROM `market` WHERE `title`='Mercadona'));
INSERT INTO `category` (`title`, `market_id`) VALUES
	('bakery', (SELECT `id` FROM `market` WHERE `title`='Mercadona'));
    
INSERT INTO `category` (`title`, `market_id`) VALUES
	('hifi', (SELECT `id` FROM `market` WHERE `title`='Electrotrade'));
INSERT INTO `category` (`title`, `market_id`) VALUES
	('computers', (SELECT `id` FROM `market` WHERE `title`='Electrotrade'));
INSERT INTO `category` (`title`, `market_id`) VALUES
	('appliances', (SELECT `id` FROM `market` WHERE `title`='Electrotrade'));
  
INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES
	('crab', '101.23', 'available', (SELECT `id` FROM `category` WHERE `title`='seafood'));
INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES
	('shrimp', '67.20', 'available', (SELECT `id` FROM `category` WHERE `title`='seafood'));
INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES
	('tuna', '176.00', 'available', (SELECT `id` FROM `category` WHERE `title`='seafood'));
    
INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES
	('milk', '7.20', 'available', (SELECT `id` FROM `category` WHERE `title`='dairy'));
INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES
	('yogurt', '12.05', 'available', (SELECT `id` FROM `category` WHERE `title`='dairy'));
INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES
	('cream', '10.35', 'available', (SELECT `id` FROM `category` WHERE `title`='dairy'));


INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES
	('bread', '4.84', 'available', (SELECT `id` FROM `category` WHERE `title`='bakery'));
INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES
	('bun', '3.05', 'available', (SELECT `id` FROM `category` WHERE `title`='bakery'));
    
INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES
	('player', '205.00', 'available', (SELECT `id` FROM `category` WHERE `title`='hifi'));
INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES
	('tv', '560.99', 'available', (SELECT `id` FROM `category` WHERE `title`='hifi'));
    
INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES
	('tablet', '320.80', 'available', (SELECT `id` FROM `category` WHERE `title`='computers'));
INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES
	('notebook', '860.00', 'available', (SELECT `id` FROM `category` WHERE `title`='computers'));
    
INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES
	('duster', '499.99', 'available', (SELECT `id` FROM `category` WHERE `title`='appliances'));
INSERT INTO `product` (`title`, `price`, `status`, `category_id`) VALUES
	('refrigerator', '1049.00', 'available', (SELECT `id` FROM `category` WHERE `title`='appliances'));
