-- EXCERCISE 8

-- 8. Classify films into pricing bands
SELECT 
    film_id,
    title,
    rental_rate,

    CASE
        WHEN rental_rate = 0.99 THEN 'Budget'
        WHEN rental_rate > 0.99 
             AND rental_rate <= 2.99 THEN 'Regular'
        WHEN rental_rate > 2.99 THEN 'Premium'
        ELSE 'Unknown'
    END AS pricing_band

FROM film
ORDER BY rental_rate, title;