-- EXERCISE 10 : Triggers & Constraints


-- 32. Create a trigger to ensure
--     payment amount cannot exceed $20
-- Table: payment


-- Create Trigger Function
CREATE OR REPLACE FUNCTION check_payment_amount()

RETURNS TRIGGER
LANGUAGE plpgsql
AS
$$
BEGIN

    IF NEW.amount > 20 THEN

        RAISE EXCEPTION
        'Payment amount cannot exceed $20';

    END IF;

    RETURN NEW;

END;
$$;



-- Create Trigger for INSERT and UPDATE
CREATE TRIGGER trg_check_payment_amount

BEFORE INSERT OR UPDATE
ON payment

FOR EACH ROW

EXECUTE FUNCTION check_payment_amount();




-- Valid INSERT Test
INSERT INTO payment
(
    customer_id,
    staff_id,
    rental_id,
    amount,
    payment_date
)
VALUES
(
    1,
    1,
    1,
    15,
    NOW()
);




-- Invalid INSERT Test
-- This will raise an exception

INSERT INTO payment
(
    customer_id,
    staff_id,
    rental_id,
    amount,
    payment_date
)
VALUES
(
    1,
    1,
    1,
    25,
    NOW()
);





-- 33. Modify the trigger so it runs
--     only when the amount column is updated
-- Table: payment


-- Remove old trigger
DROP TRIGGER IF EXISTS trg_check_payment_amount
ON payment;



-- Create trigger only for UPDATE OF amount
CREATE TRIGGER trg_check_payment_amount

BEFORE UPDATE OF amount
ON payment

FOR EACH ROW

EXECUTE FUNCTION check_payment_amount();




-- Find an existing payment record
SELECT 
    payment_id,
    amount
FROM payment
ORDER BY payment_id
LIMIT 5;




-- Valid UPDATE Test
-- Replace payment_id with an existing value from above query

UPDATE payment
SET amount = 18
WHERE payment_id = 17503;




-- Invalid UPDATE Test
-- This will raise an exception

UPDATE payment
SET amount = 25
WHERE payment_id = 17503;