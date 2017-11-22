DROP TABLE IF EXISTS EQUIVALENT_COURSES;
DROP TABLE IF EXISTS COURSE;
DROP TABLE IF EXISTS SCHOOL;
DROP VIEW IF EXISTS Non_Equivalent_Courses;
DROP VIEW IF EXISTS Equivalent_Courses_View;
DROP VIEW IF EXISTS View_Equivalent_Courses;

CREATE TABLE SCHOOL (
    SCHOOLID INTEGER AUTO_INCREMENT NOT NULL,
    OTHERID VARCHAR(100),
    name VARCHAR(100),
    address VARCHAR(100),
    city VARCHAR(100),
    state VARCHAR(100),
    zipCode VARCHAR(100),
    PRIMARY KEY (schoolID),
    UNIQUE KEY (name, address, city, state, zipCode)
);

LOAD DATA LOCAL INFILE '/Users/frederickmbw/SQL/Project/fafsaschools.txt' INTO TABLE School 
(otherID, name, address, city, state, zipCode);

#Get set of every SID like given school name
DROP PROCEDURE IF EXISTS getSIDwithSchoolName;
DELIMITER //
CREATE PROCEDURE getSIDwithSchoolName
(IN name VARCHAR(100))
BEGIN
SELECT 
    S.SCHOOLID, S.name, S.state, S.city, S.address
FROM
    SCHOOL AS S
WHERE
    S.name LIKE name;
END //
DELIMITER ;

#Get set of every SID with given otherID
DROP PROCEDURE IF EXISTS getSIDwithOtherID;
DELIMITER //
CREATE PROCEDURE getSIDwithOtherID
(IN other VARCHAR(100))
BEGIN
SELECT 
    S.SCHOOLID, S.name, S.state, S.city, S.address
FROM
    SCHOOL AS S
WHERE
    S.OTHERID = other;
END //
DELIMITER ;

#Add a school to the database
DROP PROCEDURE IF EXISTS AddSchool;
DELIMITER //
CREATE PROCEDURE AddSchool
(IN inOTHERID VARCHAR(100), inname VARCHAR(100), inaddress VARCHAR(100), incity VARCHAR(100), instate VARCHAR(100), inzipCode VARCHAR(100))
BEGIN
INSERT INTO SCHOOL (OTHERID, name, address, city, state, zipCode)
VALUES (inOTHERID, inname, inaddress, incity, instate, inzipCode);
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
    PRIMARY KEY (CID),
    FOREIGN KEY (SID)
        REFERENCES SCHOOL (schoolID),
	UNIQUE KEY (SID, CNAME)
);

#Add a course to the database
DROP PROCEDURE IF EXISTS AddCourse;
DELIMITER //
CREATE PROCEDURE AddCourse
(IN inSID INTEGER, inCNAME VARCHAR(30), inTITLE VARCHAR(140), inDEPARTMENT VARCHAR(30), inCREDITS TINYINT(100), inDESCRIPTION VARCHAR(1024), inCOURSE_OUTCOMES VARCHAR(1024))
BEGIN
INSERT INTO COURSE (SID, CNAME, TITLE, DEPARTMENT, CREDITS, DESCRIPTION, COURSE_OUTCOMES)
VALUES (inSID, inCNAME, inTITLE, inDEPARTMENT, inCREDITS, inDESCRIPTION, inCOURSE_OUTCOMES);
END //
DELIMITER ;

#Get set of every CID with given course name and school name
DROP PROCEDURE IF EXISTS getCIDwithCourseNameAndSchoolName;
DELIMITER //
CREATE PROCEDURE getCIDwithCourseNameAndSchoolName
(IN COURSE_NAME VARCHAR(30), SCHOOL_NAME VARCHAR(30))
BEGIN
SELECT 
	C.CNAME, C.CID, S.name, S.state, S.city, S.address
FROM
    COURSE AS C,
    SCHOOL AS S
WHERE
	C.CNAME LIKE COURSE_NAME 
    AND S.SCHOOLID = C.SID
    AND S.name LIKE SCHOOL_NAME;
END //
DELIMITER ;

#Get set of every CID with given course name and SID
DROP PROCEDURE IF EXISTS GetCIDwithCourseNameAndSID;
DELIMITER //
CREATE PROCEDURE GetCIDwithCourseNameAndSID
(IN COURSE_NAME VARCHAR(30), SCHOOL_SID INTEGER)
BEGIN
SELECT 
	C.CNAME, C.CID, C.SID
FROM
    COURSE AS C
WHERE
	C.CNAME LIKE COURSE_NAME 
    AND C.SID = SCHOOL_SID;
END //
DELIMITER ;

INSERT INTO COURSE (SID, CNAME, TITLE, DEPARTMENT, CREDITS, DESCRIPTION, COURSE_OUTCOMES)
VALUES 
(1, "CS 101", "Technology and Computer Science", "Computer Science", "5", "Introduces concepts of computer science through development of fluency in modern technology, while offering students an opportunity to increase skills in a variety of information systems. Computer lab work includes operation of computers on networks, programming fundamentals, logical reasoning, web searching, multimedia applications, basic spreadsheets, and database manipulation.", "Identify standard human-computer interfaces using industry standard terminology. Describe network components of computers and associated storage systems. Effectively search the Internet and use information to create a basic html web page. Describe similarities and differences between binary and decimal systems. Provide a descriptive algorithm for solving a problem. Identify digital versus analog representations of pictures and sounds. Identify and explain common spreadsheet functions and capabilities. Identify and explain common database management functions and capabilities Explain what a program is, and how a program is produced."),
(1, "CS 210", "Fundamentals of Computer Science I", "Computer Science", "5", "Introduces computer science and programming for CS majors. Students learn design and implementation of algorithms and programming in a structured, modular language, with emphasis on problem solving, program design, and style. ", "Solve computer programming problems using current industry standards in software engineering; editing, compiling, and executing code; which is structured in maintainable style with programmer comments (documentation). Define the concepts of data types, specifically the use of primitive data versus object, accessing and creating methods, their parameters passed, and returned. Use procedural decomposition to construct programming solutions using decision controls (if, else), repetitions (for, do while), and external file reading (secondary storage). Utilize simple data structures such as arrays and classes to solve complex problems, and use an Integrated Development Environment (IDE) to fix potential errors identified with code testing concepts."),
(1, "CS 211", "Fundamentals of Computer Science II ", "Computer Science", "5", "Continues CS 210, with data structures algorithm analysis and inheritance. Students learn to create collections, lists, binary trees, and sets. Other topics include sets, generic data types, sorting, recursion, run-time complexity, and graphical user interfaces.", "Create classes via inheritance, use their objects to demonstrate polymorphism of both interfaces and abstract classes; and explain the role of generic class templates within classes. Explain the principles of recursion versus repetition, and write recursive methods for a variety of tasks. Implement and contrast the uses of various data structures including arrays, sets, lists, collections, and trees. Recognize the use of Big-O notation to explain program performance in searching, sorting, recursion, and implementation of existing methods from a software API (Application Programming Interface). Compose programs that facilitate error handling using API standard Exceptions with try-catch blocks. Design programs using a Graphical User Interface (GUI) and event driven programming."),
(1, "CS 300", "Data Structures", "Computer Science", "5", "This course is an introduction to the fundamental concept of data structures. It explains how to organize and store data efficiently using data structures and how to select appropriate data structures. The course further focuses on understanding the fundamental algorithms and analyzing the time and space complexity of these algorithms.", "Distinguish the appropriate data structure for modeling a given problem and to provide justification for that selection. Implement and use each of the following fundamental data structures: Lists, Stacks, Queues, Trees. Explain the heap property and the use of heaps as an implementation of priority queues. Compare alternative implementations of data structures with respect to performance. Implement sorting, searching and hashing algorithms. Explain the "),
(1, "CS 331", "Database Systems", "Computer Science", "5", "The course covers the fundamental concepts of database systems. It teaches students the internals of database systems including data model, database design, relational model, relational algebra, SQL, indexing, concurrency control, query processing, transaction management and recovery. This course also aims to teach the new directions involving NoSQL persistence models. ", "Explain the components of a database system. Develop an entity-relationship (ER) model based on user requirements. Use the Structured Query Language (SQL) for database definition and manipulation. Generate an index file for a collection of resources. Convert an entity-relationship diagram to a set of normalized relations. Explain the ACID properties of a transaction. Describe techniques that are used for recovery. Design and implement a relational database system. Distinguish the differences and similarities between NoSQL databases and relational databases"),
(6234, "CSE142", "Computer Programming I", "Computer Science & Engineering", "4", "Basic programming-in-the-small abilities and concepts including procedural programming (methods, parameters, return, values), basic control structures (sequence, if/else, for loop, while loop), file processing, arrays, and an introduction to defining objects. Intended for students without prior programming experience.", "an ability to identify, formulate, and solve engineering problems. an ability to use the techniques, skills, and modern engineering tools. an ability to apply knowledge of mathematics, science, and engineering."),
(6234, "CSE143", "Computer Programming II", "Computer Science & Engineering", "4", "Continuation of 142. Concepts of data abstraction and encapsulation including stacks, queues, linked lists, binary trees, recursion, instruction to complexity and use of predefined collection classes.", "an ability to identify, formulate, and solve engineering problems. an ability to use the techniques, skills, and modern engineering tools. an ability to apply knowledge of mathematics, science, and engineering.");

#Make two courses equivalent to each other
CREATE TABLE EQUIVALENT_COURSES (
    COURSE1 INTEGER,
    COURSE2 INTEGER,
    IS_EQUIVALENT BOOL,
    PRIMARY KEY (COURSE1 , COURSE2),
    FOREIGN KEY (COURSE1)
        REFERENCES COURSE (CID),
    FOREIGN KEY (COURSE2)
        REFERENCES COURSE (CID)
);

#Make two courses equivalent
DROP PROCEDURE IF EXISTS AddEquivalentCourse;
DELIMITER //
CREATE PROCEDURE AddEquivalentCourse
(IN CID1 INTEGER, CID2 INTEGER, inIS_EQUIVALENT BOOL)
BEGIN
INSERT INTO EQUIVALENT_COURSES (COURSE1, COURSE2, IS_EQUIVALENT)
VALUES (CID1, CID2, inIS_EQUIVALENT);
END //
DELIMITER ;

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

INSERT INTO EQUIVALENT_COURSES (COURSE1, COURSE2, IS_EQUIVALENT)
VALUES 
(2, 6, TRUE),
(3, 7, TRUE),
(2, 7, FALSE);

#Set of equivalent and non-equivalent courses when given BC course name, other course name, and other school SID
DROP PROCEDURE IF EXISTS isEquivilentSID;
DELIMITER //
CREATE PROCEDURE isEquivilentSID
(IN BC_COURSE_NAME VARCHAR(30), OTHER_COURSE_NAME VARCHAR(30), OTHER_COURSE_SCHOOL_SID INTEGER)
BEGIN
SELECT 
	 E.IS_EQUIVALENT, S2.NAME, C2.CNAME
FROM
	EQUIVALENT_COURSES AS E,
    COURSE AS C1,
    COURSE AS C2,
    SCHOOL AS S2
WHERE
	C1.CID = E.COURSE1 
    AND C1.SID = 1
    AND C1.CNAME = BC_COURSE_NAME
    AND C2.CID = E.COURSE2
    AND C2.CNAME LIKE OTHER_COURSE_NAME
    AND C2.SID = S2.SCHOOLID
    AND S2.SID = OTHER_COURSE_SCHOOL_SID;
END //
DELIMITER ;

#Set of equivalent and non-equivalent courses when given BC course name, other course name, and other school name
DROP PROCEDURE IF EXISTS isEquivilentSchoolName;
DELIMITER //
CREATE PROCEDURE isEquivilentSchoolName
(IN BC_COURSE_NAME VARCHAR(30), OTHER_COURSE_NAME VARCHAR(30), OTHER_COURSE_SCHOOL_NAME VARCHAR(30))
BEGIN
SELECT 
	E.IS_EQUIVALENT, S2.NAME, C2.CNAME, S.state, S.city, S.address
FROM
	EQUIVALENT_COURSES AS E,
    COURSE AS C1,
    COURSE AS C2,
    SCHOOL AS S2
WHERE
	C1.CID = E.COURSE1 
    AND C1.SID = 1
    AND C1.CNAME = BC_COURSE_NAME
    AND C2.CID = E.COURSE2
    AND C2.CNAME LIKE OTHER_COURSE_NAME
    AND C2.SID = S2.SCHOOLID
    AND S2.NAME LIKE OTHER_COURSE_SCHOOL_NAME;
END //
DELIMITER ;

#View of every non-equivalent course
CREATE VIEW Equivalent_Courses_View
AS SELECT 
	C1.CNAME AS BC_COURSE, C2.CNAME, S2.NAME
FROM
	EQUIVALENT_COURSES AS E,
    COURSE AS C1,
    COURSE AS C2,
    SCHOOL AS S2
WHERE
	C1.CID = E.COURSE1 
    AND C1.SID = 1
    AND C2.CID = E.COURSE2
    AND C2.SID = S2.SCHOOLID
    AND E.IS_EQUIVALENT = 1;

#View of every equivalent course
CREATE VIEW Non_Equivalent_Courses
AS SELECT 
	C1.CNAME AS BC_COURSE, C2.CNAME, S2.NAME
FROM
	EQUIVALENT_COURSES AS E,
    COURSE AS C1,
    COURSE AS C2,
    SCHOOL AS S2
WHERE
	C1.CID = E.COURSE1 
    AND C1.SID = 1
    AND C2.CID = E.COURSE2
    AND C2.SID = S2.SCHOOLID
    AND E.IS_EQUIVALENT = 0;