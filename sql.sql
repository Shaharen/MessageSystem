1. ȸ�������� ������ ������ �� �ִ� 'web_member'���̺��� ����ÿ�.
   �÷��� : m_email / m_pw / m_tel / m_address

create table web_member(
 m_email varchar(50),
 m_pw varchar(50) not null,
 m_tel varchar(50) not null,
 m_address varchar(50) not null,
 constraint pk_web_member primary key(m_email)
 );

select * from web_member;

create table web_message(
 m_num number,
 m_sendName varchar(50) not null,
 m_receiveEmail varchar(50) not null,
 m_content varchar(200) not null,
 m_sendDate DATE not null,
 constraint pk_web_message primary key(m_num)
);

select * from web_message;

-- ������ ����
create sequence num_seq increment by 1 start with 1;

insert into web_message values( num_seq.nextval,'test','smhrd@naver.com','�׽�Ʈ����',sysdate);

drop table web_message;

drop sequence num_seq;








