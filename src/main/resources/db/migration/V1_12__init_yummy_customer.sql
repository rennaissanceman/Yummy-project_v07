CREATE TABLE customer (
    customer_id       SERIAL          NOT NULL,
    customer_number   VARCHAR(50)     NOT NULL,
    is_company        BOOLEAN         NOT NULL,
    company_name      VARCHAR(255)    NULL,
    customer_name     VARCHAR(255)    NULL,
    customer_surname  VARCHAR(255)    NULL,
    user_auth_id      INT             NULL,
    PRIMARY KEY (customer_id),
    UNIQUE (customer_number),
    UNIQUE (company_name),
    CONSTRAINT fk_customer_user_auth
        FOREIGN KEY (user_auth_id)
            REFERENCES user_auth(user_auth_id) ON DELETE CASCADE
);