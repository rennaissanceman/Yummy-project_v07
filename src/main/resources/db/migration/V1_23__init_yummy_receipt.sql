CREATE TABLE receipt (
    receipt_id      SERIAL          NOT NULL,
    receipt_number  VARCHAR(255)    NOT NULL,
    orders_id        INT             NULL,
    issue_date      TIMESTAMP WITH TIME ZONE NULL,
    sale_date       TIMESTAMP WITH TIME ZONE NULL,
    total_amount    NUMERIC(10, 2)  NULL,
    net_amount      NUMERIC(10, 2)  NULL,
    tax_amount      NUMERIC(10, 2)  NULL,
    tax_rate        NUMERIC(5, 2)   NULL,
    payment_id      INT             NULL,
    notes           TEXT            NULL,
    PRIMARY KEY (receipt_id),
    UNIQUE (receipt_number),
    CONSTRAINT fk_receipt_orders
        FOREIGN KEY (orders_id)
            REFERENCES orders(orders_id) ON DELETE SET NULL,
    CONSTRAINT fk_receipt_payment
        FOREIGN KEY (payment_id)
            REFERENCES payment(payment_id) ON DELETE SET NULL
);