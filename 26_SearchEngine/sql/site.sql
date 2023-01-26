CREATE TABLE `site` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `status` enum('INDEXING','INDEXED','FAILED') NOT NULL,
                        `status_time` datetime NOT NULL,
                        `last_error` text,
                        `url` varchar(255) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




