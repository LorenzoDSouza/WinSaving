CREATE TABLE IF NOT EXISTS expenses (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    value NUMERIC(10,2) NOT NULL,
    expense_type VARCHAR(20) NOT NULL CHECK (expense_type IN (
        'MERCADO', 'ALIMENTACAO', 'TRANSPORTE', 'HABITACAO', 'SAUDE',
        'EDUCACAO', 'LAZER', 'COMPRAS', 'IMPOSTOS', 'OUTROS'
    )),
    description VARCHAR(255),
    monthly_budget_id UUID NOT NULL,
    FOREIGN KEY (monthly_budget_id) REFERENCES monthly_budgets(id) ON DELETE NO ACTION
);

