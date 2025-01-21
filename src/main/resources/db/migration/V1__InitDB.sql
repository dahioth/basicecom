CREATE SEQUENCE IF NOT EXISTS order_line_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS orders_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS product_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE order_line
(
    id                  BIGINT  NOT NULL,
    order_line_quantity INTEGER NOT NULL,
    product_id          BIGINT,
    order_id            BIGINT,
    CONSTRAINT pk_orderline PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id           BIGINT NOT NULL,
    order_status VARCHAR(255),
    user_id      BIGINT,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE product
(
    id                  BIGINT           NOT NULL,
    product_name        VARCHAR(255),
    product_description VARCHAR(255),
    product_price       DOUBLE PRECISION NOT NULL,
    product_quantity    INTEGER          NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         BIGINT NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    email      VARCHAR(255),
    password   VARCHAR(255),
    role       VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE order_line
    ADD CONSTRAINT FK_ORDERLINE_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE order_line
    ADD CONSTRAINT FK_ORDERLINE_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);