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