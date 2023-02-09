INSERT INTO courses (id,description,image,instructor,"name",price, quota) VALUES
    (1,'An in-depth course covering the LeSS principles, framework and rules, and guides. It provides essential information for adopting and improving LeSS to your product development group.','assets/images/courses/clp.png','BAS VODDE','Certified LeSS Practitioner',40000, 30);

INSERT INTO classes (id, course_id, start_date, end_date) VALUES
    (1, 1, '2023-05-29 09:00:00', '2023-06-01 18:00:00')