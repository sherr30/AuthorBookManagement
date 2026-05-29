-- EXERCISE 3 : Joins & Relationships


-- 9. Display film title and language name
-- Tables: film, language

SELECT 
    f.title AS film_title,
    l.name AS language_name
FROM film AS f
INNER JOIN language AS l
    ON f.language_id = l.language_id
ORDER BY f.title;



-- 10. Display all actors who acted in the film 'Academy Dinosaur'
-- Tables: film, film_actor, actor

SELECT 
    a.actor_id,
    a.first_name,
    a.last_name
FROM film AS f
INNER JOIN film_actor AS fa
    ON f.film_id = fa.film_id
INNER JOIN actor AS a
    ON fa.actor_id = a.actor_id
WHERE f.title = 'Academy Dinosaur'
ORDER BY a.last_name, a.first_name;



-- 11. Display customer name, rental date, and return date for all rentals
-- Tables: customer, rental

SELECT 
    c.first_name,
    c.last_name,
    r.rental_date,
    r.return_date
FROM customer AS c
INNER JOIN rental AS r
    ON c.customer_id = r.customer_id
ORDER BY r.rental_date DESC;



-- 12. Display customer name, city, and country
-- Tables: customer, address, city, country

SELECT 
    c.first_name,
    c.last_name,
    ci.city,
    co.country
FROM customer AS c
INNER JOIN address AS a
    ON c.address_id = a.address_id
INNER JOIN city AS ci
    ON a.city_id = ci.city_id
INNER JOIN country AS co
    ON ci.country_id = co.country_id
ORDER BY c.last_name, c.first_name;



-- 13. Display store ID and total number of films available in each store
-- Tables: store, inventory

SELECT 
    s.store_id,
    COUNT(i.inventory_id) AS total_films
FROM store AS s
LEFT JOIN inventory AS i
    ON s.store_id = i.store_id
GROUP BY s.store_id
ORDER BY s.store_id;