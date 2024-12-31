CREATE TABLE menu (
    menu_id          SERIAL          NOT NULL,
    restaurant_id    INT             NULL,
    menu_name        VARCHAR(255)    NOT NULL,
    description      TEXT            NULL,
    valid_from       TIMESTAMP WITH TIME ZONE NULL,
    valid_to         TIMESTAMP WITH TIME ZONE NULL,
    created_at       TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at       TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY (menu_id),
    UNIQUE (menu_name),
    CONSTRAINT fk_menu_restaurant
        FOREIGN KEY (restaurant_id)
            REFERENCES restaurant(restaurant_id) ON DELETE SET NULL
);