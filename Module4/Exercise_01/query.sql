-- EXCERCISE 1

-- 1. Count total customers
SELECT 
    COUNT(*) AS total_customers
FROM customer;



-- 2. Fetch actors whose first name starts with 'A'
SELECT 
    first_name,
    last_name
FROM actor
WHERE first_name LIKE 'A%';



-- 3. Fetch active customers from store 1
SELECT 
    customer_id,
    first_name,
    last_name,
    email
FROM customer
WHERE active = 1
  AND store_id = 1;
  


-- 4. Fetch film(s) with the highest replacement cost
SELECT 
    title,
    replacement_cost
FROM film
WHERE replacement_cost = (
    SELECT MAX(replacement_cost)
    FROM film
);



-- 5. Replace NULL rental_duration values with 0
SELECT 
    title,
    COALESCE(rental_duration, 0) AS rental_duration
FROM film;



-- 6. Calculate average rental rate grouped by film rating
SELECT 
    rating,
    ROUND(AVG(rental_rate), 2) AS avg_rental_rate
FROM film
GROUP BY rating
ORDER BY rating;



-- 7. Display customers whose first name contains 'nn'
SELECT 
    ROW_NUMBER() OVER (ORDER BY first_name, last_name) AS row_num,
    first_name,
    last_name
FROM customer
WHERE first_name ILIKE '%nn%';