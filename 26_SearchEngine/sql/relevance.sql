CREATE TABLE IF NOT EXISTS search_engine.relevance(
    id INT PRIMARY KEY AUTO_INCREMENT,
    page INT NOT NULL,
    absolute_relevance DOUBLE NOT NULL,
    relative_relevance DOUBLE NOT NULL
    )
    ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_0900_ai_ci;

-- page — id страницы;
-- absolute_relevance — абсолютная релевантность страницы;
-- relative_relevance — относительная релевантность страницы;
-- * релевантность (актуальность) страницы ==> от 0 до 1.



