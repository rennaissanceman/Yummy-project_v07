CREATE TABLE menu_item (
    menu_item_id        SERIAL          NOT NULL,
    item_name           VARCHAR(255)    NOT NULL,
    menu_id             INT             NULL,
    description         TEXT            NULL,
    is_available        BOOLEAN         NOT NULL,
    diet_type           VARCHAR(50)     NOT NULL,
    calories            INT             NULL,
    ingredients         TEXT            NULL,
    portion_weight      VARCHAR(50)     NULL,
    preparation_time    INT             NULL,
    price               NUMERIC(10, 2)  NOT NULL,
    image_url           VARCHAR(255)    NOT NULL,
    display_order       INT             NOT NULL,
    created_at          TIMESTAMP WITH TIME ZONE NULL,
    updated_at          TIMESTAMP WITH TIME ZONE NULL,
    PRIMARY KEY (menu_item_id),
    UNIQUE (item_name),
    UNIQUE (image_url),
    CONSTRAINT fk_menu_item_menu
        FOREIGN KEY (menu_id)
            REFERENCES menu(menu_id) ON DELETE CASCADE
);