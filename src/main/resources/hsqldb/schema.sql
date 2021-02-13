DROP TABLE product IF EXISTS ;
DROP TABLE composition_type IF EXISTS ;
DROP TABLE product_composition IF EXISTS ;

CREATE TABLE product(
    id INTEGER NOT NULL,
    name VARCHAR(255),
    price INTEGER,
    quantity INTEGER NOT NULL,
    category VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE composition_type(
	type_id INTEGER NOT NULL,
	type_name VARCHAR(255) NOT NULL,
	PRIMARY KEY (type_id)
);

CREATE TABLE product_composition(
	seq INTEGER NOT NULL,
	comp_id INTEGER NOT NULL,
	type_id INTEGER NOT NULL,
	product_id INTEGER NOT NULL,
	represent TINYINT NOT NULL,
	optional TINYINT NOT NULL,
	discount INTEGER,
	PRIMARY KEY (seq)
);

ALTER TABLE product_composition ADD CONSTRAINT product_composition_type FOREIGN KEY (type_id)
REFERENCES composition_type(type_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE product_composition ADD CONSTRAINT product_composition_product FOREIGN KEY (product_id)
REFERENCES product(id) ON UPDATE CASCADE ON DELETE CASCADE;