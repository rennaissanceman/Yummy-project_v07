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