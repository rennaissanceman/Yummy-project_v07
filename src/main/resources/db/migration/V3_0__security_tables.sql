-- Tabela użytkowników aplikacji Yummy
CREATE TABLE yummy_user (
    user_id   SERIAL        NOT NULL,
    user_name VARCHAR(32)   NOT NULL,
    email     VARCHAR(32)   NOT NULL,
    password  VARCHAR(128)  NOT NULL,
    active    BOOLEAN       NOT NULL,
    PRIMARY KEY (user_id)
);

-- Tabela ról aplikacji Yummy
CREATE TABLE yummy_role (
    role_id SERIAL      NOT NULL,
    role    VARCHAR(20) NOT NULL,
    PRIMARY KEY (role_id)
);

-- Tabela pośrednia dla relacji wiele-do-wielu między użytkownikami a rolami
CREATE TABLE yummy_user_role (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_yummy_user_role_user
        FOREIGN KEY (user_id)
            REFERENCES yummy_user(user_id),
    CONSTRAINT fk_yummy_user_role_role
        FOREIGN KEY (role_id)
            REFERENCES yummy_role(role_id)
);
