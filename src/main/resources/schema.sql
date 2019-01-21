--DROP TABLE IF EXISTS student;
--DROP TABLE IF EXISTS course;
--DROP TABLE IF EXISTS student_courses;

CREATE SEQUENCE STUDENT_SEQ start with 1 increment by 1 ;

CREATE TABLE student(
	id BIGINT DEFAULT nextval('student_seq'),
	name VARCHAR(200),
	address VARCHAR(200),
	email VARCHAR(200),
	phone_number VARCHAR(200),
	student_type VARCHAR(20)
);


CREATE SEQUENCE COURSE_SEQ start with 1 increment by 1 ;

CREATE TABLE course(
	id BIGINT DEFAULT nextval('course_seq'),
	course_name VARCHAR(200),
	min_size INTEGER,
	max_size INTEGER,
	course_description VARCHAR(512),
	teacher_name VARCHAR(200),
	course_type VARCHAR(50),
	PRIMARY KEY (id)
);

CREATE TABLE student_courses(
	id NUMERIC IDENTITY PRIMARY KEY,
	student_id NUMERIC NOT NULL,
	course_id NUMERIC NOT NULL,
	course_status VARCHAR(20),
	completion_status VARCHAR(20)
);
