DROP TABLE product IF EXISTS ;
DROP TABLE composition_type IF EXISTS ;
DROP TABLE product_composition IF EXISTS ;
DROP TABLE product_composition_rel IF EXISTS ;

CREATE TABLE product(
    id INTEGER NOT NULL,
    name VARCHAR(255),
    price INTEGER,
    quantity INTEGER,
    category VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE composition_type(
	type_id INTEGER NOT NULL,
	type_name VARCHAR(255) NOT NULL,
	PRIMARY KEY (type_id)
);

CREATE TABLE product_composition(
	comp_id INTEGER NOT NULL,
	type_id INTEGER,
	product_id INTEGER,
	PRIMARY KEY (comp_id)
);

CREATE TABLE product_composition_rel(
	rel_id INTEGER NOT NULL,
	comp_id INTEGER,
	product_id INTEGER,
	discount DECIMAL(5, 2),
	optional TINYINT,
	PRIMARY KEY (rel_id)
);

ALTER TABLE product_composition ADD CONSTRAINT product_composition_type FOREIGN KEY (type_id)
REFERENCES composition_type(type_id) ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE product_composition ADD CONSTRAINT product_composition_product FOREIGN KEY (product_id)
REFERENCES product(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE product_composition_rel ADD CONSTRAINT composition_rel FOREIGN KEY (comp_id)
REFERENCES product_composition(comp_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE product_composition_rel ADD CONSTRAINT composition_product_rel FOREIGN KEY (product_id)
REFERENCES product(id) ON UPDATE CASCADE ON DELETE CASCADE;
