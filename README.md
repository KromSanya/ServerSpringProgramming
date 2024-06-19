Таблицы:
mygroup: id, name

student: id, firstName, surname, patronymic, status, group_id

teacher: id, firstName, surname, secondName

subject: id, name

lesson: id, date, time, teacherId, subjectId, groupId

attendance: id, studentId, lessonId


код для их создания:

CREATE TABLE MyGroup (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) NOT NULL
);

CREATE TABLE student (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `firstName` VARCHAR(45) NOT NULL,
    `surname` VARCHAR(45) NOT NULL,
    `secondName` VARCHAR(45) NOT NULL,
    `status` VARCHAR(45) NOT NULL,
    `groupId` INT NOT NULL,
    FOREIGN KEY (`groupId`) REFERENCES `MyGroup`(`id`)
);

CREATE TABLE Subject (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE Teacher (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(32) NOT NULL,
    surname VARCHAR(32) NOT NULL,
    secondName VARCHAR(32) NOT NULL
);

CREATE TABLE Lesson (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    time TIME NOT NULL,
    teacherId INT NOT NULL,
    subjectId INT NOT NULL,
    groupId INT NOT NULL,
    CONSTRAINT fk_lesson_teacher FOREIGN KEY (teacherId) REFERENCES Teacher(id),
    CONSTRAINT fk_lesson_subject FOREIGN KEY (subjectId) REFERENCES Subject(id),
    CONSTRAINT fk_lesson_group FOREIGN KEY (groupId) REFERENCES MyGroup(id)
);

CREATE TABLE Attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    studentId INT NOT NULL,
    lessonId INT NOT NULL,
    CONSTRAINT fk_attendance_student FOREIGN KEY (studentId) REFERENCES student(id),
    CONSTRAINT fk_attendance_lesson FOREIGN KEY (lessonId) REFERENCES Lesson(id)
);
