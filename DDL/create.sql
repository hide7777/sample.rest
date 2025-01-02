drop user test_u1 CASCADE;
create user test_u1 identified by test_u1
	default tablespace users
	temporary tablespace temp
	quota unlimited on users
;
grant connect, resource, dba to test_u1;

alter session set current_schema = test_u1;

--DROP TABLE USERS;
CREATE TABLE USERS 
(
  "ID" number NOT NULL ENABLE, 
  "NAME" VARCHAR2(100 BYTE), 
  "EMAIL" VARCHAR2(100 BYTE), 
  "STARTDATE" DATE,  
  "LANK" NUMBER,
  CONSTRAINT PK_USERS PRIMARY KEY (ID)
);
