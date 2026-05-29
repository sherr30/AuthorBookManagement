-- EXERCISE 7 : Functions


-- 26. Create a scalar function that accepts a film_id
--     and returns the total number of times the film was rented
-- Tables: rental, inventory

CREATE OR REPLACE FUNCTION get_total_rentals(p_film_id INT)
RETURNS INT
LANGUAGE plpgsql
AS
$$
DECLARE
    total_rentals INT;
BEGIN

    SELECT 
        COUNT(r.rental_id)
    INTO total_rentals
    FROM rental AS r
    INNER JOIN inventory AS i
        ON r.inventory_id = i.inventory_id
    WHERE i.film_id = p_film_id;

    RETURN total_rentals;

END;
$$;


-- Example Usage
SELECT get_total_rentals(1);




-- 27. Create a table-valued function that accepts a customer_id
--     and returns rental details with total payment amount
-- Tables: rental, payment

CREATE OR REPLACE FUNCTION get_customer_rental_details(p_customer_id INT)

RETURNS TABLE
(
    rental_id INT,
    rental_date TIMESTAMP,
    return_date TIMESTAMP,
    total_payment_amount NUMERIC
)

LANGUAGE plpgsql
AS
$$
BEGIN

    RETURN QUERY

    SELECT 
        r.rental_id,
        r.rental_date,
        r.return_date,
        COALESCE(SUM(p.amount), 0) AS total_payment_amount

    FROM rental AS r

    LEFT JOIN payment AS p
        ON r.rental_id = p.rental_id

    WHERE r.customer_id = p_customer_id

    GROUP BY 
        r.rental_id,
        r.rental_date,
        r.return_date

    ORDER BY r.rental_date;

END;
$$;


-- Example Usage
SELECT * 
FROM get_customer_rental_details(1);