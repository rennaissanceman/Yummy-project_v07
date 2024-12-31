CREATE TYPE delivery_status AS ENUM (
    'PENDING',
    'ASSIGNED',
    'PICKED_UP',
    'IN_TRANSIT',
    'DELIVERED',
    'FAILED_DELIVERY',
    'RETURNED',
    'CANCELLED'
);