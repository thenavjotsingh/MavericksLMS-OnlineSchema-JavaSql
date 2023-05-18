/* Order by and Fetch */
SELECT TRAINEEID FROM S22_S003_5_Trainee WHERE (to_char(RegistrationDate,'Month'))=(
select to_char(RegistrationDate,'Month') from S22_S003_5_Trainee GROUP BY to_char(RegistrationDate,'Month') HAVING COUNT(to_char(RegistrationDate,'Month'))>=ALL(
select COUNT(to_char(RegistrationDate,'Month')) from S22_S003_5_Trainee GROUP BY to_char(RegistrationDate,'Month'))) ORDER BY TRAINEEID FETCH FIRST 15 ROWS ONLY ;

/* Order by and Fetch*/
select FullName from S22_S003_5_Trainee t join S22_S003_5_Enroll e
on t.TraineeID=e.TraineeID
where State='California'
order by t.TraineeID Fetch first 10 Rows only


/* using group by having*/
SELECT Name as "Most Popular Trainer"
from S22_S003_5_Trainer where TrainerID in
(SELECT TrainerID
FROM S22_S003_5_Enroll e INNER JOIN S22_S003_5_Teaches t on e.CourseID=t.CourseID and e.SectionID=t.SectionID
group by TrainerID having count(TrainerID)>=ALL
(SELECT count(TRainerID)
FROM S22_S003_5_Enroll e INNER JOIN S22_S003_5_Teaches t on e.CourseID=t.CourseID and e.SectionID=t.SectionID
group by TrainerID));


/* using group by having*/
SELECT CourseName as "Most popular course(s)" from S22_S003_5_Course
where CourseID in(
SELECT e.CourseID 
FROM S22_S003_5_Enroll e INNER JOIN S22_S003_5_Course c on c.CourseID=e.CourseID 
group by e.CourseID having count(e.CourseID)>=ALL
(SELECT count(e.CourseID)
FROM S22_S003_5_Enroll e INNER JOIN S22_S003_5_Course c on c.CourseID=e.CourseID 
group by e.CourseID));



/* using group by having*/
SELECT TrainerID, MIN(Amount) AS "Lowest Amount"
FROM S22_S003_5_Salary s,S22_S003_5_TrainersSalary t
where s.PaymentID=t.PaymentID
GROUP BY TrainerID
HAVING MIN(Amount) < 500;

/*Nested subquery*/
SELECT
CourseFee
FROM
S22_S003_5_Enroll e join S22_S003_5_Course c
on e.courseID=c.CourseID
where (CourseFee)>
 (SELECT
avg(amount)
FROM
S22_S003_5_salary s join S22_S003_5_TrainersSalary t
on s.paymentID=t.PaymentId );




/* sub select in select*/
select TraineeID,FullName,
 (select count(TraineeID) from S22_S003_5_Enroll where S22_S003_5_Enroll.TraineeID= S22_S003_5_Trainee.TraineeID) from S22_S003_5_Trainee ;

/*roll up*/
SELECT PAYMENTMODE,
SUM(AMOUNT) AS AMOUNT
FROM S22_S003_5_SALARY
GROUP BY ROLLUP (PAYMENTMODE);

/*roll up*/
select TraineeID,sum(CourseFee) as "Total Enrollment fee"
from S22_S003_5_Enroll e inner join S22_S003_5_Course c on c.courseId=e.courseID 
GROUP BY ROLLUP (TraineeID);

/*over*/
select TrainerID,sum(Amount) Over(Partition by TrainerID) as "Total Salary received" from S22_S003_5_TrainersSalary t inner join S22_S003_5_Salary s
on t.PaymentID=s.PaymentID;


 














