CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS monthly_budgets (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    used_amount NUMERIC(10,2) NOT NULL DEFAULT 0.00,
    original_amount NUMERIC(10,2) NOT NULL,
    pay_day INT,
    last_reset DATE,
    CONSTRAINT check_pay_day CHECK (pay_day IS NULL OR (pay_day >= 1 AND pay_day <= 31))
);
