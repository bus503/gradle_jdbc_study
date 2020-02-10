select user(),database();

select *
from department;
select *
from employee;
select *
from title;

select title_no, title_name from title;
select dept_no, dept_name, floor from department;
select dept_no, dept_name, floor from department where dept_no=3;
 

select title_no, title_name from title where title_no=3;

insert into title values(6,'췌장');

delete from title where title_no =6;

update title set title_name = '사원' where title_no= 6;

select *
from title;

select *
from employee;
select*
from department;