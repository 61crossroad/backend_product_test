-- INSERT INTO product VALUES(1, '디코드상품', 100000);
INSERT INTO product VALUES (1, '신발 1', 88888, 88, 'shoes');
INSERT INTO product VALUES (2, '신발 2', 222222, 0, 'shoes');
INSERT INTO product VALUES (3, '가방 1', 333333, 333, 'bag');
INSERT INTO product VALUES (4, '티셔츠!!', 55555, 55, 'shirt');
INSERT INTO product VALUES (5, '바지', 77777, 77, 'pants');
INSERT INTO product VALUES (6, '모자', 11111, 111, 'cap');
INSERT INTO product VALUES (7, '반지', 999999, 99, 'ring');

INSERT INTO composition_type VALUES (1, '단일상품');
INSERT INTO composition_type VALUES (2, '1+1상품');
INSERT INTO composition_type VALUES (3, '묶음상품');
INSERT INTO composition_type VALUES (4, '옵션 상품');

INSERT INTO product_composition VALUES (1, 1, 7);

INSERT INTO product_composition VALUES (2, 2, 4);
INSERT INTO product_composition_rel VALUES (1, 2, 4, 100, 0);

INSERT INTO product_composition VALUES (3, 3, 5);
INSERT INTO product_composition_rel VALUES (2, 3, 6, 0, 0);

INSERT INTO product_composition VALUES (4, 4, 3);
INSERT INTO product_composition_rel VALUES (3, 4, 2, 12.5, 1);

-- SELECT pc.comp_id, ct.type_name, p.name, pcr_p.name FROM product_composition pc
-- INNER JOIN composition_type ct ON ct.type_id = pc.type_id
-- INNER JOIN product p ON p.id = pc.product_id
-- LEFT OUTER JOIN product_composition_rel pcr ON pcr.comp_id = pc.comp_id
-- LEFT OUTER JOIN product pcr_p ON pcr_p.id = pcr.product_id
-- WHERE pc.comp_id = 1;
