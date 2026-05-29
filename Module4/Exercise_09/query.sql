-- EXERCISE 9 : Views


-- 30. Create a view that displays:
--     • customer name
--     • total rentals
--     • total payment amount
-- Tables: customer, rental, payment

CREATE OR REPLACE VIEW customer_rental_summary AS

SELECT 
    c.customer_id,
    c.first_name,
    c.last_name,

    COUNT(DISTINCT r.rental_id) AS total_rentals,

    COALESCE(SUM(p.amount), 0) AS total_payment_amount

FROM customer AS c

LEFT JOIN rental AS r
    ON c.customer_id = r.customer_id

LEFT JOIN payment AS p
    ON r.rental_id = p.rental_id

GROUP BY 
    c.customer_id,
    c.first_name,
    c.last_name;



-- View Query Example
SELECT *
FROM customer_rental_summary
ORDER BY total_payment_amount DESC;




-- 31. Query the view to find customers who spent more than $150
-- View Used: customer_rental_summary

SELECT 
    customer_id,
    first_name,
    last_name,
    total_rentals,
    total_payment_amount
FROM customer_rental_summary
WHERE total_payment_amount > 150
ORDER BY total_payment_amount DESC;