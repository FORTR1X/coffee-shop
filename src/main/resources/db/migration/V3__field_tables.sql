INSERT INTO category(id, title, url) VALUES (1, 'Аксессуары', '/accessuari');
INSERT INTO category(id, title, url) VALUES (2, 'Кофе', '/coffee');
INSERT INTO category(id, title, url) VALUES (3, 'Чай', '/chay');
INSERT INTO category(id, title, url) VALUES (4, 'Посуда', '/posuda');

INSERT INTO subcategory(id, title, url, category_id) VALUES (1, 'Моносорта', '/monosorta', 2);
INSERT INTO subcategory(id, title, url, category_id) VALUES (2, 'Смеси', '/smesi', 2);
INSERT INTO subcategory(id, title, url, category_id) VALUES (3, 'Чёрный', '/cherniy', 3);
INSERT INTO subcategory(id, title, url, category_id) VALUES (4, 'Зелёный', '/zeleniy', 3);
INSERT INTO subcategory(id, title, url, category_id) VALUES (5, 'Улун', '/ulun', 3);
INSERT INTO subcategory(id, title, url, category_id) VALUES (6, 'Белый', '/beliy', 3);
INSERT INTO subcategory(id, title, url, category_id) VALUES (7, 'Пуэр', '/puer', 3);
INSERT INTO subcategory(id, title, url, category_id) VALUES (8, 'Травяные', '/travyanie', 3);
INSERT INTO subcategory(id, title, url, category_id) VALUES (9, 'Красный', '/krasniy', 3);