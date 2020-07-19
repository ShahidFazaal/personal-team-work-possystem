DROP DATABASE IF EXISTS possystem;
CREATE DATABASE IF NOT EXISTS possystem;
USE possystem;

DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
    customerId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    customerName varchar(100) NOT NULL,
    customerAddress varchar(100) NOT NULL,
    contactNo INT NOT NULL
);

DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
    itemId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    description varchar(100) NOT NULL,
    qty int NOT NULL,
    unitPrice double NOT NULL
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    userId int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userName varchar(20) NOT NULL,
    password varchar(15) NOT NULL
);