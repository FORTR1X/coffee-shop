CREATE TABLE category
(
    id INTEGER NOT NULL CONSTRAINT category_id_pkey PRIMARY KEY,
    title VARCHAR(255) NOT NULL CONSTRAINT category_title_key UNIQUE,
    URL VARCHAR(50) NOT NULL CONSTRAINT category_url_key UNIQUE
);

CREATE TABLE subcategory
(
    id INTEGER NOT NULL CONSTRAINT subcategory_id_pkey PRIMARY KEY,
    title VARCHAR(255) NOT NULL CONSTRAINT subcategory_title_key UNIQUE,
    url VARCHAR(50) NOT NULL CONSTRAINT subcategory_url_key UNIQUE,
    category_id INTEGER NOT NULL,
    CONSTRAINT fk_subcategory_cat_id FOREIGN KEY(category_id) REFERENCES category(id)
);

CREATE TABLE product
(
    id INTEGER NOT NULL CONSTRAINT product_id_pkey PRIMARY KEY,
    header VARCHAR(255) NOT NULL,
    price INTEGER NOT NULL,
    description TEXT NOT NULL,
    subcategory_id INTEGER NOT NULL,
    CONSTRAINT fk_product_id FOREIGN KEY(subcategory_id) REFERENCES subcategory(id)
);