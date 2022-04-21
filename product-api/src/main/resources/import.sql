-- @formatter:off

INSERT INTO category (id, description) VALUES (1, 'Comic Books');
INSERT INTO category (id, description) VALUES (2, 'Movies');
INSERT INTO category (id, description) VALUES (3, 'Books');

INSERT INTO supplier (id, name) VALUES (1, 'Panini Comics');
INSERT INTO supplier (id, name) VALUES (2, 'Amazon');

INSERT INTO product (id, name, quantity_available, category_id, supplier_id) VALUES (1, 'Crise nas Infinitas Terras', 10, 1, 1);
INSERT INTO product (id, name, quantity_available, category_id, supplier_id) VALUES (2, 'Interestelar', 5, 2, 2);
INSERT INTO product (id, name, quantity_available, category_id, supplier_id) VALUES (3, 'Harry Potter E A Pedra Filosofal', 3, 3, 2);

-- @formatter:on
