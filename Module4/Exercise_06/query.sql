-- EXERCISE 6 : Date & Time Analysis


-- 22. Display all rentals made in the year 2005
-- Tables: rental

SELECT 
    rental_id,
    rental_date,
    inventory_id,
    customer_id,
    staff_id
FROM rental
WHERE EXTRACT(YEAR FROM rental_date) = 2005
ORDER BY rental_date;



-- 23. Display total payments received per month
-- Tables: payment

SELECT 
    TO_CHAR(payment_date, 'YYYY-MM') AS payment_month,
    SUM(amount) AS total_payment
FROM payment
GROUP BY TO_CHAR(payment_date, 'YYYY-MM')
ORDER BY payment_month;



-- 24. Identify customers who returned films after the expected rental duration
-- Tables: customer, rental, inventory, film

SELECT 
    c.customer_id,
    c.first_name,
    c.last_name,
    f.title,
    r.rental_date,
    r.return_date,
    f.rental_duration,
    
    -- Calculate actual rental days
    (r.return_date::date - r.rental_date::date) AS actual_rental_days

FROM customer AS c
INNER JOIN rental AS r
    ON c.customer_id = r.customer_id
INNER JOIN inventory AS i
    ON r.inventory_id = i.inventory_id
INNER JOIN film AS f
    ON i.film_id = f.film_id

WHERE (r.return_date::date - r.rental_date::date) > f.rental_duration
ORDER BY actual_rental_days DESC;



-- 25. Display peak rental day (highest number of rentals)
-- Tables: rental

SELECT 
    rental_date::date AS rental_day,
    COUNT(rental_id) AS total_rentals
FROM rental
GROUP BY rental_date::date
ORDER BY total_rentals DESC
LIMIT 1;