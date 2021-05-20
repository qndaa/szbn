INSERT INTO Areas (area_id, name) VALUES ('27a25265-97b8-49a6-8f58-4f2889211d2f', 'Computer science');
INSERT INTO Areas (area_id, name) VALUES ('992d54e0-b679-11eb-8529-0242ac130003', 'Business');
INSERT INTO Areas (area_id, name) VALUES ('ff01372a-b67c-11eb-8529-0242ac130003', 'Arts and Humanities');
INSERT INTO Areas (area_id, name) VALUES ('a986b9a6-b67b-11eb-8529-0242ac130003', 'Data science');
INSERT INTO Areas (area_id, name) VALUES ('add8be5a-b67b-11eb-8529-0242ac130003', 'Information technology');
INSERT INTO Areas (area_id, name) VALUES ('b11660c2-b67b-11eb-8529-0242ac130003', 'Math and Logic');


INSERT INTO Skills (skill_id, name) VALUES ('83db1994-b67c-11eb-8529-0242ac130003', 'Java');
INSERT INTO Skills (skill_id, name) VALUES ('8723dd52-b67c-11eb-8529-0242ac130003', 'Python');
INSERT INTO Skills (skill_id, name) VALUES ('8b95d17e-b67c-11eb-8529-0242ac130003', 'Database');
INSERT INTO Skills (skill_id, name) VALUES ('8f204dce-b67c-11eb-8529-0242ac130003', 'JSON');
INSERT INTO Skills (skill_id, name) VALUES ('925fa4c6-b67c-11eb-8529-0242ac130003', 'XML');
INSERT INTO Skills (skill_id, name) VALUES ('959ebfbe-b67c-11eb-8529-0242ac130003', 'GO');
INSERT INTO Skills (skill_id, name) VALUES ('e421aca8-b6a1-11eb-8529-0242ac130003', 'Algorithm');


INSERT INTO USERS (user_id, name, surname, username, password, type_of_user) VALUES ('3809d724-b67e-11eb-8529-0242ac130003', 'Petar', 'Petrovic', 'pera@gmail.com', 'pera1234', 0);
INSERT INTO USERS (user_id, name, surname, username, password, type_of_user) VALUES ('9bda8b40-b697-11eb-8529-0242ac130003', 'Marko', 'Markovic', 'marko@gmail.com', 'marko1234', 0);


INSERT INTO USERS (user_id, name, surname, username, password, type_of_user) VALUES ('dfc9bc30-b698-11eb-8529-0242ac130003', 'Zeljana', 'Stankovic', 'zeljna@gmail.com', 'zeljana1234', 1);
INSERT INTO USERS (user_id, name, surname, username, password, type_of_user) VALUES ('e39930d4-b698-11eb-8529-0242ac130003', 'Marija', 'Markovic', 'marija', 'marija1234', 1);


INSERT INTO USERS (user_id, name, surname, username, password, type_of_user) VALUES ('e71ab9bc-b698-11eb-8529-0242ac130003', 'Djura', 'Djuric', 'djura@gmail.com', 'djura1234', 2);
INSERT INTO USERS (user_id, name, surname, username, password, type_of_user) VALUES ('ed5de8c6-b698-11eb-8529-0242ac130003', 'Stefan', 'Stefanovic', 'stefan@gmail.com', 'stefan1234', 2);
INSERT INTO USERS (user_id, name, surname, username, password, type_of_user) VALUES ('ed5de8c6-b698-11eb-8529-0242ac130004', 'Stefan', 'Stefanovic', 'stefan3@gmail.com', 'stefan1234', 2);
INSERT INTO USERS (user_id, name, surname, username, password, type_of_user) VALUES ('ed5de8c6-b698-11eb-8529-0242ac130005', 'Stefan', 'Stefanovic', 'stefan4@gmail.com', 'stefan1234', 2);
INSERT INTO USERS (user_id, name, surname, username, password, type_of_user) VALUES ('ed5de8c6-b698-11eb-8529-0242ac130006', 'Stefan', 'Stefanovic', 'stefan5@gmail.com', 'stefan1234', 2);
INSERT INTO USERS (user_id, name, surname, username, password, type_of_user) VALUES ('ed5de8c6-b698-11eb-8529-0242ac130007', 'Stefan', 'Stefanovic', 'stefan6@gmail.com', 'stefan1234', 2);
INSERT INTO USERS (user_id, name, surname, username, password, type_of_user) VALUES ('ed5de8c6-b698-11eb-8529-0242ac130008', 'Stefan', 'Stefanovic', 'stefan7@gmail.com', 'stefan1234', 2);


INSERT INTO TEACHERS(user_id) VALUES('dfc9bc30-b698-11eb-8529-0242ac130003');
INSERT INTO TEACHERS(user_id) VALUES('e39930d4-b698-11eb-8529-0242ac130003');

INSERT INTO SUBSCRIBERS(user_id) VALUES('e71ab9bc-b698-11eb-8529-0242ac130003');
INSERT INTO SUBSCRIBERS(user_id) VALUES('ed5de8c6-b698-11eb-8529-0242ac130003');
INSERT INTO SUBSCRIBERS(user_id) VALUES('ed5de8c6-b698-11eb-8529-0242ac130004');
INSERT INTO SUBSCRIBERS(user_id) VALUES('ed5de8c6-b698-11eb-8529-0242ac130005');
INSERT INTO SUBSCRIBERS(user_id) VALUES('ed5de8c6-b698-11eb-8529-0242ac130006');
INSERT INTO SUBSCRIBERS(user_id) VALUES('ed5de8c6-b698-11eb-8529-0242ac130007');
INSERT INTO SUBSCRIBERS(user_id) VALUES('ed5de8c6-b698-11eb-8529-0242ac130008');



INSERT INTO COURSES(course_id, title, description, duration, price, year, level, user_id) VALUES ('525ce06e-b69f-11eb-8529-0242ac130003', 'Introduction to Python', 'Basic knowledge in Python!', 90, 149, '2019-01-20', 0, 'dfc9bc30-b698-11eb-8529-0242ac130003' );
INSERT INTO COURSES(course_id, title, description, duration, price, year, level, user_id) VALUES ('8f2eaabc-b6a0-11eb-8529-0242ac130003', 'Introduction to Java', 'Basic knowledge in Java!', 120, 149, '2020-02-25', 0, 'e39930d4-b698-11eb-8529-0242ac130003' );
INSERT INTO COURSES(course_id, title, description, duration, price, year, level, user_id) VALUES ('959984ee-b6a0-11eb-8529-0242ac130003', 'Introduction to Algorithm', 'Learn algorithm in Python language!', 200, 149, '2021-01-25', 1, 'dfc9bc30-b698-11eb-8529-0242ac130003' );


INSERT INTO COURSE_AREA(course_id, area_id) VALUES ('525ce06e-b69f-11eb-8529-0242ac130003', '27a25265-97b8-49a6-8f58-4f2889211d2f');
INSERT INTO COURSE_AREA(course_id, area_id) VALUES ('8f2eaabc-b6a0-11eb-8529-0242ac130003', 'add8be5a-b67b-11eb-8529-0242ac130003');
INSERT INTO COURSE_AREA(course_id, area_id) VALUES ('959984ee-b6a0-11eb-8529-0242ac130003', '27a25265-97b8-49a6-8f58-4f2889211d2f');
INSERT INTO COURSE_AREA(course_id, area_id) VALUES ('959984ee-b6a0-11eb-8529-0242ac130003', 'b11660c2-b67b-11eb-8529-0242ac130003');


INSERT INTO PRECONDITIONS(course_id, precondition_id) VALUES ('959984ee-b6a0-11eb-8529-0242ac130003', '525ce06e-b69f-11eb-8529-0242ac130003');

INSERT INTO SKILL_BY_COURSE(course_id, skill_id) VALUES ('525ce06e-b69f-11eb-8529-0242ac130003', '8723dd52-b67c-11eb-8529-0242ac130003');
INSERT INTO SKILL_BY_COURSE(course_id, skill_id) VALUES ('8f2eaabc-b6a0-11eb-8529-0242ac130003', '83db1994-b67c-11eb-8529-0242ac130003');
INSERT INTO SKILL_BY_COURSE(course_id, skill_id) VALUES ('959984ee-b6a0-11eb-8529-0242ac130003', '8723dd52-b67c-11eb-8529-0242ac130003');
INSERT INTO SKILL_BY_COURSE(course_id, skill_id) VALUES ('959984ee-b6a0-11eb-8529-0242ac130003', 'e421aca8-b6a1-11eb-8529-0242ac130003');


INSERT INTO SUBSCRIBER_ON_COURSES(user_id, course_id) VALUES ('e71ab9bc-b698-11eb-8529-0242ac130003', '525ce06e-b69f-11eb-8529-0242ac130003');
INSERT INTO SUBSCRIBER_ON_COURSES(user_id, course_id) VALUES ('e71ab9bc-b698-11eb-8529-0242ac130003', '8f2eaabc-b6a0-11eb-8529-0242ac130003');
INSERT INTO SUBSCRIBER_ON_COURSES(user_id, course_id) VALUES ('ed5de8c6-b698-11eb-8529-0242ac130003', '525ce06e-b69f-11eb-8529-0242ac130003');
INSERT INTO SUBSCRIBER_ON_COURSES(user_id, course_id) VALUES ('ed5de8c6-b698-11eb-8529-0242ac130003', '8f2eaabc-b6a0-11eb-8529-0242ac130003');
INSERT INTO SUBSCRIBER_ON_COURSES(user_id, course_id) VALUES ('ed5de8c6-b698-11eb-8529-0242ac130004', '8f2eaabc-b6a0-11eb-8529-0242ac130003');
INSERT INTO SUBSCRIBER_ON_COURSES(user_id, course_id) VALUES ('ed5de8c6-b698-11eb-8529-0242ac130005', '8f2eaabc-b6a0-11eb-8529-0242ac130003');
INSERT INTO SUBSCRIBER_ON_COURSES(user_id, course_id) VALUES ('ed5de8c6-b698-11eb-8529-0242ac130006', '8f2eaabc-b6a0-11eb-8529-0242ac130003');
INSERT INTO SUBSCRIBER_ON_COURSES(user_id, course_id) VALUES ('ed5de8c6-b698-11eb-8529-0242ac130007', '8f2eaabc-b6a0-11eb-8529-0242ac130003');
INSERT INTO SUBSCRIBER_ON_COURSES(user_id, course_id) VALUES ('ed5de8c6-b698-11eb-8529-0242ac130008', '8f2eaabc-b6a0-11eb-8529-0242ac130003');