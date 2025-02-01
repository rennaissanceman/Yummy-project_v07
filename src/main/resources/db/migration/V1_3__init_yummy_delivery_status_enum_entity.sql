CREATE TYPE delivery_status_enum_entity AS ENUM (
    'PENDING',
    'ASSIGNED',
    'PICKED_UP',
    'IN_TRANSIT',
    'DELIVERED',
    'FAILED_DELIVERY',
    'RETURNED',
    'CANCELLED'
);