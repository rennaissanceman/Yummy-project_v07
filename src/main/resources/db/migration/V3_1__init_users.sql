-- Wstawienie przykładowych użytkowników do tabeli yummy_user
INSERT INTO yummy_user (user_id, user_name, email, password, active) VALUES
(1, 'john_customer', 'john_customer@yummy.com', '$2a$12$abcdefghijklmnopqrstuv', true),
(2, 'alice_customer', 'alice_customer@yummy.com', '$2a$12$abcdefghijklmnopqrstuv', true),
(3, 'chef_mario', 'chef_mario@yummy.com', '$2a$12$abcdefghijklmnopqrstuv', true),
(4, 'manager_smith', 'manager_smith@yummy.com', '$2a$12$abcdefghijklmnopqrstuv', true),
(5, 'chef_luigi', 'chef_luigi@yummy.com', '$2a$12$abcdefghijklmnopqrstuv', true);

-- Wstawienie przykładowych ról do tabeli yummy_role
INSERT INTO yummy_role (role_id, role) VALUES
(1, 'CUSTOMER'),
(2, 'CHEF'),
(3, 'MANAGER');

-- Wstawienie przykładowych przypisań ról do użytkowników do tabeli yummy_user_role
INSERT INTO yummy_user_role (user_id, role_id) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 3),
(5, 2);
