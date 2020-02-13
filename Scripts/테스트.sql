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


select e.emp_no, e.emp_name , e.title , t.title_name, m.emp_name as manager_name , m.emp_no as manager_no , e.salary , e.dept , d.dept_name, e.hire_date 
from employee e left join employee m on e.manager = m.emp_no join department d on e.dept = d.dept_no join title t on e.title = t.title_no;

select emp_no, emp_name, title, manager, salary, dept, hire_date from employee;
select e.emp_no,e.emp_name, e.title, t.title_name, m.emp_name as manager_name, m.emp_no as manager_no, e.salary, e.dept

select emp_no, emp_name, t.title_name from employee e left join title t on e.title = t.title_no where dept =1;

select e.emp_no, e.emp_name, t.title_name, t.title_no, m.emp_no as manager_no, m.emp_name as manager_name, e.salary, d.dept_no ,d.dept_name, e.hire_date, e.pic 
from employee e left join title t on e.title = t.title_no 
	left join employee m on e.manager =m.emp_no 
	left join department d on e.dept = d.dept_no 
	
select *
from employee;
	
	
	
