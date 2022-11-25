INSERT INTO category(id, title, url)
VALUES (1, 'Аксессуары', '/accessuari'),
       (2, 'Кофе', '/coffee'),
       (3, 'Чай', '/chay'),
       (4, 'Посуда', '/posuda');

INSERT INTO subcategory(id, title, url, category_id)
VALUES (1, 'Моносорта', '/monosorta', 2),
       (2, 'Смеси', '/smesi', 2),
       (3, 'Чёрный', '/cherniy', 3),
       (4, 'Зелёный', '/zeleniy', 3),
       (5, 'Улун', '/ulun', 3),
       (6, 'Белый', '/beliy', 3),
       (7, 'Пуэр', '/puer', 3),
       (8, 'Травяные', '/travyanie', 3),
       (9, 'Красный', '/krasniy', 3);