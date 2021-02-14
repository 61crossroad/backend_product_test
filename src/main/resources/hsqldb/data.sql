-- INSERT INTO product VALUES(1, '디코드상품', 100000);
INSERT INTO category VALUES(1, '신발');
INSERT INTO category VALUES(2, '가방');
INSERT INTO category VALUES(3, '티셔츠');
INSERT INTO category VALUES(4, '바지');
INSERT INTO category VALUES(5, '모자');
INSERT INTO category VALUES(6, '반지');

INSERT INTO product (id, name, price, quantity) VALUES(1, '상품-카테고리-X', 100000, 1);
INSERT INTO product VALUES(2, '상품-신발-A', 200000, 22, 1);
INSERT INTO product VALUES(3, '상품-가방-A', 300000, 33, 2);
INSERT INTO product VALUES(4, '상품-티셔츠-A', 400000, 44, 3);
INSERT INTO product VALUES(5, '상품-바지-A', 500000, 55, 4);
INSERT INTO product VALUES(6, '상품-모자-A', 600000, 66, 5);
INSERT INTO product VALUES(7, '상품-반지-A', 700000, 77, 6);
INSERT INTO product VALUES(8, '상품-티셔츠-B', 800000, 1, 3);
INSERT INTO product VALUES(9, '상품-모자-B', 9000, 0, 5);
INSERT INTO product VALUES(10, '상품-신발-B', 10100, 0, 1);
INSERT INTO product VALUES(11, '상품-가방B', 1110111, 0, 2);

INSERT INTO product_type VALUES(1, '단품상품');
INSERT INTO product_type VALUES(2, '1+1상품');
INSERT INTO product_type VALUES(3, '묶음상품');
INSERT INTO product_type VALUES(4, '옵션 상품');
INSERT INTO product_type VALUES(5, '묶음상품 (티셔츠+모자)');
INSERT INTO product_type VALUES(6, '옵션 상품 3개');

-- M: 대표상품, S: 서브상품, O: 옵션상품
-- 상품 형태별로 상품들의 순서는 테이블에 저장된 순서와 같다고 가정했습니다.
-- 그러므로 각 상품 형태에서 대표상품이 항상 첫번째 row로 조회되며, 순서에 관한 처리는 따로 하지 않았습니다.
INSERT INTO type_desc VALUES(1, 2, 3, 'M', 0);
INSERT INTO type_desc VALUES(3, 3, 4, 'M', 0);
INSERT INTO type_desc VALUES(4, 3, 5, 'S', 0);
INSERT INTO type_desc VALUES(5, 4, 2, 'M', 0);
INSERT INTO type_desc VALUES(6, 4, 1, 'O', 10);
INSERT INTO type_desc VALUES(7, 5, 3, 'M', 0);
INSERT INTO type_desc VALUES(8, 5, 5, 'S', 0);
INSERT INTO type_desc VALUES(9, 6, 6, 'M', 0);
INSERT INTO type_desc VALUES(10, 6, 2, 'O', 0);
INSERT INTO type_desc VALUES(11, 6, 3, 'O', 0);
