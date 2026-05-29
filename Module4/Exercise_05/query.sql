-- EXERCISE 5 : Subqueries, Join, CTEs & EXISTS


-- 19. List customers who have never rented a film
-- Using LEFT JOIN
-- Tables: customer, rental

SELECT 
    c.customer_id,
    c.first_name,
    c.last_name
FROM customer AS c
LEFT JOIN rental AS r
    ON c.customer_id = r.customer_id
WHERE r.rental_id IS NULL
ORDER BY c.customer_id;



-- 19. List customers who have never rented a film
-- Using Subquery
-- Tables: customer, rental

SELECT 
    customer_id,
    first_name,
    last_name
FROM customer
WHERE customer_id NOT IN (
    SELECT customer_id
    FROM rental
)
ORDER BY customer_id;



-- 19. List customers who have never rented a film
-- Using CTE
-- Tables: customer, rental

WITH rented_customers AS (
    SELECT DISTINCT customer_id
    FROM rental
)

SELECT 
    c.customer_id,
    c.first_name,
    c.last_name
FROM customer AS c
LEFT JOIN rented_customers AS rc
    ON c.customer_id = rc.customer_id
WHERE rc.customer_id IS NULL
ORDER BY c.customer_id;



-- 19. List customers who have never rented a film
-- Using NOT EXISTS
-- Tables: customer, rental

SELECT 
    c.customer_id,
    c.first_name,
    c.last_name
FROM customer AS c
WHERE NOT EXISTS (
    SELECT 1
    FROM rental AS r
    WHERE r.customer_id = c.customer_id
)
ORDER BY c.customer_id;



-- 20. Identify actors who have acted in more than 25 films
-- Tables: actor, film_actor

SELECT 
    a.actor_id,
    a.first_name,
    a.last_name,
    COUNT(fa.film_id) AS total_films
FROM actor AS a
INNER JOIN film_actor AS fa
    ON a.actor_id = fa.actor_id
GROUP BY 
    a.actor_id,
    a.first_name,
    a.last_name
HAVING COUNT(fa.film_id) > 25
ORDER BY total_films DESC;



-- 21. Display films that are not present in inventory
-- Tables: film, inventory

SELECT 
    f.film_id,
    f.title
FROM film AS f
LEFT JOIN inventory AS i
    ON f.film_id = i.film_id
WHERE i.inventory_id IS NULL
ORDER BY f.title;