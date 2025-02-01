CREATE TYPE payment_method_status_enum_entity AS ENUM (
    'ACTIVE',
    'INACTIVE',
    'PENDING_APPROVAL',
    'SUSPENDED',
    'DEPRECATED',
    'UNDER_MAINTENANCE',
    'REMOVED'
);