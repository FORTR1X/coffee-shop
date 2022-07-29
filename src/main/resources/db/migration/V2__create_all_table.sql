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
    CONSTRAINT fk_subcategory_category_id FOREIGN KEY(category_id) REFERENCES category(id)
);

CREATE TABLE product
(
    id INTEGER NOT NULL CONSTRAINT product_id_pkey PRIMARY KEY,
    header VARCHAR(255) NOT NULL,
    price INTEGER NOT NULL,
    description VARCHAR NOT NULL,cd 
    subcat_id INTEGER NOT NULL,
    CONSTRAINT fk_product_id FOREIGN KEY(subcat_id) REFERENCES subcategory(id)
);

CREATE TABlE filter_tea
(
    id INTEGER NOT NULL CONSTRAINT filter_tea_pkey PRIMARY KEY,
    country VARCHAR(50)
);
INSERT INTO filter_tea(id, country) VALUES (0, 'Страна');

CREATE TABlE filter_coffee
(
    id INTEGER NOT NULL CONSTRAINT filter_coffee_pkey PRIMARY KEY,
    continent VARCHAR(50)
);
INSERT INTO filter_coffee(id, continent) VALUES (0, 'Континент');