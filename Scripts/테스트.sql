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




select emp_no, emp_name, title, manager, salary, dept, hire_date pic from employee where emp_no=1003;

-- 조민희가 로그인하려고 하는 경우
select emp_no, emp_name, title, manager, salary, dept,hire_date
from employee 
where emp_no = 1003 and passwd = password('1234567');


select emp_no, emp_name, title, manager, salary, dept, pic, passwd, hire_date
from employee;


insert into employee(emp_no, emp_name, title, manager, salary, dept, passwd, hire_date) values (1004, '이유영', 2, 1003, 2000000, 2, 'rootroot','2000-03-01');