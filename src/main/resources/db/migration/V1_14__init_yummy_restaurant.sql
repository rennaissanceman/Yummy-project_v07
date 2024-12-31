CREATE TABLE restaurant (
    restaurant_id     SERIAL           NOT NULL,
    restaurant_name   VARCHAR(255)     NOT NULL,
    owner_id          INT              NULL,
    address_id        INT              NOT NULL,
    phone             VARCHAR(50)      NOT NULL,
    email             VARCHAR(255)     NOT NULL,
    website           VARCHAR(255)     NULL,
    opening_hours     TEXT             NULL,
    cuisine_type      VARCHAR(50)      NOT NULL,
    average_rating    DOUBLE PRECISION NULL,
    rating_count      INT              NULL,
    description       TEXT             NULL,
    logo_url          VARCHAR(255)     NULL,
    PRIMARY KEY (restaurant_id),
    UNIQUE (restaurant_name),
    UNIQUE (phone),
    UNIQUE (email),
    UNIQUE (website),
    UNIQUE (logo_url),
    CONSTRAINT fk_restaurant_owner
        FOREIGN KEY (owner_id)
            REFERENCES owner(owner_id) ON DELETE SET NULL,
    CONSTRAINT fk_restaurant_address
        FOREIGN KEY (address_id)
            REFERENCES address(address_id) ON DELETE CASCADE
);