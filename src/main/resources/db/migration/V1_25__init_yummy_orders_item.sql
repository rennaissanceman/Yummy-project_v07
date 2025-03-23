CREATE TABLE orders_item (
    orders_item_id   SERIAL          NOT NULL,
    orders_id        INT             NULL,
    menu_item_id     INT             NULL,
    item_name        VARCHAR(255)    NOT NULL,
    quantity         INT             NOT NULL,
    unit_price       NUMERIC(10, 2)  NOT NULL,
    total_price      NUMERIC(10, 2)  NOT NULL,
    item_notes       TEXT            NULL,
    PRIMARY KEY (orders_item_id),
    CONSTRAINT fk_orders_item_orders
        FOREIGN KEY (orders_id)
            REFERENCES orders(orders_id) ON DELETE CASCADE,
    CONSTRAINT fk_orders_item_menu_item
        FOREIGN KEY (menu_item_id)
            REFERENCES menu_item(menu_item_id) ON DELETE SET NULL
);