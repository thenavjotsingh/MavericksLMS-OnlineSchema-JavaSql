/*Newly added Trainers into Maverick LMS*/

INSERT INTO S22_S003_5_Trainer (TrainerID,Name, Password, Email, PhoneNumber, AccountNumber) VALUES (151,'Leonard Rafael','Rafet^2434','Rafael098@gmail.com',3681690567,900765546853 );
INSERT INTO S22_S003_5_Trainer (TrainerID,Name, Password, Email, PhoneNumber, AccountNumber) VALUES (152,'Luis Brett','Brett@12345','brett123456@iCloud.com',4001690567,901765546853 );
INSERT INTO S22_S003_5_Trainer (TrainerID,Name, Password, Email, PhoneNumber, AccountNumber) VALUES (153,'Dale Edgar','dale@3456','dale123@gmail.com',4011690567,902765546853);

/* Few Trainers have changed their Phone numbers*/
UPDATE S22_S003_5_Trainer SET PHONENUMBER=8171690158 where TrainerID=100;
UPDATE S22_S003_5_Trainer SET PHONENUMBER=9004965872 where TrainerID=101;
UPDATE S22_S003_5_Trainer SET PHONENUMBER=8170195766 where TrainerID=102;
UPDATE S22_S003_5_Trainer SET PHONENUMBER=6000905099 where TrainerID=103;

/*Adding newly registered Trainees into Maverick LMS*/
INSERT INTO S22_S003_5_Trainee VALUES(FullName,State,Email,Password,RegistrationDate,Employee,Student)
VALUES ('Alexander Mason','California','Mason123@iCloud.com','djie$2986',TO_DATE('21-09-18','YY-MM-DD'),'Y','N');
INSERT INTO S22_S003_5_Trainee VALUES(FullName,State,Email,Password,RegistrationDate,Employee,Student)
VALUES ('Mateo Jack','Florida','jack123@iCloud.com','jdhiy$2376',TO_DATE('21-011-18','YY-MM-DD'),'N','Y');
INSERT INTO S22_S003_5_Trainee VALUES(FullName,State,Email,Password,RegistrationDate,Employee,Student)
VALUES ('Aiden Samuel','Georgia','aiden123@iCloud.com','uruuy$2376',TO_DATE('21-010-16','YY-MM-DD'),'Y','N');
INSERT INTO S22_S003_5_Trainee VALUES(FullName,State,Email,Password,RegistrationDate,Employee,Student)
VALUES ('John David','Texas','david123@iCloud.com','uhiwh$2376',TO_DATE('21-09-11','YY-MM-DD'),'N','Y');
INSERT INTO S22_S003_5_Trainee VALUES(FullName,State,Email,Password,RegistrationDate,Employee,Student)
VALUES ('Luke Asher','Arkansas','luke123@iCloud.com','uhiwh$2376',TO_DATE('21-10-28','YY-MM-DD'),'Y','N');


