-- INSERT INTO product VALUES(1, '디코드상품', 100000);
INSERT INTO category VALUES(1, '신발');
INSERT INTO category VALUES(2, '가방');
INSERT INTO category VALUES(3, '티셔츠');
INSERT INTO category VALUES(4, '바지');
INSERT INTO category VALUES(5, '모자');
INSERT INTO category VALUES(6, '반지');

INSERT INTO product VALUES(1, '상품-신발-A', 100000, 11, 1);
INSERT INTO product VALUES(2, '상품-가방-A', 200000, 22, 2);
INSERT INTO product VALUES(3, '상품-티셔츠-A', 300000, 33, 3);
INSERT INTO product VALUES(4, '상품-바지-A', 400000, 44, 4);
INSERT INTO product VALUES(5, '상품-모자-A', 500000, 55, 5);
INSERT INTO product VALUES(6, '상품-반지-A', 600000, 66, 6);

INSERT INTO product_type VALUES(1, '단품상품');
INSERT INTO product_type VALUES(2, '1+1상품');
INSERT INTO product_type VALUES(3, '묶음상품');
INSERT INTO product_type VALUES(4, '옵션 상품');

-- M: 메인, S: 서브, O: 옵션, N: 뷰에 표시 안 함
INSERT INTO type_desc VALUES(1, 2, 3, 'M', 0);
INSERT INTO type_desc VALUES(2, 2, 3, 'N', 100);
INSERT INTO type_desc VALUES(3, 3, 4, 'M', 0);
INSERT INTO type_desc VALUES(4, 3, 5, 'S', 0);
INSERT INTO type_desc VALUES(5, 4, 2, 'M', 0);
INSERT INTO type_desc VALUES(6, 4, 1, 'O', 0);
