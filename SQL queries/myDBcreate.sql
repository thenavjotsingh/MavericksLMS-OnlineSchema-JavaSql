CREATE TABLE S22_S003_5_Trainee(
TraineeID     number(4)    GENERATED ALWAYS as IDENTITY(START with 001 INCREMENT by 1),
FullName      varchar(30)  not null,
State          varchar(10)  not null,
Email          varchar(40) not null,
Password      varchar(15) not null,
RegistrationDate  Date  not null,
Employee char check(Employee='Y' or Employee='N'),
Student char check( Student='N' or Student='Y'),
Unique (Email),
Primary key(TraineeID)
);



CREATE TABLE S22_S003_5_Certificate(
TraineeID     number(3)   not null,
CertificateId  varchar(10)  not null,
Image varchar(30) unique ,                                  
DateOfIssue Date,
Primary key(CertificateId,TraineeID),
Foreign key(TraineeID) References S22_S003_5_Trainee(TraineeID)
On delete cascade
);


CREATE TABLE S22_S003_5_Trainer(
TrainerID number(3) ,
Name  varchar(20) not null,
Password varchar(15) not null,
Email  varchar(40) not null,
PhoneNumber   number(10) not null,
AccountNumber  number(12) not null,
primary key(TrainerID),
unique(Email,PhoneNumber,AccountNumber)
);

CREATE TABLE S22_S003_5_Course(
CourseID  number(4)  GENERATED ALWAYS as IDENTITY(START with 1050 INCREMENT by 1),
CourseName  varchar(30) not null,
CourseFee  number(3)not null,
Primary key(CourseID)
);


CREATE TABLE S22_S003_5_Quiz(
QuestionNumber  number(5)  not null,
CourseID  number(4)not null,
Question varchar(100) not null,
Choice1  char(1)  not null check(Choice1='a'),
Choice2  char(1) not null check(Choice2='b'),
Choice3  char(1) not null check(Choice3='c'),
Choice4  char(1) not null check(Choice4='d'),
CorrectAnswer char(1)NOT NULL,
TrainerID number(3) not null,
primary key(QuestionNumber,CourseID,TrainerID),
Foreign key(CourseID) references S22_S003_5_Course(CourseID),
Foreign key (TrainerID) references S22_S003_5_Trainer(TrainerID)
On delete cascade
);

CREATE TABLE S22_S003_5_Salary(
PaymentId   number(6)  GENERATED ALWAYS as IDENTITY(START with 1501012 INCREMENT by 1),
PaymentMode  varchar(15),
Amount  number(4),
Primary Key(PaymentId)
);

CREATE TABLE S22_S003_5_TrainersSalary (
TrainerID number(3) not null,
PaymentId  number(6),
Primary key(PaymentId,TrainerID),
Foreign key(PaymentId) references S22_S003_5_Salary(PaymentId),
Foreign key(TrainerID) references S22_S003_5_Trainer(TrainerID)
On delete cascade
);

CREATE TABLE S22_S003_5_Section(
SectionID number(3)  not null,
CourseID  number(4) not null,
Primary key(SectionID,CourseID),
Foreign key(CourseID) references S22_S003_5_Course(CourseID)
On delete cascade
);



CREATE TABLE S22_S003_5_Feedback(
SNo number(4) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
FeedbackContent varchar(30),
primary key(SNo)
);

CREATE TABLE S22_S003_5_TrainersFeedback(
FeedbackNo number(4) not null,
TrainerID number(5) not null,
primary key(FeedbackNo,TrainerID),
Foreign key(TrainerID)references S22_S003_5_Trainer(TrainerID),
Foreign key(FeedbackNo)references S22_S003_5_Feedback(SNo)
On delete cascade
);


CREATE TABLE S22_S003_5_TraineesFeedback(
FeedbackNo number(4) not null,
TraineeID     number(3)   not null,
primary key(FeedbackNo,TraineeID),
Foreign key(TraineeID)references S22_S003_5_Trainee(TraineeID),
Foreign key(FeedbackNo)references S22_S003_5_Feedback(SNo)
On delete cascade
);



CREATE TABLE S22_S003_5_TrainerLanguage(
TrainerID number(3) not null,
Language  varchar(10) not null,
primary key(TrainerID,Language),
foreign key (TrainerID) references S22_S003_5_Trainer(TrainerID)
);

CREATE TABLE S22_S003_5_Teaches(

TrainerID number(3) not null,

CourseID number(4) not null,

SectionID number(3) not null,

CourseVideo varchar(35),

Primary Key (TrainerID,CourseID,SectionID),

Foreign key(TrainerID) references S22_S003_5_Trainer(TrainerID),

Foreign key(CourseID) references S22_S003_5_Course(CourseID),

Foreign key(SectionID) references S22_S003_5_Section(SectionID)

On delete cascade

);

CREATE TABLE S22_S003_5_Teaches(
TrainerID number(3) not null,
CourseID number(4) not null,
SectionID number(3) not null,
CourseVideo varchar(35),
Primary Key (TrainerID,CourseID,SectionID),
Foreign key(TrainerID) references S22_S003_5_Trainer(TrainerID),
Foreign key(CourseID) references S22_S003_5_Course(CourseID) On delete cascade);

CREATE TABLE S22_S003_5_Enroll(
TraineeID number(3) not null,
CourseID number(4) not null,
SectionID number(3) not null,
DateofEnrollment Date not null,
Primary Key(CourseID,TraineeID, SectionID),
Foreign key(CourseID) references S22_S003_5_Course(CourseID),
Foreign key(TraineeID) references S22_S003_5_Trainee(TraineeID)On delete cascade);



CREATE TABLE S22_S003_5_TraineesAnswer(
SNo number(4) not null,
TraineesAnswer char(1)  not null,
Result number(1) not null check(Result='0' or Result='1'),
TraineeID     number(3)   not null,
primary key(SNo,TraineeID),
Foreign key(TraineeID) references  S22_S003_5_Trainee(TraineeID)
On delete cascade
);

CREATE TABLE S22_S003_5_AssessedBy(
QuestionNumber  number(5)  not null,
AnswerNo  number(4) not null,
TraineeID  number(3)  not null,
CourseID  number(4) not null,
TrainerID number(3) not null,
Primary key(QuestionNumber,AnswerNo,TraineeID,CourseID,TrainerID),
Foreign key(AnswerNo) references S22_S003_5_TraineesAnswer(SNo),
Foreign key(TraineeID) references S22_S003_5_Trainee(TraineeID),
Foreign key(TrainerID) references S22_S003_5_Trainer(TrainerID),
Foreign key(CourseID) references S22_S003_5_Course(CourseID)
On delete cascade
);














