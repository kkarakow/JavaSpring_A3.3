CREATE TABLE EMPLOYEES
( EMPLOYEE_ID NUMERIC(6,0),
FIRST_NAME VARCHAR(20 ),
LAST_NAME VARCHAR(25) CONSTRAINT "EMP_LAST_NAME_NN" NOT NULL , 
EMAIL VARCHAR(25) CONSTRAINT "EMP_EMAIL_NN" NOT NULL , 
PHONE_NUMBER VARCHAR(20),
HIRE_DATE DATE CONSTRAINT "EMP_HIRE_DATE_NN" NOT NULL ,
JOB_ID VARCHAR(10) CONSTRAINT "EMP_JOB_NN" NOT NULL ,
SALARY NUMERIC(8,2),
COMMISSION_PCT NUMERIC(4,2),
MANAGER_ID NUMERIC(6,0),
DEPARTMENT_ID NUMERIC(4,0)
);

CREATE TABLE DEPARTMENTS
( DEPARTMENT_ID NUMERIC(4,0),
"DEPARTMENT_NAME" VARCHAR(30) CONSTRAINT DEPT_NAME_NN NOT NULL , 
"MANAGER_ID" NUMERIC(6,0),
"LOCATION_ID" NUMERIC(4,0)
);

create table SEC_USER
(
  userId            BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  email             VARCHAR(75) NOT NULL UNIQUE,
  encryptedPassword VARCHAR(128) NOT NULL,
  enabled           BIT NOT NULL 
) ;

create table SEC_ROLE
(
  roleId   BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  roleName VARCHAR(30) NOT NULL UNIQUE
) ;

create table USER_ROLE
(
  ID     BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  userId BIGINT NOT NULL,
  roleId BIGINT NOT NULL
);

alter table USER_ROLE
  add constraint USER_ROLE_UK unique (userId, roleId);

alter table USER_ROLE
  add constraint USER_ROLE_FK1 foreign key (userId)
  references SEC_USER (userId);
 
alter table USER_ROLE
  add constraint USER_ROLE_FK2 foreign key (roleId)
  references SEC_ROLE (roleId);