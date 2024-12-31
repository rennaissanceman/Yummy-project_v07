CREATE TABLE payment (
    payment_id          SERIAL          NOT NULL,
    orders_id           INT             NOT NULL,
    payment_method_id   INT             NULL,
    amount              NUMERIC(10, 2)  NOT NULL,
    payment_status      VARCHAR(50)     NOT NULL,
    transaction_id      VARCHAR(255)    NULL,
    created_at          TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at          TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY (payment_id),
    CONSTRAINT fk_payment_orders
        FOREIGN KEY (orders_id)
            REFERENCES orders(orders_id) ON DELETE SET NULL,
    CONSTRAINT fk_payment_method
        FOREIGN KEY (payment_method_id)
            REFERENCES payment_method(payment_method_id) ON DELETE SET NULL
);