DROP TABLE IF EXISTS CUSTOMER;
DROP TABLE IF EXISTS REWARD_TRANSACTION;
DROP TABLE IF EXISTS SECURITY_USER;
CREATE TABLE CUSTOMER (CUSTOMER_ID int, CUSTOMER_NAME VARCHAR2(50) );
CREATE TABLE REWARD_TRANSACTION (TRANSACTION_ID int,CUSTOMER_ID int ,TRANSACTION_DATE DATE,AMOUNT int);
CREATE TABLE SECURITY_USER (ID int, USERNAME VARCHAR2(50),ROLE VARCHAR2(50),PASSWORD VARCHAR2(50));

