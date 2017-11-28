
#**********************************************************************
#
#	Title:		cs 331 : CREATE SQL DB FILE
#	Authors:	Sean, Beau, Fred, Norm
#	
#				This file creates all the tables for our SQL database
#				there are 2 main parts:
#				(1)	-	Students, BC Members, Devices, Checkout, Maintenance
#				(2)	-	Courses, Schools
#
#**********************************************************************


#**********************************************************************
#																Part 1

drop table if exists checking_out_item;
drop table if exists maintenance;
drop table if exists student;
drop table if exists devices;
drop table if exists books;
DROP TABLE IF EXISTS EQUIVALENT_COURSES;
drop table if exists BC_Member;
#drop table if exists course_equivalency;
drop table if exists generic_item;

Create table generic_item(
item_id integer auto_increment primary key,
Price real,
Date_of_purchase date not null,
Status varchar(10) not null,
item_description varchar(40) 
);

create table BC_Member(
	SID varchar(16) not null,
	nameFirst varchar(20) not null,
	nameLast varchar(20) not null,
	address_street varchar(20),
	address_city varchar(20),
	address_state varchar(2),
	address_zip varchar(12),
	phone varchar(12),
	email varchar(40), 
	primary key (SID)
);
							
create table devices(
item_id integer primary key, 
device_id varchar(10) unique not null,
model_number varchar(20),
Foreign key(item_id) references generic_item(item_id));

create table books(
item_id integer primary key, 
title varchar(25),
book_type varchar(12),
edition integer,
ISBN varchar(20),	
book_condition varchar(10),
book_id varchar(10) unique,
Foreign key (item_id) references generic_item(item_id));

create table maintenance(
item_id integer not null,
description_of_maintenance varchar(25),
date_maintainance_start datetime not null,
date_maintainance_end datetime, 
primary key(item_id, date_maintainance_start),
foreign key(item_id) references generic_item(item_id)); 

create table student(
	SID varchar(16) primary key,
	standing varchar(2),
	cGPA real, 
	demographic varchar(15),
	dob date,
	advisor varchar(40),
	currently_enrolled bool,
	grad_date date,			
	foreign key (SID) references BC_Member(SID)
);

create table checking_out_item(
SID varchar(16) not null,
date_checked_out datetime not null, 
date_returned datetime, 
item_id integer not null,
primary key (SID, date_checked_out, item_id), 
foreign key (SID) references bc_member(SID),
foreign key (item_id) references generic_item(item_id));

#drop trigger if exists rent_update_status;

delimiter v
create trigger rent_update_status after insert on checking_out_item
	for each row begin
	update generic_item set generic_item.Status = ('Out')
		where generic_item.item_id = new.item_id and isnull(new.date_returned);
		end; v
delimiter ;

#drop trigger if exists maint_update_status;

delimiter v
create trigger maint_update_status after insert on maintenance
	for each row begin
	update generic_item set generic_item.Status = ('repairing')
		where generic_item.item_id = new.item_id and isnull(new.date_maintainance_end);
		end; v
delimiter ;

#drop trigger if exists rent_return_status;

delimiter v

create trigger rent_return_status before update on checking_out_item
	for each row begin
    if (new.date_returned < new.date_checked_out) then set new.date_returned=new.date_checked_out; end if;
	update generic_item set generic_item.Status = ('available')
		where generic_item.item_id = new.item_id and isnull(old.date_returned) and not isnull(new.date_returned);
		end; v
delimiter ;

#drop trigger if exists maint_return_status;

delimiter v
create trigger maint_return_status before update on maintenance
	for each row begin
    if (new.date_maintainance_end < new.date_maintainance_start) then set new.date_maintainance_end=new.date_maintainance_start; end if;
	update generic_item set generic_item.Status = ('available')
		where generic_item.item_id = new.item_id and isnull(old.date_maintainance_end) and not isnull(new.date_maintainance_end);
		end; v
delimiter ;

delete from BC_member where nameFirst = '';
INSERT INTO BC_Member (SID, nameFirst, nameLast, address_street, address_city, address_state, address_zip, phone,email) VALUES (111111111, 'Beau', 'Shirdavani', '5234th ave ne', 'Redmond', 'WA', '98053', '4258685555','Beau@bellevue');
INSERT INTO BC_Member (SID, nameFirst, nameLast, address_street, address_city, address_state, address_zip, phone,email) VALUES (555555555, 'Norm', 'Lew', '5234th ave ne', 'Seattle', 'WA', '98053', '4258685555','Norm@bellevue');
INSERT INTO BC_Member (SID, nameFirst, nameLast, address_street, address_city, address_state, address_zip, phone,email) VALUES (222222222, 'Sean', 'Hardin', '5234th ave ne', 'Kirkland', 'WA', '98053', '4258685555','sean#bellevue');
INSERT INTO BC_Member (SID, nameFirst, nameLast, address_street, address_city, address_state, address_zip, phone,email) VALUES (999999999, 'Fred', 'Wirtz', '5234th ave ne', 'Bellevue', 'WA', '98053', '4258685555','fred@bellevue');
insert into bc_member values(123456789, 'seth','dowell','up','higher','no','zoom','unreachable', 'still unreachable');
-- select * from bc_member;
update BC_member set email = concat(nameFirst, '@bellevuecollege.edu');
delete from student;
insert into student values (111111111, 'JR', 1,'dog','1980-01-01','Sara Farag', true, null);
insert into student values (555555555, 'FR', 2,'alien','2016-01-01' ,'Fatma Serce', true, null);
insert into student values (222222222, 'SO', 3, 'human','2000-01-01' , 'Fatma Serce', true, null);
insert into student values (999999999, 'SR', 4, 'mutant','1950-01-01' , 'Sara Farag', false,null);
insert into student values (123456789, 'SR', 5, 'genius', '1900-01-01', 'himself', false, '2000-01-01');
-- select * from student;

delete from generic_item;
insert into generic_item (Price, Date_of_purchase, Status, item_description) values (14.90, '2015-12-1', 'available', 'A book');
insert into generic_item (Price, Date_of_purchase, Status, item_description) values (500.99, '2016-06-1', 'available', 'Laptop');
insert into generic_item (Price, Date_of_purchase, Status, item_description) values (500.99, '2016-06-1', 'available', 'test auto book');
insert into generic_item (Price, Date_of_purchase, Status, item_description) values (500.94, '2016-02-1', 'available', 'test auto book2');
-- select * from generic_item;
delete from books;
insert into books (item_id, title, book_type, edition, ISBN, book_condition, book_id) values(00001, 'Databases YAY!', 'Hard Cover', 3, '9523-sdfdfds-2343', 'old/poor', 'four');
delete from devices;
insert into devices (item_id, device_id, model_number) values (00003, 2525, 'HP12323-003-Z');
-- select * from books;
-- select * from devices;
delete from checking_out_item;
insert into checking_out_item (SID, date_checked_out, date_returned, item_id) values (111111111, '2014-09-08 17:51:04.777777', '2014-09-08 17:55', 00001);
insert into checking_out_item (SID, date_checked_out, date_returned, item_id) values (555555555, '2014-09-09 17:51:04.777777', '2014-09-10 17:55', 00001);
insert into checking_out_item (SID, date_checked_out, date_returned, item_id) values (222222222, '2014-09-12 17:51:04.777777', '2014-09-15 17:55', 00001);
insert into checking_out_item (SID, date_checked_out, date_returned, item_id) values (999999999, '2014-10-01 17:51:04.777777', '2014-10-30 17:55', 00001);
insert into checking_out_item (SID, date_checked_out, date_returned, item_id) values (111111111, '2014-09-08 17:51:04.777777',null, 000000003);
insert into checking_out_item (SID, date_checked_out, date_returned, item_id) values (111111111, '2015-11-12 17:51:04.777777',null, 000000004);
insert into checking_out_item values (111111111, '2014-09-08 17:51:04', null, 2);
-- select * from checking_out_item;
delete from maintenance;
insert into maintenance (item_id, description_of_maintenance, date_maintainance_start, date_maintainance_end) values (00002, 'F key missing', '2014-09-08 17:55', '2014-09-20 17:55');
-- select * from maintenance;



#delete from maintenance;
#insert into maintenance (item_id, description_of_maintenance, date_maintainance_start) values (00002, 'A key missing', '2014-09-08 17:55');


#update generic_item set status='available' where status <> 'as';
#insert into checking_out_item values (999999999, '2015-09-08 17:55:00', null, 2);
#drop trigger rent_return_status;
#update maintenance set date_maintainance_end='2014-09-08 17:33:03' where item_id=1 and isnull(date_maintainance_end);
#insert into maintenance values(1,'ug',now(), null);

#select * from maintenance;
#select * from checking_out_item;
#select * from generic_item;
#update checking_out_item set date_returned = '2017-11-08 17:55:01' where item_id = 3 and isnull(date_returned);
#update checking_out_item set SID = 999999999 where item_id = 3;
#update checking_out_item set date_returned = null where sid = 555555555 ;
#update checking_out_item set item_id = 2 where sid = 555555555;
-- select datediff(dob, now())/365 from students_view;
-- update student set advisor = 'de' where sid = '3';
#update checking_out_item set date_returned = '2017-11-08 17:55:01' where item_id = 3;
#update checking_out_item set date_returned = null where item_id = 3;
-- select * from bc_member;
-- select * from student where currently_enrolled = true;
drop view if exists students_view, devices_view, books_view, maint_view, checkout, overdue, generic_items_view;
create view students_view as select student.Sid, nameFirst, nameLast, address_street, address_city, address_state, address_zip, phone, email, standing, cgpa, demographic, dob, advisor, grad_date, currently_enrolled from student, bc_member where student.sid=bc_member.sid;
-- select * from devices;
-- select * from generic_item;

#select 1 from students_view where true and now() > '2014-09-08 17:51:05';
create view generic_items_view as select * from generic_item where item_id not in (select item_id from devices) and item_id not in (select item_id from books);
create view devices_view as select generic_item.item_id, device_id, item_description, status, model_number, price, date_of_purchase from generic_item, devices where generic_item.item_id = devices.item_id;
create view books_view as select generic_item.item_id, book_id, title, status, item_description, edition, book_type, isbn, book_condition, price, date_of_purchase from books, generic_item where books.item_id = generic_item.item_id;
-- select * from books;
create view maint_view as select item_description, generic_item.item_id, description_of_maintenance, date_maintainance_start, date_maintainance_end from maintenance, books, devices, generic_item where maintenance.item_id=generic_item.item_id;
create view checkout as select bc_member.sid, namefirst, namelast, item_description, generic_item.item_id, date_checked_out, date_returned from checking_out_item, bc_member, devices, books, generic_item where checking_out_item.sid=bc_member.sid and checking_out_item.item_id=generic_item.item_id;
-- select * from checking_out_item;
create view overdue as select sid, namefirst, namelast, item_description, item_id date_checked_out, date_returned, datediff(now(),date_checked_out)-14 as 'days_overdue' from checkout where isnull(date_returned) and datediff(now(), date_checked_out) > 14;














#**********************************************************************
#																Part 2
DROP TABLE IF EXISTS EQUIVALENT_COURSES;
DROP TABLE IF EXISTS COURSE;
DROP TABLE IF EXISTS SCHOOL;

CREATE TABLE SCHOOL (
    SID INTEGER AUTO_INCREMENT NOT NULL,
    OID VARCHAR(100),
    SNAME VARCHAR(100) NOT NULL,
    ADDRESS VARCHAR(100),
    CITY VARCHAR(100),
    STATE VARCHAR(100),
    ZIPCODE VARCHAR(100),
    PRIMARY KEY (SID),
    UNIQUE KEY (SNAME, ADDRESS, CITY, STATE, ZIPCODE)
);

#Add a school to the database
DROP PROCEDURE IF EXISTS AddSchool;
DELIMITER //
CREATE PROCEDURE AddSchool
(IN inOID VARCHAR(100), inSNAME VARCHAR(100), inADDRESS VARCHAR(100), inCITY VARCHAR(100), inSTATE VARCHAR(100), inZIPCODE VARCHAR(100))
BEGIN
INSERT INTO SCHOOL (OID, SNAME, ADDRESS, CITY, STATE, ZIPCODE)
VALUES (inOID, inSNAME, inADDRESS, inCITY, inSTATE, inZIPCODE);
END //
DELIMITER ;

#Update a school in the database
DROP PROCEDURE IF EXISTS UpdateSchool;
DELIMITER //
CREATE PROCEDURE UpdateSchool
(IN inSID INTEGER, IN inOID VARCHAR(100), IN inSNAME VARCHAR(100), IN inADDRESS VARCHAR(100), IN inCITY VARCHAR(100), IN inSTATE VARCHAR(100), IN inZIPCODE VARCHAR(100))
BEGIN
UPDATE 
	SCHOOL
SET 
	OID = inOID, SNAME = inSNAME, ADDRESS = inADDRESS, CITY = inCITY, STATE = inSTATE, ZIPCODE = inZIPCODE
WHERE 	
	SID = inSID;
END //
DELIMITER ;

#Get set of every SID with the given information
DROP PROCEDURE IF EXISTS searchSchool;
DELIMITER //
CREATE PROCEDURE searchSchool
(IN inOID VARCHAR(100), IN inSNAME VARCHAR(100), IN inADDRESS VARCHAR(100), IN inCITY VARCHAR(100), IN inSTATE VARCHAR(100), IN inZIPCODE VARCHAR(100))
BEGIN
SELECT 
    S.SID, S.OID, S.SNAME, S.ADDRESS, S.CITY, S.STATE, S.ZIPCODE
FROM
    SCHOOL AS S
WHERE
    S.OID LIKE inOID
    AND S.SNAME LIKE inSNAME
    AND S.ADDRESS LIKE inADDRESS
    AND S.CITY LIKE inCITY
    AND S.STATE LIKE inSTATE
    AND S.ZIPCODE LIKE inZIPCODE;
END //
DELIMITER ;

#Return the school with the matching SID
DROP PROCEDURE IF EXISTS getSchool;
DELIMITER //
CREATE PROCEDURE getSchool
(IN inSID INTEGER)
BEGIN
SELECT 
    S.SID, S.OID, S.SNAME, S.ADDRESS, S.CITY, S.STATE, S.ZIPCODE
FROM
    SCHOOL AS S
WHERE
    S.SID = inSID;
END //
DELIMITER ;

CREATE TABLE COURSE (
    CID INTEGER AUTO_INCREMENT,
    SID INTEGER NOT NULL,
    CNAME VARCHAR(30) NOT NULL,
    TITLE VARCHAR(140) NOT NULL,
    DEPARTMENT VARCHAR(30),
    CREDITS TINYINT NOT NULL,
    DESCRIPTION VARCHAR(1024),
    COURSE_OUTCOMES VARCHAR(1024),
    CONTACT_EMAIL VARCHAR(100),
    CONTACT_NAME VARCHAR (100),
    PRIMARY KEY (CID),
    FOREIGN KEY (SID)
        REFERENCES SCHOOL (SID),
	UNIQUE KEY (SID, CNAME)
);

#Add a course to the database
DROP PROCEDURE IF EXISTS AddCourse;
DELIMITER //
CREATE PROCEDURE AddCourse
(IN inSID INTEGER, inCNAME VARCHAR(30), inTITLE VARCHAR(140), inDEPARTMENT VARCHAR(30), inCREDITS TINYINT(100), inDESCRIPTION VARCHAR(1024), inCOURSE_OUTCOMES VARCHAR(1024), inCONTACT_EMAIL VARCHAR(100), inCONTACT_NAME VARCHAR(100))
BEGIN
INSERT INTO COURSE (SID, CNAME, TITLE, DEPARTMENT, CREDITS, DESCRIPTION, COURSE_OUTCOMES, CONTACT_EMAIL, CONTACT_NAME)
VALUES (inSID, inCNAME, inTITLE, inDEPARTMENT, inCREDITS, inDESCRIPTION, inCOURSE_OUTCOMES, inCONTACT_EMAIL, inCONTACT_NAME);
END //
DELIMITER ;

#Update a course in the data base
DROP PROCEDURE IF EXISTS UpdateCourse;
DELIMITER //
CREATE PROCEDURE UpdateCourse
(IN inCID INTEGER, IN inSID INTEGER, IN inCNAME VARCHAR(30), IN inTITLE VARCHAR(140), IN inDEPARTMENT VARCHAR(30), IN inCREDITS TINYINT, IN inDESCRIPTION VARCHAR(1024), IN inCOURSE_OUTCOMES VARCHAR(1024), IN inCONTACT_EMAIL VARCHAR(100), IN inCONTACT_NAME VARCHAR(100))
BEGIN
UPDATE 
	COURSE
SET 
	SID = inSID, CNAME = inCNAME, TITLE = inTITLE, DEPARTMENT = inDEPARTMENT, CREDITS = inCREDITS, DESCRIPTION = inDESCRIPTION, COURSE_OUTCOMES = inCOURSE_OUTCOMES, CONTACT_EMAIL = inCONTACT_EMAIL, CONTACT_NAME = inCONTACT_NAME
WHERE 	
	CID = inCID;
END //
DELIMITER ;

#Return a course with the matching CID
DROP PROCEDURE IF EXISTS getCourse;
DELIMITER //
CREATE PROCEDURE getCourse
(IN inCID INTEGER)
BEGIN
SELECT 
	CID, SID, CNAME, TITLE, DEPARTMENT, CREDITS, DESCRIPTION, COURSE_OUTCOMES, CONTACT_EMAIL, CONTACT_NAME
FROM
    COURSE
WHERE
	CID = inCID;
END //
DELIMITER ;

#Get set of every Course with given information
DROP PROCEDURE IF EXISTS searchCourse;
DELIMITER //
CREATE PROCEDURE searchCourse
(IN inCNAME VARCHAR(30), inTITLE VARCHAR(140), inDEPARTMENT VARCHAR(30), inSNAME VARCHAR(30))
BEGIN
SELECT 
	C.CID, C.CNAME, C.TITLE, C.DEPARTMENT, C.CREDITS, C.DESCRIPTION, C.COURSE_OUTCOMES, C.CONTACT_NAME, C.CONTACT_EMAIL, S.SNAME, S.SID
FROM
    COURSE AS C,
    SCHOOL AS S
WHERE
	C.CNAME LIKE inCNAME 
    AND C.TITLE LIKE inTITLE
    AND C.DEPARTMENT LIKE inDEPARTMENT
    AND S.SID = C.SID
    AND S.SNAME LIKE inSNAME;
END //
DELIMITER ;

#Get every CID that is offered at a school with the matching SID and othe information
DROP PROCEDURE IF EXISTS searchCourseWithSID;
DELIMITER //
CREATE PROCEDURE searchCourseWithSID
(IN inCNAME VARCHAR(30), inTITLE VARCHAR(140), inDEPARTMENT VARCHAR(30), inSID INTEGER)
BEGIN
SELECT 
	C.CID, C.CNAME, C.TITLE, C.DEPARTMENT, C.CREDITS, C.DESCRIPTION, C.COURSE_OUTCOMES, C.CONTACT_NAME, C.CONTACT_EMAIL, C.SID
FROM
    COURSE AS C
WHERE
	C.CNAME LIKE inCNAME
    AND C.TITLE LIKE inTITLE
    AND C.DEPARTMENT LIKE inDEPARTMENT
    AND C.SID = inSID;
END //
DELIMITER ;

#Get set of every CID with given course name and SID
DROP PROCEDURE IF EXISTS getCourseNameAndSchool;
DELIMITER //
CREATE PROCEDURE getCourseNameAndSchool
(inCID INTEGER)
BEGIN
SELECT 
	C.CNAME, S.SNAME
FROM
    COURSE AS C,
    SCHOOL AS S
WHERE
	C.CID = inCID
    AND S.SID = C.SID;
END //
DELIMITER ;

#A view of every CID
DROP VIEW IF EXISTS EVERYCID;
CREATE VIEW EVERYCID
AS SELECT
	CID
FROM
	COURSE;

#Table of two courses and if they are equivalent
CREATE TABLE EQUIVALENT_COURSES (
    COURSE1 INTEGER,
    COURSE2 INTEGER,
    IS_EQUIVALENT BOOL,
    EC_COMMENT VARCHAR(140),
    APPROVED DATE,
    REVIEWED_BY VARCHAR(16),
    PRIMARY KEY (COURSE1 , COURSE2),
    FOREIGN KEY (COURSE1)
        REFERENCES COURSE (CID),
    FOREIGN KEY (REVIEWED_BY)
    	REFERENCES BC_Member (SID),
    FOREIGN KEY (COURSE2)
        REFERENCES COURSE (CID)
);

#Add course equivalent record
DROP PROCEDURE IF EXISTS AddEquivalentCourse;
DELIMITER //
CREATE PROCEDURE AddEquivalentCourse
(IN CID1 INTEGER, CID2 INTEGER, inIS_EQUIVALENT BOOL, inEC_COMMENT VARCHAR(140), IN inSID VARCHAR(16))
BEGIN
INSERT INTO EQUIVALENT_COURSES (COURSE1, COURSE2, IS_EQUIVALENT, EC_COMMENT, APPROVED, REVIEWED_BY)
VALUES (CID1, CID2, inIS_EQUIVALENT, inEC_COMMENT, NOW(), inSID);
END //
DELIMITER ;

#Update if a school is equivalent
DROP PROCEDURE IF EXISTS UpdateEQUIVALENT;
DELIMITER //
CREATE PROCEDURE UpdateEQUIVALENT
(IN inCID1 INTEGER, inCID2 INTEGER, inIS_EQUIVALENT BOOL, inEC_COMMENT VARCHAR(140), IN inSID VARCHAR(16))
BEGIN
UPDATE EQUIVALENT_COURSES
SET COURSE1 = inCID1, COURSE2 = inCID2, IS_EQUIVALENT = inIS_EQUIVALENT, EC_COMMENT = inEC_COMMENT, APPROVED = CURDATE(), REVIEWED_BY = inSID
WHERE 	inCID1 = COURSE1 
		AND inCID2 = COURSE2;
END //
DELIMITER ;

#Trigger to prevent course being equivalent to its self
DELIMITER $$
CREATE TRIGGER NO_DUPLICATE_EQUIVALENT_COURSES BEFORE INSERT ON EQUIVALENT_COURSES
FOR EACH ROW 
BEGIN
	IF 
		NEW.COURSE1 = NEW.COURSE2
    THEN
        SIGNAL SQLSTATE '45000';
    END IF;
END$$
DELIMITER ;

#Course1 must be a BC course when creating an equivalent course
DELIMITER $$
CREATE TRIGGER BC_COURSE_REQUIRED BEFORE INSERT ON EQUIVALENT_COURSES
FOR EACH ROW 
BEGIN
	IF 
		NEW.COURSE1 NOT IN (SELECT C.CID
							FROM COURSE AS C
							WHERE C.SID = 1)
    THEN
        SIGNAL SQLSTATE '45000';
    END IF;
END$$
DELIMITER ;

#Gets the equivalent info for two courses
DROP PROCEDURE IF EXISTS getEquivalentInfo;
DELIMITER //
CREATE PROCEDURE getEquivalentInfo
(IN inCID1 INTEGER, inCID2 INTEGER)
BEGIN
SELECT 
	IS_EQUIVALENT, EC_COMMENT, APPROVED, REVIEWED_BY
FROM
	EQUIVALENT_COURSES
WHERE
	COURSE1 = inCID1
    AND COURSE2 = inCID2;
END //
DELIMITER ;

######LOAD DATA######

LOAD DATA LOCAL INFILE '/Users/frederickmbw/SQL/Project/fafsaschools.txt' INTO TABLE School 
(OID, SNAME, ADDRESS, CITY, STATE, ZIPCODE);

INSERT INTO COURSE (SID, CNAME, TITLE, DEPARTMENT, CREDITS, DESCRIPTION, COURSE_OUTCOMES)
VALUES 
(1, "CS 101", "Technology and Computer Science", "Computer Science", "5", "Introduces concepts of computer science through development of fluency in modern technology, while offering students an opportunity to increase skills in a variety of information systems. Computer lab work includes operation of computers on networks, programming fundamentals, logical reasoning, web searching, multimedia applications, basic spreadsheets, and database manipulation.", "Identify standard human-computer interfaces using industry standard terminology. Describe network components of computers and associated storage systems. Effectively search the Internet and use information to create a basic html web page. Describe similarities and differences between binary and decimal systems. Provide a descriptive algorithm for solving a problem. Identify digital versus analog representations of pictures and sounds. Identify and explain common spreadsheet functions and capabilities. Identify and explain common database management functions and capabilities Explain what a program is, and how a program is produced."),
(1, "CS 210", "Fundamentals of Computer Science I", "Computer Science", "5", "Introduces computer science and programming for CS majors. Students learn design and implementation of algorithms and programming in a structured, modular language, with emphasis on problem solving, program design, and style. ", "Solve computer programming problems using current industry standards in software engineering; editing, compiling, and executing code; which is structured in maintainable style with programmer comments (documentation). Define the concepts of data types, specifically the use of primitive data versus object, accessing and creating methods, their parameters passed, and returned. Use procedural decomposition to construct programming solutions using decision controls (if, else), repetitions (for, do while), and external file reading (secondary storage). Utilize simple data structures such as arrays and classes to solve complex problems, and use an Integrated Development Environment (IDE) to fix potential errors identified with code testing concepts."),
(1, "CS 211", "Fundamentals of Computer Science II ", "Computer Science", "5", "Continues CS 210, with data structures algorithm analysis and inheritance. Students learn to create collections, lists, binary trees, and sets. Other topics include sets, generic data types, sorting, recursion, run-time complexity, and graphical user interfaces.", "Create classes via inheritance, use their objects to demonstrate polymorphism of both interfaces and abstract classes; and explain the role of generic class templates within classes. Explain the principles of recursion versus repetition, and write recursive methods for a variety of tasks. Implement and contrast the uses of various data structures including arrays, sets, lists, collections, and trees. Recognize the use of Big-O notation to explain program performance in searching, sorting, recursion, and implementation of existing methods from a software API (Application Programming Interface). Compose programs that facilitate error handling using API standard Exceptions with try-catch blocks. Design programs using a Graphical User Interface (GUI) and event driven programming."),
(1, "CS 300", "Data Structures", "Computer Science", "5", "This course is an introduction to the fundamental concept of data structures. It explains how to organize and store data efficiently using data structures and how to select appropriate data structures. The course further focuses on understanding the fundamental algorithms and analyzing the time and space complexity of these algorithms.", "Distinguish the appropriate data structure for modeling a given problem and to provide justification for that selection. Implement and use each of the following fundamental data structures: Lists, Stacks, Queues, Trees. Explain the heap property and the use of heaps as an implementation of priority queues. Compare alternative implementations of data structures with respect to performance. Implement sorting, searching and hashing algorithms. Explain the "),
(1, "CS 331", "Database Systems", "Computer Science", "5", "The course covers the fundamental concepts of database systems. It teaches students the internals of database systems including data model, database design, relational model, relational algebra, SQL, indexing, concurrency control, query processing, transaction management and recovery. This course also aims to teach the new directions involving NoSQL persistence models. ", "Explain the components of a database system. Develop an entity-relationship (ER) model based on user requirements. Use the Structured Query Language (SQL) for database definition and manipulation. Generate an index file for a collection of resources. Convert an entity-relationship diagram to a set of normalized relations. Explain the ACID properties of a transaction. Describe techniques that are used for recovery. Design and implement a relational database system. Distinguish the differences and similarities between NoSQL databases and relational databases"),
(6234, "CSE142", "Computer Programming I", "Computer Science & Engineering", "4", "Basic programming-in-the-small abilities and concepts including procedural programming (methods, parameters, return, values), basic control structures (sequence, if/else, for loop, while loop), file processing, arrays, and an introduction to defining objects. Intended for students without prior programming experience.", "an ability to identify, formulate, and solve engineering problems. an ability to use the techniques, skills, and modern engineering tools. an ability to apply knowledge of mathematics, science, and engineering."),
(6234, "CSE143", "Computer Programming II", "Computer Science & Engineering", "4", "Continuation of 142. Concepts of data abstraction and encapsulation including stacks, queues, linked lists, binary trees, recursion, instruction to complexity and use of predefined collection classes.", "an ability to identify, formulate, and solve engineering problems. an ability to use the techniques, skills, and modern engineering tools. an ability to apply knowledge of mathematics, science, and engineering.");

INSERT INTO EQUIVALENT_COURSES (COURSE1, COURSE2, IS_EQUIVALENT, EC_COMMENT, APPROVED, REVIEWED_BY)
VALUES 
(1, 6, TRUE, "What?", CURDATE(), 111111111),
(1, 7, FALSE, "Sure?", CURDATE(), 111111111),
(2, 6, FALSE, "Okay?", CURDATE(), 111111111);











