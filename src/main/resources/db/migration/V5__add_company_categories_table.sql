CREATE TABLE company_category (
    id INTEGER NOT NULL CONSTRAINT company_category_id_pkey PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    url VARCHAR(50) NOT NULL
);

INSERT INTO company_category(id, title, url) VALUES (1, 'О компании', '/o_kompanii');
INSERT INTO company_category(id, title, url) VALUES (2, 'Оптовикам', '/optovikam');
INSERT INTO company_category(id, title, url) VALUES (3, 'Контакты', '/kontakti');