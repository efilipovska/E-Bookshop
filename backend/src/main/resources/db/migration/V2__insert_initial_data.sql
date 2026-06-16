INSERT INTO countries(name, continent)
VALUES ('UK','Europe'),
       ('USA','North America');

INSERT INTO authors(created_at,updated_at,name,surname,country_id)
VALUES (now(), now(),'George','Orwell',1),
       (now(), now(),'Stephen','King',2);

INSERT INTO books(created_at,updated_at,name,category,state,available_copies,author_id,deleted)
VALUES (now(), now(),'1984','NOVEL','GOOD',5,1,false),
       (now(), now(),'The Shining','THRILLER','GOOD',3,2,false);