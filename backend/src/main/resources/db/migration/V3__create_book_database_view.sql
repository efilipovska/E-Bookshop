CREATE VIEW book_database_view AS
SELECT
    b.id,
    b.name,
    b.category,
    b.state,
    b.available_copies,
    a.name || ' ' || a.surname AS author_full_name,
    c.name AS country_name
FROM books b
         JOIN authors a ON b.author_id = a.id
         JOIN countries c ON a.country_id = c.id;