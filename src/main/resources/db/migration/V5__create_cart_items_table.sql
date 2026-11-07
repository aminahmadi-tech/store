CREATE TABLE IF NOT EXISTS cart_items(
	id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	cart_id BIGINT NOT NULL,
	product_id BIGINT NOT NULL,
	quantity INT NOT NULL CHECK(quantity > 0),
	unit_price BIGINT NOT NULL CHECK(unit_price >= 0),
	CONSTRAINT uk_cart_items_cart_id_product_id UNIQUE (cart_id, product_id),
	CONSTRAINT fk_cart_items_products FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
	CONSTRAINT fk_cart_items_carts FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE
);