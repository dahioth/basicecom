ALTER TABLE "order"
    DROP CONSTRAINT fk_order_on_user;

ALTER TABLE order_line
    DROP CONSTRAINT fk_orderline_on_order;

CREATE SEQUENCE IF NOT EXISTS orders_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE orders
(
    id           BIGINT NOT NULL,
    order_status VARCHAR(255),
    user_id      BIGINT,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

ALTER TABLE order_line
    ADD CONSTRAINT FK_ORDERLINE_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

DROP TABLE "order" CASCADE;

DROP SEQUENCE order_seq CASCADE;