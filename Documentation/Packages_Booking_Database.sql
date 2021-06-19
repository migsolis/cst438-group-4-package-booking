CREATE DATABASE  IF NOT EXISTS `package_booking`;

-- This table store all the booking information for site.
-- It is a many to one relationship with the user table.
USE `package_booking`;
DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `id` int NOT NULL,
  `car_info` varchar(255) DEFAULT NULL,
  `departure_date` datetime(6) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `flight_info` varchar(255) DEFAULT NULL,
  `hotel_info` varchar(255) DEFAULT NULL,
  `package_type` int DEFAULT '0',
  `return_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_price` double DEFAULT '0',
  `transaction_date` datetime(6) DEFAULT NULL,
  `travelers` int DEFAULT '0',
  `user_id` int NOT NULL,
  `flight_id` int DEFAULT '0',
  `rental_id` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK7udbel7q86k041591kj6lfmvw` (`user_id`),
  CONSTRAINT `FK7udbel7q86k041591kj6lfmvw` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

USE `package_booking`;

DROP TABLE IF EXISTS `roles`;

-- This table stores the differnt security roles on the site.
-- 
CREATE TABLE `roles` (
  `role_id` int NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
);
-- This table maps the different security roles to the user
-- The many to many relationship between the user and roles tables
-- allows user to have multiple roles
USE `package_booking`;
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`),
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
);

-- This table stores user information for the site
USE `package_booking`;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);
