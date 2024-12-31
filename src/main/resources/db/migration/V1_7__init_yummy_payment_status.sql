CREATE TYPE payment_status AS ENUM (
    'PENDING',
    'COMPLETED',
    'FAILED',
    'CANCELLED',
    'REFUNDED',
    'CHARGEBACK',
    'IN_PROGRESS',
    'EXPIRED'
);