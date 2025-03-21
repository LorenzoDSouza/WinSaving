CREATE TABLE IF NOT EXISTS saving_goals (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    purpose VARCHAR(255),
    deposit_place VARCHAR(255),
    due_date DATE,
    goal_amount NUMERIC(15, 2),
    total_amount NUMERIC(15, 2) DEFAULT 0.00,
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);