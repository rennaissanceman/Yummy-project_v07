CREATE TABLE payment_method (
    payment_method_id       SERIAL          NOT NULL,
    payment_method_name     VARCHAR(255)    NOT NULL,
    description             TEXT            NULL,
    is_active               BOOLEAN         NOT NULL,
    payment_method_status   VARCHAR(50)     NOT NULL,
    created_at              TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at              TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY (payment_method_id)
);