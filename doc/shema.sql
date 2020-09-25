CREATE DATABASE `poseidon` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE poseidon;

CREATE TABLE `users` (
  `id` int NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into users(id, fullname, username, password, role) values(1,"Administrator", "admin", "$2a$10$/Oa835whQGgbbHEl4lyyDe0IWlREZb69.ikdiCzUco81yOwx3W07m", "ADMIN");
insert into users(id, fullname, username, password, role) values(2, "User", "user", "$2a$10$/Oa835whQGgbbHEl4lyyDe0IWlREZb69.ikdiCzUco81yOwx3W07m", "USER");

CREATE TABLE `bid_list` (
  `bid_list_id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(30) NOT NULL,
  `ask` decimal(16,4) DEFAULT NULL,
  `ask_quantity` decimal(16,4) DEFAULT NULL,
  `benchmark` varchar(125) DEFAULT NULL,
  `bid` decimal(16,4) DEFAULT NULL,
  `bid_list_date` datetime(6) DEFAULT NULL,
  `bid_quantity` decimal(16,4) NOT NULL,
  `book` varchar(125) DEFAULT NULL,
  `commentary` varchar(125) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `creation_name` varchar(125) DEFAULT NULL,
  `deal_name` varchar(125) DEFAULT NULL,
  `deal_type` varchar(125) DEFAULT NULL,
  `revision_date` datetime(6) DEFAULT NULL,
  `revision_name` varchar(125) DEFAULT NULL,
  `security` varchar(125) DEFAULT NULL,
  `side` varchar(125) DEFAULT NULL,
  `source_list_id` varchar(125) DEFAULT NULL,
  `status` varchar(125) DEFAULT NULL,
  `trader` varchar(125) DEFAULT NULL,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`bid_list_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO bid_list (bid_list_id, account, ask, ask_quantity, benchmark, bid, bid_list_date, bid_quantity, 
book, commentary, creation_date, creation_name, deal_name, deal_type, revision_date, revision_name, security, side, source_list_id, status, trader, type) 
values (3, 'Account 1', 13.0000, 560.0000, 'Benchmark 1', 12.5000, '2019-10-22 16:35:00.000000', 124.0000, 'Book One', 'Comment 1', '2020-09-06 01:18:15.980000', 'administrator', 'The name of the big deal', 'The big deal type', '2020-09-06 01:18:15.980000', 'user', 'Security 1', 'Side 1', 'SL0000028', 'Status 1', 'John Trader', 'Type 1'),
(4,'Account 2', 55.0000, 1256.0000, 'Benchmark 2', 53.0000, '2020-12-05 16:45:00.000000', 223.0000, 'Book Two', 'Comment 2', '2020-09-06 01:18:15.980000', 'administrator', 'The name of the deal', 'The deal type', '2020-09-03 19:18:24.233000', 'user', 'Security 2', 'Side 2', 'SL0000078', 'Status 2', 'Paul Bismuth', 'Type 2');


CREATE TABLE `curve_point` (
  `curve_point_id` int NOT NULL AUTO_INCREMENT,
  `as_of_date` datetime(6) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `curve_id` int NOT NULL,
  `term` decimal(16,4) DEFAULT NULL,
  `value` decimal(16,4) DEFAULT NULL,
  PRIMARY KEY (`curve_point_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rating` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fitch_rating` varchar(125) DEFAULT NULL,
  `moodys_rating` varchar(125) DEFAULT NULL,
  `order_number` int DEFAULT NULL,
  `stand_poors_rating` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rule_name` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(125) DEFAULT NULL,
  `json` varchar(125) DEFAULT NULL,
  `name` varchar(125) DEFAULT NULL,
  `sql_part` varchar(125) DEFAULT NULL,
  `sql_str` varchar(125) DEFAULT NULL,
  `template` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `trade` (
  `trade_id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(30) DEFAULT NULL,
  `benchmark` varchar(125) DEFAULT NULL,
  `book` varchar(125) DEFAULT NULL,
  `buy_price` decimal(16,4) DEFAULT NULL,
  `buy_quantity` decimal(16,4) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `creation_name` varchar(125) DEFAULT NULL,
  `deal_name` varchar(125) DEFAULT NULL,
  `deal_type` varchar(125) DEFAULT NULL,
  `revision_date` datetime(6) DEFAULT NULL,
  `revision_name` varchar(125) DEFAULT NULL,
  `security` varchar(125) DEFAULT NULL,
  `sell_price` decimal(16,4) DEFAULT NULL,
  `sell_quantity` decimal(16,4) DEFAULT NULL,
  `side` varchar(125) DEFAULT NULL,
  `source_list_id` varchar(125) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `trade_date` datetime(6) DEFAULT NULL,
  `trader` varchar(125) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`trade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

UPDATE hibernate_sequence
SET next_val = 11;
