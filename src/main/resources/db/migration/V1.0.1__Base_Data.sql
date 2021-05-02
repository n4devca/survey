insert into authority(code) values('ROLE_USER');
insert into authority(code) values('ROLE_ADMIN');

insert into question_type(code) values('yes_no');
insert into question_type(code) values('number_0_to_10');
insert into question_type(code) values('text');

insert into survey_user(username, passwd, name) values('admin', '{bcrypt}$2a$10$6Mrsx778tT5LSS0uMlVifO5aJAyI0sVVX8d9f4SMLQGgJr7IQHawy', 'Bob');

insert into user_authority(user_id, authority_id)
select distinct user.id, authority.id
from survey_user as user,
     authority as authority
where user.username = 'admin'
;