INSERT INTO node (id, name, address) VALUES (1001, 'rivadavia102', 'avellaneda'), (1002, 'colon 1453', 'belgrano');
INSERT INTO company (id, name, contact) VALUES (1001, 'ok', 'imanol'), (1002, 'Rodrigo', 'Imanol'), (1003, 'Juan', 'ECORP'), (1004, 'Lucas', 'SodaSA');
INSERT INTO student (id, name, node_id, deleted, looking_for_work, working) VALUES (1001, 'imanol', 1001, 0, 1, 0), (1002, 'Daniel', 1001, 0, 1, 0), (1003, 'Julian', 1002, 1, 1, 0), (1004, 'Leandro', 1002, 0, 1, 0);
INSERT INTO interview (id, comments, date, student_id, company_id) VALUES (1001, 'Sin comentarios', '2016-11-02', 1001, 1002), (1002, 'Sin comentarios', '2016-11-02', 1002, 1003);
