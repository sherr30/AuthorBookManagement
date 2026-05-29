-- EXERCISE 8 : Stored Procedures


-- 28. Write a stored procedure that:
--     • Accepts store_id
--     • Returns total revenue and total rentals for that store
-- Tables: payment, rental, inventory

CREATE OR REPLACE PROCEDURE get_store_summary
(
    IN p_store_id INT,
    INOUT total_revenue NUMERIC,
    INOUT total_rentals INT
)

LANGUAGE plpgsql
AS
$$
BEGIN

    -- Calculate total revenue for the store
    SELECT 
        COALESCE(SUM(p.amount), 0)
    INTO total_revenue
    FROM payment AS p
    INNER JOIN rental AS r
        ON p.rental_id = r.rental_id
    INNER JOIN inventory AS i
        ON r.inventory_id = i.inventory_id
    WHERE i.store_id = p_store_id;


    -- Calculate total rentals for the store
    SELECT 
        COUNT(r.rental_id)
    INTO total_rentals
    FROM rental AS r
    INNER JOIN inventory AS i
        ON r.inventory_id = i.inventory_id
    WHERE i.store_id = p_store_id;

END;
$$;


-- Example Usage
CALL get_store_summary(1, NULL, NULL);




-- 29. Modify the procedure to:
--     • Use default value store_id = 1 if not provided
-- Tables: payment, rental, inventory

CREATE OR REPLACE PROCEDURE get_store_summary_default
(
    IN p_store_id INT DEFAULT 1,
    INOUT total_revenue NUMERIC DEFAULT 0,
    INOUT total_rentals INT DEFAULT 0
)

LANGUAGE plpgsql
AS
$$
BEGIN

    -- Calculate total revenue for the store
    SELECT 
        COALESCE(SUM(p.amount), 0)
    INTO total_revenue
    FROM payment AS p
    INNER JOIN rental AS r
        ON p.rental_id = r.rental_id
    INNER JOIN inventory AS i
        ON r.inventory_id = i.inventory_id
    WHERE i.store_id = p_store_id;


    -- Calculate total rentals for the store
    SELECT 
        COUNT(r.rental_id)
    INTO total_rentals
    FROM rental AS r
    INNER JOIN inventory AS i
        ON r.inventory_id = i.inventory_id
    WHERE i.store_id = p_store_id;

END;
$$;


-- Example Usage
CALL get_store_summary_default();

-- Example Usage with specific store_id
CALL get_store_summary_default(2);