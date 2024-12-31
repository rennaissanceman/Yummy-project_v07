CREATE TYPE payment_method_status AS ENUM (
    'ACTIVE',
    'INACTIVE',
    'PENDING_APPROVAL',
    'SUSPENDED',
    'DEPRECATED',
    'UNDER_MAINTENANCE',
    'REMOVED'
);