CREATE TABLE customer_address (
    delivery_address_id         SERIAL       NOT NULL,
    customer_id                 INT          NULL,
    available_delivery_area_id  INT          NULL,
    address_id                  INT          NOT NULL,
    is_default                  BOOLEAN      NOT NULL,
    PRIMARY KEY (delivery_address_id),
    CONSTRAINT fk_customer_address_customer
        FOREIGN KEY (customer_id)
            REFERENCES customer(customer_id) ON DELETE SET NULL,
    CONSTRAINT fk_customer_address_delivery_area
        FOREIGN KEY (available_delivery_area_id)
            REFERENCES available_delivery_area(available_delivery_area_id) ON DELETE SET NULL,
    CONSTRAINT fk_customer_address_address
        FOREIGN KEY (address_id)
            REFERENCES address(address_id) ON DELETE CASCADE
);