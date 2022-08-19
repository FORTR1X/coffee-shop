CREATE TABLE best_sellers (
    id INTEGER NOT NULL CONSTRAINT best_sellers_id PRIMARY KEY,
    CONSTRAINT fk_best_sellers_id FOREIGN KEY(id) REFERENCES product(id)
)