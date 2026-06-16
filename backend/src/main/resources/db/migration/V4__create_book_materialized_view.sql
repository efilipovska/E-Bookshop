CREATE MATERIALIZED VIEW book_materialized_view AS
SELECT
    category,
    COUNT(*) AS total_books,
    SUM(available_copies) AS total_available,
    COUNT(*) FILTER (WHERE state <> 'GOOD') AS not_good_condition
FROM books
GROUP BY category;