-- INSERTY dla tabeli user_auth (przykładowo 5 rekordów)
INSERT INTO user_auth (phone, email, password_hash, salt, created_at, updated_at)
VALUES
  ('111222331', 'owner1@yummy.pl', 'hash_owner1', 'salt_owner1', NOW(), NOW()),
  ('111222332', 'owner2@yummy.pl', 'hash_owner2', 'salt_owner2', NOW(), NOW()),
  ('111222333', 'owner3@yummy.pl', 'hash_owner3', 'salt_owner3', NOW(), NOW()),
  ('444555331', 'customer1@yummy.pl', 'hash_cust1', 'salt_cust1', NOW(), NOW()),
  ('444555332', 'customer2@yummy.pl', 'hash_cust2', 'salt_cust2', NOW(), NOW());

-- INSERTY dla tabeli address (5 rekordów)
INSERT INTO address (country, city, postal_code, street)
VALUES
  ('Polska', 'Warszawa', '00-001', 'ul. Marszałkowska 1'),
  ('Polska', 'Kraków', '30-001', 'ul. Floriańska 1'),
  ('Polska', 'Gdańsk', '80-001', 'ul. Długa 1'),
  ('Polska', 'Poznań', '60-001', 'ul. Półwiejskiej 1'),
  ('Polska', 'Wrocław', '50-001', 'ul. Piłsudskiego 1');

-- INSERTY dla tabeli payment_method (5 rekordów)
INSERT INTO payment_method (payment_method_name, description, is_active, payment_method_status, created_at, updated_at)
VALUES
  ('Karta kredytowa', 'Płatność kartą Visa/Mastercard', true, 'ACTIVE', NOW(), NOW()),
  ('Przelew', 'Płatność przelewem bankowym', true, 'ACTIVE', NOW(), NOW()),
  ('PayPal', 'Płatność przez PayPal', true, 'ACTIVE', NOW(), NOW()),
  ('BLIK', 'Płatność systemem BLIK', true, 'ACTIVE', NOW(), NOW()),
  ('Gotówka', 'Płatność gotówką przy odbiorze', true, 'ACTIVE', NOW(), NOW());

-- INSERTY dla tabeli owner (5 rekordów) – powiązane z user_auth (zakładamy, że odpowiednie identyfikatory istnieją)
INSERT INTO owner (owner_number, owner_name, user_auth_id)
VALUES
  ('OWN-001', 'Yummy Owner 1', 1),
  ('OWN-002', 'Yummy Owner 2', 2),
  ('OWN-003', 'Yummy Owner 3', 3),
  ('OWN-004', 'Yummy Owner 4', 4),
  ('OWN-005', 'Yummy Owner 5', 5);

-- INSERTY dla tabeli customer (5 rekordów)
INSERT INTO customer (customer_number, is_company, company_name, customer_name, customer_surname, user_auth_id)
VALUES
  ('CUS-001', false, NULL, 'Jan', 'Kowalski', 1),
  ('CUS-002', false, NULL, 'Anna', 'Nowak', 2),
  ('CUS-003', false, NULL, 'Piotr', 'Zieliński', 3),
  ('CUS-004', false, NULL, 'Ewa', 'Wiśniewska', 4),
  ('CUS-005', false, NULL, 'Marek', 'Wójcik', 5);

-- INSERTY dla tabeli courier (5 rekordów)
INSERT INTO courier (courier_number, courier_name, courier_surname, user_auth_id, vehicle_type, vehicle_registration_number, average_ratings, delivery_count, hire_date)
VALUES
  ('COU-001', 'Michał', 'Kowalski', 1, 'Rower', 'XYZ123', 4.7, 20, '08:00:00+00'),
  ('COU-002', 'Tomasz', 'Wiśniewski', 2, 'Samochód', 'ABC987', 4.5, 15, '09:00:00+00'),
  ('COU-003', 'Krzysztof', 'Nowak', 3, 'Motocykl', 'LMN456', 4.8, 25, '07:30:00+00'),
  ('COU-004', 'Adam', 'Malinowski', 4, 'Rower', 'OPQ321', 4.2, 10, '08:15:00+00'),
  ('COU-005', 'Paweł', 'Sikora', 5, 'Samochód', 'RST654', 4.9, 30, '07:45:00+00');

-- INSERTY dla tabeli restaurant (5 rekordów)
INSERT INTO restaurant (restaurant_name, owner_id, address_id, phone, email, website, opening_hours, cuisine_type, average_rating, rating_count, description, logo_url)
VALUES
  ('Yummy Restaurant 1', 1, 1, '123456789', 'contact1@yummy.pl', 'http://www.yummy1.pl', '10:00-22:00', 'ITALIAN', 4.5, 150, 'Najlepsza kuchnia włoska', 'logo1.png'),
  ('Yummy Restaurant 2', 2, 2, '987654321', 'contact2@yummy.pl', 'http://www.yummy2.pl', '11:00-23:00', 'CHINESE', 4.2, 120, 'Pyszna kuchnia chińska', 'logo2.png'),
  ('Yummy Restaurant 3', 3, 3, '555666777', 'contact3@yummy.pl', 'http://www.yummy3.pl', '09:00-21:00', 'INDIAN', 4.7, 180, 'Autentyczna kuchnia indyjska', 'logo3.png'),
  ('Yummy Restaurant 4', 4, 4, '222333444', 'contact4@yummy.pl', 'http://www.yummy4.pl', '10:00-20:00', 'AMERICAN', 4.0, 100, 'Klasyczna kuchnia amerykańska', 'logo4.png'),
  ('Yummy Restaurant 5', 5, 5, '777888999', 'contact5@yummy.pl', 'http://www.yummy5.pl', '08:00-20:00', 'MEXICAN', 4.3, 110, 'Wyjątkowa kuchnia meksykańska', 'logo5.png');

-- INSERTY dla tabeli available_delivery_area (5 rekordów)
INSERT INTO available_delivery_area (restaurant_id, address_id)
VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 4),
  (5, 5);

-- INSERTY dla tabeli customer_address (5 rekordów)
INSERT INTO customer_address (customer_id, available_delivery_area_id, address_id, is_default)
VALUES
  (1, 1, 1, true),
  (2, 2, 2, true),
  (3, 3, 3, true),
  (4, 4, 4, true),
  (5, 5, 5, true);

-- INSERTY dla tabeli menu (5 rekordów)
INSERT INTO menu (restaurant_id, menu_name, description, valid_from, valid_to, created_at, updated_at)
VALUES
  (1, 'Menu Lunch 1', 'Oferta obiadowa', NOW(), NOW() + INTERVAL '30 days', NOW(), NOW()),
  (2, 'Menu Lunch 2', 'Oferta obiadowa', NOW(), NOW() + INTERVAL '30 days', NOW(), NOW()),
  (3, 'Menu Lunch 3', 'Oferta obiadowa', NOW(), NOW() + INTERVAL '30 days', NOW(), NOW()),
  (4, 'Menu Lunch 4', 'Oferta obiadowa', NOW(), NOW() + INTERVAL '30 days', NOW(), NOW()),
  (5, 'Menu Lunch 5', 'Oferta obiadowa', NOW(), NOW() + INTERVAL '30 days', NOW(), NOW());

-- INSERTY dla tabeli orders (5 rekordów)
INSERT INTO orders (orders_number, customer_id, menu_id, orders_date_time, orders_status, orders_description, total_amount, orders_rating, available_delivery_area_id, customer_address_id)
VALUES
  ('ORD-001', 1, 1, NOW(), 'PENDING', 'Zamówienie lunchowe 1', 45.50, 5, 1, 1),
  ('ORD-002', 2, 2, NOW(), 'PENDING', 'Zamówienie lunchowe 2', 55.00, 4, 2, 2),
  ('ORD-003', 3, 3, NOW(), 'PENDING', 'Zamówienie lunchowe 3', 65.75, 5, 3, 3),
  ('ORD-004', 4, 4, NOW(), 'PENDING', 'Zamówienie lunchowe 4', 75.00, 4, 4, 4),
  ('ORD-005', 5, 5, NOW(), 'PENDING', 'Zamówienie lunchowe 5', 85.25, 5, 5, 5);

-- INSERTY dla tabeli payment (5 rekordów)
INSERT INTO payment (orders_id, payment_method_id, amount, payment_status, transaction_id, created_at, updated_at)
VALUES
  (1, 1, 45.50, 'COMPLETED', 'TXN-001', NOW(), NOW()),
  (2, 2, 55.00, 'COMPLETED', 'TXN-002', NOW(), NOW()),
  (3, 3, 65.75, 'COMPLETED', 'TXN-003', NOW(), NOW()),
  (4, 4, 75.00, 'COMPLETED', 'TXN-004', NOW(), NOW()),
  (5, 5, 85.25, 'COMPLETED', 'TXN-005', NOW(), NOW());

-- INSERTY dla tabeli delivery (5 rekordów)
INSERT INTO delivery (delivery_number, orders_id, available_delivery_area_id, courier_id, delivery_status, start_time, estimated_delivery_time, delivery_fee, delivery_notes)
VALUES
  ('DEL-001', 1, 1, 1, 'PENDING', NOW(), NOW() + INTERVAL '30 minutes', 5.00, 'Dostawa w toku 1'),
  ('DEL-002', 2, 2, 2, 'PENDING', NOW(), NOW() + INTERVAL '30 minutes', 6.00, 'Dostawa w toku 2'),
  ('DEL-003', 3, 3, 3, 'PENDING', NOW(), NOW() + INTERVAL '30 minutes', 7.00, 'Dostawa w toku 3'),
  ('DEL-004', 4, 4, 4, 'PENDING', NOW(), NOW() + INTERVAL '30 minutes', 8.00, 'Dostawa w toku 4'),
  ('DEL-005', 5, 5, 5, 'PENDING', NOW(), NOW() + INTERVAL '30 minutes', 9.00, 'Dostawa w toku 5');

-- INSERTY dla tabeli billing_information (5 rekordów)
INSERT INTO billing_information (customer_id, company_name, vat_number, address_id)
VALUES
  (1, 'Firma Yummy 1', 'PL1234567890', 1),
  (2, 'Firma Yummy 2', 'PL0987654321', 2),
  (3, 'Firma Yummy 3', 'PL1122334455', 3),
  (4, 'Firma Yummy 4', 'PL2233445566', 4),
  (5, 'Firma Yummy 5', 'PL3344556677', 5);

-- INSERTY dla tabeli invoice (5 rekordów)
INSERT INTO invoice (invoice_number, orders_id, issue_date, sale_date, total_amount, net_amount, tax_amount, tax_rate, payment_id, billing_information_id, notes, due_date, issuer_signature)
VALUES
  ('INV-001', 1, NOW(), NOW(), 45.50, 38.00, 7.50, 20.00, 1, 1, 'Faktura VAT 1', NOW() + INTERVAL '14 days', 'Manager 1'),
  ('INV-002', 2, NOW(), NOW(), 55.00, 45.00, 10.00, 20.00, 2, 2, 'Faktura VAT 2', NOW() + INTERVAL '14 days', 'Manager 2'),
  ('INV-003', 3, NOW(), NOW(), 65.75, 55.00, 10.75, 20.00, 3, 3, 'Faktura VAT 3', NOW() + INTERVAL '14 days', 'Manager 3'),
  ('INV-004', 4, NOW(), NOW(), 75.00, 60.00, 15.00, 20.00, 4, 4, 'Faktura VAT 4', NOW() + INTERVAL '14 days', 'Manager 4'),
  ('INV-005', 5, NOW(), NOW(), 85.25, 70.00, 15.25, 20.00, 5, 5, 'Faktura VAT 5', NOW() + INTERVAL '14 days', 'Manager 5');

-- INSERTY dla tabeli receipt (5 rekordów)
INSERT INTO receipt (receipt_number, orders_id, issue_date, sale_date, total_amount, net_amount, tax_amount, tax_rate, payment_id, notes)
VALUES
  ('REC-001', 1, NOW(), NOW(), 45.50, 38.00, 7.50, 20.00, 1, 'Paragon fiskalny 1'),
  ('REC-002', 2, NOW(), NOW(), 55.00, 45.00, 10.00, 20.00, 2, 'Paragon fiskalny 2'),
  ('REC-003', 3, NOW(), NOW(), 65.75, 55.00, 10.75, 20.00, 3, 'Paragon fiskalny 3'),
  ('REC-004', 4, NOW(), NOW(), 75.00, 60.00, 15.00, 20.00, 4, 'Paragon fiskalny 4'),
  ('REC-005', 5, NOW(), NOW(), 85.25, 70.00, 15.25, 20.00, 5, 'Paragon fiskalny 5');

-- INSERTY dla tabeli menu_item (5 rekordów)
INSERT INTO menu_item (item_name, menu_id, description, is_available, diet_type, calories, ingredients, portion_weight, preparation_time, price, image_url, display_order, created_at, updated_at)
VALUES
  ('Pizza Margherita 1', 1, 'Klasyczna pizza z mozzarellą, pomidorami i bazylią', true, 'VEGETARIAN', 800, 'mąka, pomidory, mozzarella, bazylia', '300g', 15, 15.00, 'pizza_margherita1.png', 1, NOW(), NOW()),
  ('Pizza Margherita 2', 2, 'Klasyczna pizza z mozzarellą, pomidorami i bazylią', true, 'VEGETARIAN', 850, 'mąka, pomidory, mozzarella, bazylia', '320g', 16, 16.00, 'pizza_margherita2.png', 1, NOW(), NOW()),
  ('Pizza Margherita 3', 3, 'Klasyczna pizza z mozzarellą, pomidorami i bazylią', true, 'VEGETARIAN', 900, 'mąka, pomidory, mozzarella, bazylia', '350g', 17, 17.00, 'pizza_margherita3.png', 1, NOW(), NOW()),
  ('Pizza Margherita 4', 4, 'Klasyczna pizza z mozzarellą, pomidorami i bazylią', true, 'VEGETARIAN', 950, 'mąka, pomidory, mozzarella, bazylia', '360g', 18, 18.00, 'pizza_margherita4.png', 1, NOW(), NOW()),
  ('Pizza Margherita 5', 5, 'Klasyczna pizza z mozzarellą, pomidorami i bazylią', true, 'VEGETARIAN', 1000, 'mąka, pomidory, mozzarella, bazylia', '370g', 19, 19.00, 'pizza_margherita5.png', 1, NOW(), NOW());

-- INSERTY dla tabeli orders_item (5 rekordów)
INSERT INTO orders_item (orders_id, menu_item_id, item_name, quantity, unit_price, total_price, item_notes)
VALUES
  (1, 1, 'Pizza Margherita 1', 2, 15.00, 30.00, 'Bez dodatkowych sosów 1'),
  (2, 2, 'Pizza Margherita 2', 1, 16.00, 16.00, 'Bez dodatkowych sosów 2'),
  (3, 3, 'Pizza Margherita 3', 3, 17.00, 51.00, 'Bez dodatkowych sosów 3'),
  (4, 4, 'Pizza Margherita 4', 1, 18.00, 18.00, 'Bez dodatkowych sosów 4'),
  (5, 5, 'Pizza Margherita 5', 2, 19.00, 38.00, 'Bez dodatkowych sosów 5');

-- INSERTY dla tabeli feedback (5 rekordów)
INSERT INTO feedback (rating, comments, feedback_date, courier_id, orders_id, restaurant_id)
VALUES
  (4.50, 'Bardzo smaczne danie, miła obsługa 1', NOW(), 1, 1, 1),
  (4.00, 'Smaczne, ale obsługa mogłaby być lepsza 2', NOW(), 2, 2, 2),
  (5.00, 'Wyśmienite, polecam 3', NOW(), 3, 3, 3),
  (4.25, 'Dobre jedzenie, ale długie oczekiwanie 4', NOW(), 4, 4, 4),
  (4.75, 'Bardzo polecam, wszystko super 5', NOW(), 5, 5, 5);
