CREATE EXTENSIONS IF NOT EXISTS "pgcrypto";

CREATE TABLE users (
    id UUID get_random_UUID() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    monthly_budget_id UUID UNIQUE,
    FOREIGN KEY (monthly_budget_id) REFERENCES monthly_budgets(id) ON DELETE CASCADE
);
