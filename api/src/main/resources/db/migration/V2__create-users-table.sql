CREATE TABLE IF NOT EXISTS users (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    monthly_budget_id UUID UNIQUE,
    FOREIGN KEY (monthly_budget_id) REFERENCES monthly_budgets(id) ON DELETE RESTRICT
);
