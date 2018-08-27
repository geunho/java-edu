insert into member(id, name, email, passwd, reg_date)
values( null, 'kim', 'urstory@gmail.com', '{bcrypt}$2a$10$0IKjNgE8fn.5oTSc4V0Cj.9NArYsSZYEZl7NVwV/cPP27dKDOGy76', now());

insert into member(id, name, email, passwd, reg_date)
values( null, 'kang', 'carami@gmail.com', '{bcrypt}$2a$10$0IKjNgE8fn.5oTSc4V0Cj.9NArYsSZYEZl7NVwV/cPP27dKDOGy76', now());

insert into member(id, name, email, passwd, reg_date)
values( null, 'KIM', 'abc@google.com', '{bcrypt}$2a$10$YxX0nwIAxumeXJe3eVgwsOlhsU1ItBnN1Bk1poCpqM0tSqaPMAbtO', now());

insert into member_role(id, name, member_id)
values( null, 'USER', 1);

insert into member_role(id, name, member_id)
values( null, 'ADMIN', 1);

insert into member_role(id, name, member_id)
values( null, 'USER', 2);

insert into board(id, title, content, member_id, reg_date)
values( null, '테스트 게시물', 'TESTTESTTESTTESTTESTTESTTESTTESTTESTTEST', 2, now());
insert into board(id, title, content, member_id, reg_date)
values( null, '테스트 게시물2', 'TESTTESTTESTTESTTESTTESTTESTTESTTESTTEST', 1, now());
insert into board(id, title, content, member_id, reg_date)
values( null, '테스트 게시물3', 'TESTTESTTESTTESTTESTTESTTESTTESTTESTTEST', 2, now());
insert into board(id, title, content, member_id, reg_date)
values( null, '테스트 게시물4', 'TESTTESTTESTTESTTESTTESTTESTTESTTESTTEST', 3, now());
insert into board(id, title, content, member_id, reg_date)
values( null, '테스트 게시물5', 'TESTTESTTESTTESTTESTTESTTESTTESTTESTTEST', 1, now());
insert into board(id, title, content, member_id, reg_date)
values( null, '테스트 게시물6', 'TESTTESTTESTTESTTESTTESTTESTTESTTESTTEST', 2, now());

