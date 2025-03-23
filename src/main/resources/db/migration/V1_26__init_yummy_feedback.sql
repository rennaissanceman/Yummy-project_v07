CREATE TABLE feedback (
    feedback_id      SERIAL                     NOT NULL,
    rating           NUMERIC(10, 2)             NOT NULL,
    comments         TEXT                       NULL,
    feedback_date    TIMESTAMP WITH TIME ZONE   NOT NULL,
    courier_id       INT                        NOT NULL,
    orders_id        INT                        NOT NULL,
    restaurant_id    INT                        NOT NULL,
    PRIMARY KEY (feedback_id),
    CONSTRAINT fk_feedback_courier
        FOREIGN KEY (courier_id)
            REFERENCES courier(courier_id) ON DELETE CASCADE,
    CONSTRAINT fk_feedback_orders
        FOREIGN KEY (orders_id)
            REFERENCES orders(orders_id) ON DELETE CASCADE
    CONSTRAINT fk_feedback_restaurant
        FOREIGN KEY (restaurant_id)
            REFERENCES restaurant(restaurant_id) ON DELETE CASCADE
);