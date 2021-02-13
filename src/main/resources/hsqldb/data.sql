-- INSERT INTO product VALUES(1, '디코드상품', 100000);

INSERT INTO composition_type VALUES (1, '단일상품');
INSERT INTO composition_type VALUES (2, '1+1상품');
INSERT INTO composition_type VALUES (3, '묶음상품');
INSERT INTO composition_type VALUES (4, '옵션 상품');

INSERT INTO product VALUES (1, '신발 1', 88888, 88, 'shoes');
INSERT INTO product VALUES (2, '신발 2', 222222, 0, 'shoes');
INSERT INTO product VALUES (3, '가방 1', 333333, 333, 'bag');
INSERT INTO product VALUES (4, '티셔츠!!', 55555, 55, 'shirt');
INSERT INTO product VALUES (5, '바지', 77777, 77, 'pants');
INSERT INTO product VALUES (6, '모자', 11111, 11, 'cap');
INSERT INTO product VALUES (7, '반지', 999999, 99, 'ring');

-- (seq, comp_id, type_id, product_id, represent, optional, discount)
INSERT INTO product_composition VALUES (1, 1, 1, 1, true, false, 0);
INSERT INTO product_composition VALUES (2, 2, 1, 2, true, false, 0);
INSERT INTO product_composition VALUES (3, 3, 1, 3, true, false, 0);
INSERT INTO product_composition VALUES (4, 4, 1, 4, true, false, 0);
INSERT INTO product_composition VALUES (5, 5, 1, 5, true, false, 0);
INSERT INTO product_composition VALUES (6, 6, 1, 6, true, false, 0);
INSERT INTO product_composition VALUES (7, 7, 1, 7, true, false, 0);

INSERT INTO product_composition VALUES (8, 8, 2, 4, true, false, 0);

INSERT INTO product_composition VALUES (9, 9, 3, 5, true, false, 0);
INSERT INTO product_composition VALUES (10, 9, 3, 6, false, false, 1111);

INSERT INTO product_composition VALUES (11, 10, 4, 3, false, true, 0);
INSERT INTO product_composition VALUES (12, 10, 4, 2, true, false, 0);

INSERT INTO product_composition VALUES (13, 11, 3, 2, false, false, 0);
INSERT INTO product_composition VALUES (14, 11, 3, 4, true, false, 0);
INSERT INTO product_composition VALUES (15, 11, 3, 5, false, false, 0);

INSERT INTO product_composition VALUES (16, 12, 2, 6, true, false, 0);
