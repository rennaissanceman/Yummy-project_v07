CREATE TYPE payment_status_enum_entity AS ENUM (
    'PENDING',
    'COMPLETED',
    'FAILED',
    'CANCELLED',
    'REFUNDED',
    'CHARGEBACK',
    'IN_PROGRESS',
    'EXPIRED'
);