CREATE TABLE orders (
    orders_id                  SERIAL          NOT NULL,
    orders_number              VARCHAR(255)    NOT NULL,
    customer_id                INT             NULL,
    menu_id                    INT             NULL,
    orders_date_time           TIMESTAMP WITH TIME ZONE NOT NULL,
    orders_status              VARCHAR(50)     NOT NULL,
    orders_description         TEXT            NULL,
    total_amount               NUMERIC(10, 2)  NOT NULL,
    available_delivery_area_id INT            NULL,
    customer_address_id        INT             NULL,
    PRIMARY KEY (orders_id),
    UNIQUE (orders_number),
    CONSTRAINT fk_orders_customer
        FOREIGN KEY (customer_id)
            REFERENCES customer(customer_id) ON DELETE SET NULL,
    CONSTRAINT fk_order_menu
        FOREIGN KEY (menu_id)
            REFERENCES menu(menu_id) ON DELETE SET NULL,
    CONSTRAINT fk_order_delivery_area
        FOREIGN KEY (available_delivery_area_id)
            REFERENCES available_delivery_area(available_delivery_area_id) ON DELETE SET NULL,
    CONSTRAINT fk_order_customer_address
        FOREIGN KEY (customer_address_id)
            REFERENCES customer_address(delivery_address_id) ON DELETE SET NULL
);