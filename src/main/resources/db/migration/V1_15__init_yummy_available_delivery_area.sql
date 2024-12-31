CREATE TABLE available_delivery_area (
    available_delivery_area_id SERIAL          NOT NULL,
    restaurant_id              INT             NULL,
    address_id                 INT             NOT NULL,
    PRIMARY KEY (available_delivery_area_id),
    CONSTRAINT fk_available_delivery_area_restaurant
        FOREIGN KEY (restaurant_id)
            REFERENCES restaurant(restaurant_id) ON DELETE SET NULL,
    CONSTRAINT fk_available_delivery_area_address
        FOREIGN KEY (address_id)
            REFERENCES address(address_id) ON DELETE CASCADE
);