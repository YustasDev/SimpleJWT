CREATE TABLE search_engine.page (
	id INT auto_increment NOT NULL,
	`path` TEXT NOT NULL,
	code INT NOT NULL,
	content MEDIUMTEXT NOT NULL,
    lemmatized_content MEDIUMTEXT NOT NULL,
	CONSTRAINT page_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

alter table page add key path_index (path(512));

-- path TEXT NOT NULL — адрес страницы от корня сайта (должен начинаться со слеша, например: /news/372189/);
-- code INT NOT NULL — код ответа, полученный при запросе страницы (например, 200, 404, 500 или другие);
-- content MEDIUMTEXT NOT NULL — контент страницы (HTML-код).
