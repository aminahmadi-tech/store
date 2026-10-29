CREATE TABLE IF NOT EXISTS products(
	id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	description TEXT,
	price BIGINT NOT NULL CHECK(price >= 0),
	quantity INT NOT NULL CHECK(quantity >= 0),
	category_id SMALLINT NOT NULL,
	created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
	updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
	CONSTRAINT fk_products_categories FOREIGN KEY(category_id) REFERENCES categories(id)
);