CREATE TABLE IF NOT EXISTS order_items(
	id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	product_id BIGINT NOT NULL,
	order_id BIGINT NOT NULL,
	unit_price BIGINT NOT NULL CHECK(unit_price >= 0),
	quantity INT NOT NULL CHECK(quantity > 0),
	created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
	CONSTRAINT fk_order_items_products FOREIGN KEY(product_id) REFERENCES products(id),
	CONSTRAINT fk_order_items_orders FOREIGN KEY(order_id) REFERENCES orders(id),
	CONSTRAINT uk_order_items_order_id_product_id UNIQUE (order_id, product_id)
);