-- 계정 생성
CREATE USER spring IDENTIFIED BY spring;

-- 권한 부여
GRANT CONNECT, RESOURCE TO spring;

-- 테이블스페이
alter USER spring default tablespace users quota unlimited on users;

-- 시퀀스
CREATE SEQUENCE SEQ_USER;
CREATE SEQUENCE SEQ_POST;

-- 테이블 생성
CREATE TABLE users(
                      id NUMBER(4) PRIMARY KEY,
                      userid VARCHAR2(20),
                      password VARCHAR2(30),
                      name VARCHAR2(30)
);

create TABLE board(
                      id NUMBER(4) PRIMARY KEY,
                      title VARCHAR2(20),
                      content LONG,
                      writer NUMBER(4) REFERENCES users(id),
                      reg_date DATE DEFAULT SYSDATE,
                      storeFileName VARCHAR2(100),
                      fileName VARCHAR2(100)
)