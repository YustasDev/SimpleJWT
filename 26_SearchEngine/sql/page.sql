CREATE TABLE search_engine.page (
	id INT auto_increment NOT NULL,
	`path` TEXT NOT NULL,
	code INT NOT NULL,
	content MEDIUMTEXT NOT NULL,
	CONSTRAINT page_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;