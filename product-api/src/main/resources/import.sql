-- @formatter:off

INSERT INTO category (description) VALUES ( 'Comic Books');
INSERT INTO category (description) VALUES ( 'Movies');
INSERT INTO category (description) VALUES ( 'Books');

INSERT INTO supplier (name) VALUES ( 'Panini Comics');
INSERT INTO supplier (name) VALUES ( 'Amazon');

INSERT INTO product (name, quantity_available, category_id, supplier_id, created_at) VALUES ('Crise nas Infinitas Terras', 10, 1, 1,current_timestamp);
INSERT INTO product (name, quantity_available, category_id, supplier_id, created_at) VALUES ('Interestelar', 5, 2, 2, current_timestamp);
INSERT INTO product (name, quantity_available, category_id, supplier_id, created_at) VALUES ('Harry Potter E A Pedra Filosofal', 3, 3, 2, current_timestamp);

-- @formatter:on
