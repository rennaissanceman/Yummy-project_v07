CREATE TABLE address (
    address_id          SERIAL          NOT NULL,
    country             VARCHAR(32)     NOT NULL,
    city                VARCHAR(32)     NOT NULL,
    postal_code         VARCHAR(32)     NOT NULL,
    street              VARCHAR(32)     NOT NULL,
    PRIMARY KEY (address_id)
);

CREATE TABLE available_delivery_area (
    available_delivery_area_id      SERIAL  NOT NULL,
    restaurant_id                   INT     NOT NULL,
    address_id                      INT     NOT NULL,
    PRIMARY KEY (available_delivery_area_id)
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id) ON DELETE CASCADE,
    FOREIGN KEY (address_id) REFERENCES address(address_id) ON DELETE CASCADE
);

CREATE TABLE billing_information (
    billing_information_id SERIAL PRIMARY KEY,
    customer_id INTEGER,
    company_name VARCHAR NOT NULL UNIQUE,
    vat_number VARCHAR NOT NULL UNIQUE,
    address_id INTEGER,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (address_id) REFERENCES address(address_id) ON DELETE CASCADE
);

CREATE TABLE courier (
    courier_id SERIAL PRIMARY KEY,
    courier_number VARCHAR NOT NULL UNIQUE,
    courier_name VARCHAR NOT NULL,
    courier_surname VARCHAR NOT NULL,
    user_auth_id INTEGER,
    vehicle_type VARCHAR NOT NULL,
    vehicle_registration_number VARCHAR NOT NULL UNIQUE,
    average_ratings DOUBLE PRECISION NOT NULL,
    delivery_count INTEGER NOT NULL,
    hire_date TIME WITH TIME ZONE NOT NULL,
    FOREIGN KEY (user_auth_id) REFERENCES user_auth(user_auth_id) ON DELETE CASCADE
);

CREATE TABLE customer_address (
    delivery_address_id SERIAL PRIMARY KEY,
    customer_id INTEGER,
    available_delivery_area_id INTEGER,
    address_id INTEGER,
    is_default BOOLEAN NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (available_delivery_area_id) REFERENCES available_delivery_area(available_delivery_area_id) ON DELETE CASCADE,
    FOREIGN KEY (address_id) REFERENCES address(address_id) ON DELETE CASCADE
);

CREATE TABLE customer (
    customer_id SERIAL PRIMARY KEY,
    customer_number VARCHAR NOT NULL UNIQUE,
    is_company BOOLEAN NOT NULL,
    company_name VARCHAR UNIQUE,
    customer_name VARCHAR,
    customer_surname VARCHAR,
    user_auth_id INTEGER,
    FOREIGN KEY (user_auth_id) REFERENCES user_auth(user_auth_id) ON DELETE CASCADE
);

CREATE TABLE delivery (
    delivery_id SERIAL PRIMARY KEY,
    delivery_number VARCHAR NOT NULL UNIQUE,
    order_id INTEGER,
    available_delivery_area_id INTEGER,
    courier_id INTEGER,
    delivery_status VARCHAR,
    start_time TIMESTAMP WITH TIME ZONE,
    end_time TIMESTAMP WITH TIME ZONE,
    estimated_delivery_time TIMESTAMP WITH TIME ZONE,
    actual_delivery_date_time TIMESTAMP WITH TIME ZONE,
    delivery_fee NUMERIC,
    delivery_notes TEXT,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (available_delivery_area_id) REFERENCES available_delivery_area(available_delivery_area_id) ON DELETE CASCADE,
    FOREIGN KEY (courier_id) REFERENCES courier(courier_id) ON DELETE CASCADE
);

CREATE TABLE invoice (
    invoice_id SERIAL PRIMARY KEY,
    invoice_number VARCHAR NOT NULL UNIQUE,
    order_id INTEGER,
    issue_date TIMESTAMP WITH TIME ZONE NOT NULL,
    sale_date TIMESTAMP WITH TIME ZONE NOT NULL,
    total_amount NUMERIC NOT NULL,
    net_amount NUMERIC NOT NULL,
    tax_amount NUMERIC NOT NULL,
    tax_rate NUMERIC NOT NULL,
    payment_id INTEGER,
    billing_information_id INTEGER,
    notes TEXT,
    due_date TIMESTAMP WITH TIME ZONE NOT NULL,
    issuer_signature VARCHAR NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (payment_id) REFERENCES payment(payment_id) ON DELETE CASCADE,
    FOREIGN KEY (billing_information_id) REFERENCES billing_information(billing_information_id) ON DELETE CASCADE
);

CREATE TABLE menu (
    menu_id SERIAL PRIMARY KEY,
    restaurant_id INTEGER,
    menu_name VARCHAR NOT NULL UNIQUE,
    description TEXT,
    valid_from TIMESTAMP WITH TIME ZONE,
    valid_to TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id) ON DELETE CASCADE
);

CREATE TABLE menu_item (
    menu_item_id SERIAL PRIMARY KEY,
    item_name VARCHAR NOT NULL UNIQUE,
    menu_id INTEGER,
    description TEXT,
    is_available BOOLEAN NOT NULL,
    diet_type VARCHAR,
    calories INTEGER,
    ingredients TEXT,
    portion_weight VARCHAR,
    preparation_time INTEGER,
    price NUMERIC NOT NULL,
    image_url VARCHAR NOT NULL UNIQUE,
    display_order INTEGER NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY (menu_id) REFERENCES menu(menu_id) ON DELETE CASCADE
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    order_number VARCHAR NOT NULL UNIQUE,
    customer_id INTEGER,
    menu_id INTEGER,
    order_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
    order_status VARCHAR NOT NULL,
    order_description TEXT,
    total_amount NUMERIC NOT NULL,
    available_delivery_area_id INTEGER,
    customer_address_id INTEGER,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES menu(menu_id) ON DELETE CASCADE,
    FOREIGN KEY (available_delivery_area_id) REFERENCES available_delivery_area(available_delivery_area_id) ON DELETE CASCADE,
    FOREIGN KEY (customer_address_id) REFERENCES customer_address(delivery_address_id) ON DELETE CASCADE
);

CREATE TABLE order_item (
    order_item_id SERIAL PRIMARY KEY,
    order_id INTEGER,
    menu_id INTEGER,
    item_name VARCHAR NOT NULL UNIQUE,
    quantity INTEGER NOT NULL,
    unit_price NUMERIC NOT NULL,
    total_price NUMERIC NOT NULL,
    item_notes TEXT,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES menu(menu_id) ON DELETE CASCADE
);

CREATE TABLE owner (
    owner_id SERIAL PRIMARY KEY,
    owner_number VARCHAR NOT NULL UNIQUE,
    owner_name VARCHAR NOT NULL UNIQUE,
    user_auth_id INTEGER,
    FOREIGN KEY (user_auth_id) REFERENCES user_auth(user_auth_id) ON DELETE CASCADE
);

CREATE TABLE payment (
    payment_id SERIAL PRIMARY KEY,
    order_id INTEGER UNIQUE,
    payment_method_id INTEGER,
    amount NUMERIC NOT NULL,
    payment_status VARCHAR NOT NULL,
    transaction_id VARCHAR,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (payment_method_id) REFERENCES payment_method(payment_method_id) ON DELETE CASCADE
);

CREATE TABLE payment_method (
    payment_method_id SERIAL PRIMARY KEY,
    payment_method_name VARCHAR,
    description TEXT,
    is_active BOOLEAN,
    payment_method_status VARCHAR,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE receipt (
    receipt_id SERIAL PRIMARY KEY,
    receipt_number VARCHAR NOT NULL UNIQUE,
    order_id INTEGER UNIQUE,
    issue_date TIMESTAMP WITH TIME ZONE,
    sale_date TIMESTAMP WITH TIME ZONE,
    total_amount NUMERIC,
    net_amount NUMERIC,
    tax_amount NUMERIC,
    tax_rate NUMERIC,
    payment_id INTEGER UNIQUE,
    notes TEXT,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (payment_id) REFERENCES payment(payment_id) ON DELETE CASCADE
);

CREATE TABLE restaurant (
    restaurant_id SERIAL PRIMARY KEY,
    restaurant_name VARCHAR UNIQUE,
    owner_id INTEGER,
    address_id INTEGER,
    phone VARCHAR NOT NULL UNIQUE,
    email VARCHAR NOT NULL UNIQUE,
    website VARCHAR UNIQUE,
    opening_hours TEXT,
    cuisine_type VARCHAR,
    average_rating DOUBLE PRECISION,
    rating_count INTEGER,
    description TEXT,
    logo_url VARCHAR UNIQUE,
    FOREIGN KEY (owner_id) REFERENCES owner(owner_id) ON DELETE CASCADE,
    FOREIGN KEY (address_id) REFERENCES address(address_id) ON DELETE CASCADE
);

CREATE TABLE user_auth (
    user_auth_id SERIAL PRIMARY KEY,
    phone VARCHAR NOT NULL UNIQUE,
    email VARCHAR NOT NULL UNIQUE,
    password_hash VARCHAR,
    salt VARCHAR,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE
);
