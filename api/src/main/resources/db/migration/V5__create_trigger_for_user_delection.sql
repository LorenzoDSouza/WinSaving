CREATE OR REPLACE FUNCTION delete_monthly_budgets()
    RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM monthly_budgets WHERE id = OLD.monthly_budget_id;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_user_delete
    AFTER DELETE ON users
    FOR EACH ROW
EXECUTE FUNCTION delete_monthly_budgets();