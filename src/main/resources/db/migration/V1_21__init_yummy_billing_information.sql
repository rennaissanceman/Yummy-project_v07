CREATE TABLE billing_information (
    billing_information_id SERIAL          NOT NULL,
    customer_id            INT             NULL,
    company_name           VARCHAR(32)     NOT NULL,
    vat_number             VARCHAR(32)     NOT NULL,
    address_id             INT             NOT NULL,
    PRIMARY KEY (billing_information_id),
    UNIQUE (company_name),
    UNIQUE (vat_number),
    CONSTRAINT fk_billing_information_customer
        FOREIGN KEY (customer_id)
            REFERENCES customer(customer_id) ON DELETE SET NULL,
    CONSTRAINT fk_billing_information_address
        FOREIGN KEY (address_id)
            REFERENCES address(address_id) ON DELETE CASCADE
);