CREATE TABLE invoice (
    invoice_id              SERIAL          NOT NULL,
    invoice_number          VARCHAR(32)     NOT NULL,
    orders_id                INT             NULL,
    issue_date              TIMESTAMP WITH TIME ZONE NOT NULL,
    sale_date               TIMESTAMP WITH TIME ZONE NOT NULL,
    total_amount            NUMERIC(10, 2)  NOT NULL,
    net_amount              NUMERIC(10, 2)  NOT NULL,
    tax_amount              NUMERIC(10, 2)  NOT NULL,
    tax_rate                NUMERIC(5, 2)   NOT NULL,
    payment_id              INT             NULL,
    billing_information_id  INT             NULL,
    notes                   TEXT            NULL,
    due_date                TIMESTAMP WITH TIME ZONE NOT NULL,
    issuer_signature        VARCHAR(255)    NOT NULL,
    PRIMARY KEY (invoice_id),
    UNIQUE (invoice_number),
    CONSTRAINT fk_invoice_orders
        FOREIGN KEY (orders_id)
            REFERENCES orders(orders_id) ON DELETE SET NULL,
    CONSTRAINT fk_invoice_payment
        FOREIGN KEY (payment_id)
            REFERENCES payment(payment_id) ON DELETE SET NULL,
    CONSTRAINT fk_invoice_billing_information
        FOREIGN KEY (billing_information_id)
            REFERENCES billing_information(billing_information_id) ON DELETE SET NULL
);