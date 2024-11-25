CREATE TABLE address (
    address_id      SERIAL          NOT NULL,
    country         VARCHAR(32)     NOT NULL,
    city            VARCHAR(32)     NOT NULL,
    postal_code     VARCHAR(32)     NOT NULL,
    street          VARCHAR(32)     NOT NULL,
    PRIMARY KEY (address_id)
);

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

CREATE TABLE billing_information (
    billing_information_id SERIAL          NOT NULL,
    customer_id            INT             NULL,
    company_name           VARCHAR(32)     NOT NULL,
    vat_number             VARCHAR(32)     NOT NULL,
    address_id             INT             NOT NULL,
    PRIMARY KEY (billing_information_id),
    UNIQUE (company_name),
    UNIQUE (vat_number),
    CONSTRAINT fk_billing_information_customer
        FOREIGN KEY (customer_id)
            REFERENCES customer(customer_id) ON DELETE SET NULL,
    CONSTRAINT fk_billing_information_address
        FOREIGN KEY (address_id)
            REFERENCES address(address_id) ON DELETE CASCADE
);

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

CREATE TABLE delivery (
    delivery_id                  SERIAL          NOT NULL,
    delivery_number              VARCHAR(32)     NOT NULL,
    order_id                     INT             NULL,
    available_delivery_area_id   INT             NULL,
    courier_id                   INT             NULL,
    delivery_status              VARCHAR(32)     NULL,
    start_time                   TIMESTAMP WITH TIME ZONE NULL,
    end_time                     TIMESTAMP WITH TIME ZONE NULL,
    estimated_delivery_time      TIMESTAMP WITH TIME ZONE NULL,
    actual_delivery_date_time    TIMESTAMP WITH TIME ZONE NULL,
    delivery_fee                 NUMERIC(10, 2)  NULL,
    delivery_notes               TEXT            NULL,
    PRIMARY KEY (delivery_id),
    UNIQUE (delivery_number),
    CONSTRAINT fk_delivery_order
        FOREIGN KEY (order_id)
            REFERENCES orders(order_id) ON DELETE SET NULL,
    CONSTRAINT fk_delivery_area
        FOREIGN KEY (available_delivery_area_id)
            REFERENCES available_delivery_area(available_delivery_area_id) ON DELETE SET NULL,
    CONSTRAINT fk_delivery_courier
        FOREIGN KEY (courier_id)
            REFERENCES courier(courier_id) ON DELETE SET NULL
);

CREATE TABLE invoice (
    invoice_id              SERIAL          NOT NULL,
    invoice_number          VARCHAR(32)     NOT NULL,
    order_id                INT             NULL,
    issue_date              TIMESTAMP WITH TIME ZONE NOT NULL,
    sale_date               TIMESTAMP WITH TIME ZONE NOT NULL,
    total_amount            NUMERIC(10, 2)  NOT NULL,
    net_amount              NUMERIC(10, 2)  NOT NULL,
    tax_amount              NUMERIC(10, 2)  NOT NULL,
    tax_rate                NUMERIC(5, 2)   NOT NULL,
    payment_id              INT             NULL,
    billing_information_id  INT             NULL,
    notes                   TEXT            NULL,
    due_date                TIMESTAMP WITH TIME ZONE NOT NULL,
    issuer_signature        VARCHAR(255)    NOT NULL,
    PRIMARY KEY (invoice_id),
    UNIQUE (invoice_number),
    CONSTRAINT fk_invoice_order
        FOREIGN KEY (order_id)
            REFERENCES orders(order_id) ON DELETE SET NULL,
    CONSTRAINT fk_invoice_payment
        FOREIGN KEY (payment_id)
            REFERENCES payment(payment_id) ON DELETE SET NULL,
    CONSTRAINT fk_invoice_billing_information
        FOREIGN KEY (billing_information_id)
            REFERENCES billing_information(billing_information_id) ON DELETE SET NULL
);

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

CREATE TABLE menu_item (
    menu_item_id        SERIAL          NOT NULL,
    item_name           VARCHAR(255)    NOT NULL,
    menu_id             INT             NULL,
    description         TEXT            NULL,
    is_available        BOOLEAN         NOT NULL,
    diet_type           VARCHAR(50)     NULL,
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

CREATE TABLE orders (
    order_id                  SERIAL          NOT NULL,
    order_number              VARCHAR(255)    NOT NULL,
    customer_id               INT             NULL,
    menu_id                   INT             NULL,
    order_date_time           TIMESTAMP WITH TIME ZONE NOT NULL,
    order_status              VARCHAR(50)     NOT NULL,
    order_description         TEXT            NULL,
    total_amount              NUMERIC(10, 2)  NOT NULL,
    available_delivery_area_id INT            NULL,
    customer_address_id       INT             NULL,
    PRIMARY KEY (order_id),
    UNIQUE (order_number),
    CONSTRAINT fk_order_customer
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

CREATE TABLE order_item (
    order_item_id    SERIAL          NOT NULL,
    order_id         INT             NULL,
    menu_id          INT             NULL,
    item_name        VARCHAR(255)    NOT NULL,
    quantity         INT             NOT NULL,
    unit_price       NUMERIC(10, 2)  NOT NULL,
    total_price      NUMERIC(10, 2)  NOT NULL,
    item_notes       TEXT            NULL,
    PRIMARY KEY (order_item_id),
    UNIQUE (item_name),
    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id)
            REFERENCES orders(order_id) ON DELETE CASCADE,
    CONSTRAINT fk_order_item_menu
        FOREIGN KEY (menu_id)
            REFERENCES menu(menu_id) ON DELETE SET NULL
);

CREATE TABLE owner (
    owner_id       SERIAL          NOT NULL,
    owner_number   VARCHAR(50)     NOT NULL,
    owner_name     VARCHAR(255)    NOT NULL,
    user_auth_id   INT             NULL,
    PRIMARY KEY (owner_id),
    UNIQUE (owner_number),
    UNIQUE (owner_name),
    CONSTRAINT fk_owner_user_auth
        FOREIGN KEY (user_auth_id)
            REFERENCES user_auth(user_auth_id) ON DELETE SET NULL
);

CREATE TABLE payment (
    payment_id          SERIAL          NOT NULL,
    order_id            INT             NULL,
    payment_method_id   INT             NULL,
    amount              NUMERIC(10, 2)  NOT NULL,
    payment_status      VARCHAR(50)     NOT NULL,
    transaction_id      VARCHAR(255)    NULL,
    created_at          TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at          TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY (payment_id),
    CONSTRAINT fk_payment_order
        FOREIGN KEY (order_id)
            REFERENCES orders(order_id) ON DELETE SET NULL,
    CONSTRAINT fk_payment_method
        FOREIGN KEY (payment_method_id)
            REFERENCES payment_method(payment_method_id) ON DELETE SET NULL
);

CREATE TABLE payment_method (
    payment_method_id       SERIAL          NOT NULL,
    payment_method_name     VARCHAR(255)    NOT NULL,
    description             TEXT            NULL,
    is_active               BOOLEAN         NOT NULL,
    payment_method_status   VARCHAR(50)     NOT NULL,
    created_at              TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at              TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY (payment_method_id)
);

CREATE TABLE receipt (
    receipt_id      SERIAL          NOT NULL,
    receipt_number  VARCHAR(255)    NOT NULL,
    order_id        INT             NULL,
    issue_date      TIMESTAMP WITH TIME ZONE NULL,
    sale_date       TIMESTAMP WITH TIME ZONE NULL,
    total_amount    NUMERIC(10, 2)  NULL,
    net_amount      NUMERIC(10, 2)  NULL,
    tax_amount      NUMERIC(10, 2)  NULL,
    tax_rate        NUMERIC(5, 2)   NULL,
    payment_id      INT             NULL,
    notes           TEXT            NULL,
    PRIMARY KEY (receipt_id),
    UNIQUE (receipt_number),
    CONSTRAINT fk_receipt_order
        FOREIGN KEY (order_id)
            REFERENCES orders(order_id) ON DELETE SET NULL,
    CONSTRAINT fk_receipt_payment
        FOREIGN KEY (payment_id)
            REFERENCES payment(payment_id) ON DELETE SET NULL
);

CREATE TABLE restaurant (
    restaurant_id     SERIAL          NOT NULL,
    restaurant_name   VARCHAR(255)    NOT NULL,
    owner_id          INT             NULL,
    address_id        INT             NOT NULL,
    phone             VARCHAR(50)     NOT NULL,
    email             VARCHAR(255)    NOT NULL,
    website           VARCHAR(255)    NULL,
    opening_hours     TEXT            NULL,
    cuisine_type      VARCHAR(50)     NULL,
    average_rating    DOUBLE PRECISION NULL,
    rating_count      INT             NULL,
    description       TEXT            NULL,
    logo_url          VARCHAR(255)    NULL,
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

CREATE TABLE user_auth (
    user_auth_id    SERIAL          NOT NULL,
    phone           VARCHAR(50)     NOT NULL,
    email           VARCHAR(255)    NOT NULL,
    password_hash   VARCHAR(255)    NULL,
    salt            VARCHAR(255)    NULL,
    created_at      TIMESTAMP WITH TIME ZONE NULL,
    updated_at      TIMESTAMP WITH TIME ZONE NULL,
    PRIMARY KEY (user_auth_id),
    UNIQUE (phone),
    UNIQUE (email)
);
