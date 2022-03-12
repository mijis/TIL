-- 1. 계정 생성
create user user_jmj identified by test1234;

-- 계정접속, 자원사용 권한 부여
grant connect, resource to user_jmj;

-- tablespace 사용권한 부여
alter user user_jmj default tablespace users quota unlimited on users;


--2.
--- 2-1
create table member(
id varchar2(30) constraint pk_member primary key, pass varchar2(100),
name varchar2(50), gender char(1), tel varchar2(14), regdate date default sysdate);

create table schedule_movie(
movie_code char(15) constraint pk_schedul_movie primary key , mv_title varchar2(100),
mv_story varchar2(4000), mv_runtime number, mv_regdate date default sysdate);


create table reservation(
res_num number constraint pk_reservation primary key,
id varchar2(30) constraint fk_reservation_id references member(id),
movie_code char(15) constraint fk_reservation_movie_code references schedule_movie(movie_code),
regdate date default sysdate);

create table director(
dr_code number constraint pk_director primary key,
dr_name varchar2(50), dr_regdate date default sysdate,
movie_code char(15) constraint fk_director_movie_code references schedule_movie(movie_code));


-- 시퀀스 생성
create sequence seq_res_num
increment by 1
start with 1
maxvalue 4 ;

create sequence seq_movie_code
increment by 1
start with 1
maxvalue 4 ;

create sequence seq_dr_code
increment by 1
start with 1
maxvalue 5 ;

---2-2 데이터 추가
insert into MEMBER(id, pass, name, gender, tel)
values('son', 1234, '손재욱','M', '010-7361-9876');

insert into MEMBER(id, pass, name, gender, tel)
values('kim', 1234, '김영주','M', '010-6712-7652');

insert into MEMBER(id, pass, name, gender, tel)
values('jung', 1234, '정헌석','M', '010-7731-1471');
---------------------------------------------------------------

insert into schedule_movie(MOVIE_CODE, MV_TITLE, MV_STORY, MV_RUNTIME)
values(concat('MV_00000000000', seq_movie_code.nextval),
			'007 노 타임 투 다이(No time to Die)',
			'가장 강력한 운명의 적과 마주하게된 제임스 본드의 마지막 미션이 시작된다.', 163);

insert into schedule_movie(MOVIE_CODE, MV_TITLE, MV_STORY, MV_RUNTIME)
values(concat('MV_00000000000', seq_movie_code.nextval),
				'보이스(On the Line)', '단 한 통의 전화!걸려오는 순간 걸려들었다!' ,109);

insert into schedule_movie(MOVIE_CODE, MV_TITLE, MV_STORY, MV_RUNTIME)
values(concat('MV_00000000000', seq_movie_code.nextval),
				'수색자(The Recon)','억울하게 죽은 영혼들의 무덤 DMZ', 111);

insert into schedule_movie(MOVIE_CODE, MV_TITLE, MV_STORY, MV_RUNTIME)
values(concat('MV_00000000000', seq_movie_code.nextval),
				'기적(Mircle)', '오갈 수 있는 길은 기찻길밖에 없지만 정작 기차역은 없는 마을.' , 117);
---------------------------------------------------------------------------------------------

insert into DIRECTOR(DR_CODE, DR_NAME,MOVIE_CODE)
values(SEQ_DR_CODE.nextval, '캐리 후쿠나가', 'MV_000000000001');

insert into DIRECTOR(DR_CODE, DR_NAME,MOVIE_CODE)
values(SEQ_DR_CODE.nextval, '김선' , 'MV_000000000002');

insert into DIRECTOR(DR_CODE, DR_NAME,MOVIE_CODE)
values(SEQ_DR_CODE.nextval, '김곡' , 'MV_000000000002');

insert into DIRECTOR(DR_CODE, DR_NAME,MOVIE_CODE)
values(SEQ_DR_CODE.nextval,  '김민섭' , 'MV_000000000003');

insert into DIRECTOR(DR_CODE, DR_NAME,MOVIE_CODE)
values(SEQ_DR_CODE.nextval, '이창훈', 'MV_000000000004');
-------------------------------------------------------------------

insert into RESERVATION(RES_NUM, ID, MOVIE_CODE)
values (SEQ_RES_NUM.nextval, 'son', 'MV_000000000002');

insert into RESERVATION(RES_NUM, ID, MOVIE_CODE)
values (SEQ_RES_NUM.nextval, 'son', 'MV_000000000003');

insert into RESERVATION(RES_NUM, ID, MOVIE_CODE)
values (SEQ_RES_NUM.nextval, 'kim', 'MV_000000000001');

insert into RESERVATION(RES_NUM, ID, MOVIE_CODE)
values (SEQ_RES_NUM.nextval, 'jung', 'MV_000000000002');


---2-3 검색
select * from MEMBER ;
select * from reservation;
select * from DIRECTOR;

select  		sm.MOVIE_CODE, sm.MV_STORY, sm.MV_RUNTIME, d.DR_NAME
from				SCHEDULE_MOVIE  sm
inner join  DIRECTOR  d
on 					d.MOVIE_CODE = sm.MOVIE_CODE ;


select      m.NAME, m.GENDER, m.TEL, r.RES_NUM, r.REGDATE
from        member    m,  RESERVATION  r
where       r.ID = m.ID
order by		r.regdate desc;
