insert into admin (id, login, name, password, patronymic, surname) values (1, 'gdate0', 'Сергій', 'sergiy', 'Іванович', 'Ткаченко');
insert into admin (id, login, name, password, patronymic, surname) values (2, 'rolifard1', 'Тетяна', 'tetyana', 'Василівна', 'Шикота');
insert into admin (id, login, name, password, patronymic, surname) values (3, 'ustihl2', 'Андрій', 'andriy', 'Юрійович', 'Юрченко');

insert into faculty (id, name) values (1, 'ТЕФ');
insert into faculty (id, name) values (2, 'ІХФ');

insert into speciality (id, name) values (1, 121);
insert into speciality (id, name) values (2, 122);
insert into speciality (id, name) values (3, 133);
insert into speciality (id, name) values (4, 151);

insert into `group` (id, name, faculty_id, speciality_id) values (1, 'ТІ-91', 1, 1);
insert into `group` (id, name, faculty_id, speciality_id) values (2, 'ТВ-91', 1, 1);
insert into `group` (id, name, faculty_id, speciality_id) values (3, 'ТР-81', 1, 2);
insert into `group` (id, name, faculty_id, speciality_id) values (4, 'ТМ-91', 1, 2);
insert into `group` (id, name, faculty_id, speciality_id) values (5, 'ЛП-01', 2, 3);
insert into `group` (id, name, faculty_id, speciality_id) values (6, 'ЛН-91', 2, 3);
insert into `group` (id, name, faculty_id, speciality_id) values (7, 'ЛА-91', 2, 4);
insert into `group` (id, name, faculty_id, speciality_id) values (8, 'ЛА-11', 2, 4);

insert into subject (id, name, teacher_id) values (1, 'Організація комп`ютерних мереж', 'екзамен', 4);
insert into subject (id, name, teacher_id) values (2, 'Комп`ютерне моделювання та оптимізація', 'залік', 2);
insert into subject (id, name, teacher_id) values (3, 'Методології розробки інтелектуальних комп`ютерних програм', 'залік', 5);
insert into subject (id, name, teacher_id) values (4, 'Іноземна мова професійного спрямування', 'залік', 1);

insert into teacher (id, login, name, password, patronymic, surname) values (1, 'fholwell0', 'Ірина', '8TZGouon', 'Алімівна', 'Свірепчук');
insert into teacher (id, login, name, password, patronymic, surname) values (2, 'awildman1', 'Олексій', 'KUzS7lMP', 'Миколайович', 'Шушура');
insert into teacher (id, login, name, password, patronymic, surname) values (3, 'sdagger2', 'Денис', 'mhkMHCfmEYt', 'Сергійович', 'Смаковський');
insert into teacher (id, login, name, password, patronymic, surname) values (4, 'cschrieves3', 'Ірина', 'hoZefErNyS', 'Ігорівна', 'Гусєва');
insert into teacher (id, login, name, password, patronymic, surname) values (5, 'tryburn3', 'Андрій', 'teacher', 'Петрович', 'Мусієнко');

insert into student (id, course, login, name, password, patronymic, surname, faculty_id, group_id, speciality_id) values (1, 3, 'lera', 'Валерія', '01', 'Ігорівна', 'Соболь', 1, 1, 1);
insert into student (id, course, login, name, password, patronymic, surname, faculty_id, group_id, speciality_id) values (2, 3, 'katya', 'Катерина', '02', 'Сергіївна', 'Яковлева', 1, 1, 1);
insert into student (id, course, login, name, password, patronymic, surname, faculty_id, group_id, speciality_id) values (3, 3, 'vika', 'Вікторія', '03', 'Ігорівна', 'Іващенко', 1, 1, 1);
insert into student (id, course, login, name, password, patronymic, surname, faculty_id, group_id, speciality_id) values (4, 3, 'egutierrez3', 'Олег', 'P11lSLe', 'Станіславович', 'Стельмащук', 1, 1, 1);
insert into student (id, course, login, name, password, patronymic, surname, faculty_id, group_id, speciality_id) values (5, 3, 'vrickarsey4', 'Володимир', 'gztzscEYUNaG', 'Володимирович', 'Скиба', 1, 1, 1);
insert into student (id, course, login, name, password, patronymic, surname, faculty_id, group_id, speciality_id) values (6, 3, 'edarlasson5', 'Юрій', 'IRPqwhrybj', 'Іванович', 'Сімоненко', 1, 2, 1);
insert into student (id, course, login, name, password, patronymic, surname, faculty_id, group_id, speciality_id) values (7, 3, 'gphifer6', 'Артем', '9j8A6F6isq', 'Юрійович', 'Хоменко', 1, 2, 1);

insert into mark (id, value, student_id, subject_id) values (1, 96, 3, 4);
insert into mark (id, value, student_id, subject_id) values (2, 89, 2, 2);
insert into mark (id, value, student_id, subject_id) values (3, 87, 2, 3);
insert into mark (id, value, student_id, subject_id) values (4, 56, 4, 2);
insert into mark (id, value, student_id, subject_id) values (5, 55, 5, 1);
insert into mark (id, value, student_id, subject_id) values (6, 88, 1, 2);
insert into mark (id, value, student_id, subject_id) values (7, 90, 5, 4);
insert into mark (id, value, student_id, subject_id) values (8, 94, 4, 1);
insert into mark (id, value, student_id, subject_id) values (9, 97, 6, 4);
insert into mark (id, value, student_id, subject_id) values (10, 87, 2, 4);
insert into mark (id, value, student_id, subject_id) values (11, 89, 1, 1);
insert into mark (id, value, student_id, subject_id) values (12, 94, 4, 3);
insert into mark (id, value, student_id, subject_id) values (13, 97, 7, 4);
insert into mark (id, value, student_id, subject_id) values (14, 95, 3, 2);
insert into mark (id, value, student_id, subject_id) values (15, 90, 7, 2);
insert into mark (id, value, student_id, subject_id) values (16, 98, 3, 1);
insert into mark (id, value, student_id, subject_id) values (17, 85, 5, 2);
insert into mark (id, value, student_id, subject_id) values (18, 86, 2, 1);
insert into mark (id, value, student_id, subject_id) values (19, 93, 5, 3);
insert into mark (id, value, student_id, subject_id) values (20, 88, 4, 4);