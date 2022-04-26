CREATE TABLE IF NOT EXISTS search_engine.lemma (
    id INT PRIMARY KEY AUTO_INCREMENT,
    lemma VARCHAR(255) NOT NULL,
    frequency INT NOT NULL)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;
--
-- lemma VARCHAR(255) NOT NULL — нормальная форма слова;
-- frequency INT NOT NULL — количество страниц, на которых слово встречается хотя бы один раз.
--                          Максимальное значение не может превышать общее количество слов на сайте.
--
