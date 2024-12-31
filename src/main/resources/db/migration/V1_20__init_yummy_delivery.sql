CREATE TABLE delivery (
    delivery_id                  SERIAL          NOT NULL,
    delivery_number              VARCHAR(32)     NOT NULL,
    orders_id                    INT             NULL,
    available_delivery_area_id   INT             NULL,
    courier_id                   INT             NULL,
    delivery_status              VARCHAR(32)     NOT NULL,
    start_time                   TIMESTAMP WITH TIME ZONE NULL,
    end_time                     TIMESTAMP WITH TIME ZONE NULL,
    estimated_delivery_time      TIMESTAMP WITH TIME ZONE NULL,
    actual_delivery_date_time    TIMESTAMP WITH TIME ZONE NULL,
    delivery_fee                 NUMERIC(10, 2)  NULL,
    delivery_notes               TEXT            NULL,
    PRIMARY KEY (delivery_id),
    UNIQUE (delivery_number),
    CONSTRAINT fk_delivery_orders
        FOREIGN KEY (orders_id)
            REFERENCES orders(orders_id) ON DELETE SET NULL,
    CONSTRAINT fk_delivery_area
        FOREIGN KEY (available_delivery_area_id)
            REFERENCES available_delivery_area(available_delivery_area_id) ON DELETE SET NULL,
    CONSTRAINT fk_delivery_courier
        FOREIGN KEY (courier_id)
            REFERENCES courier(courier_id) ON DELETE SET NULL
);