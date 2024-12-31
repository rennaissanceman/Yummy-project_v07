CREATE TABLE courier (
    courier_id                SERIAL          NOT NULL,
    courier_number            VARCHAR(32)     NOT NULL,
    courier_name              VARCHAR(32)     NOT NULL,
    courier_surname           VARCHAR(32)     NOT NULL,
    user_auth_id              INT             NULL,
    vehicle_type              VARCHAR(32)     NOT NULL,
    vehicle_registration_number VARCHAR(32)   NOT NULL,
    average_ratings           DOUBLE PRECISION NOT NULL,
    delivery_count            INT             NOT NULL,
    hire_date                 TIME WITH TIME ZONE NOT NULL,
    PRIMARY KEY (courier_id),
    UNIQUE (courier_number),
    UNIQUE (vehicle_registration_number),
    CONSTRAINT fk_courier_user_auth
        FOREIGN KEY (user_auth_id)
            REFERENCES user_auth(user_auth_id) ON DELETE SET NULL
);