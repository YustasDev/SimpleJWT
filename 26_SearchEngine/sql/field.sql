CREATE TABLE IF NOT EXISTS search_engine.field(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    selector VARCHAR(255) NOT NULL,
    weight DOUBLE NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

-- name VARCHAR(255) NOT NULL — имя поля;
-- selector VARCHAR(255) NOT NULL — CSS-выражение, позволяющее получить содержимое конкретного поля;
-- weight FLOAT NOT NULL — релевантность (вес) поля от 0 до 1.

