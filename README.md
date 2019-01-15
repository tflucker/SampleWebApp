# SampleWebApp - University Portal


## Description of Project
A web portal that allows students to add courses for their semester. Web Portal also has ADMIN view which is allowed to add new students, new courses, update student or course info, and also delete student / course info.  

## Description Project Infrastructure
This is a Spring Boot application with an embedded H2 database and several REST API endpoints to access in memory data.  Using Spring MVC, Spring Data, Hibernate a user is able to find data, create new data, update existing data, or delete data.  




## Future Functionality 

- Update Student findAll API to follow same response object return type as other Student APIs
- Create "Course" APIs (find all, find by id, create, update, delete)
- Create link between Student and Course objects 
  - update Student Object to contain list of courses (completed v. currently enrolled)
  - update student API response objects to return list of courses
  - When deleting students, delete links to any courses
  - When deleting courses, delete from each student list
- Create Login page to Univeristy Portal
  - Create STUDENT profile
  - Create ADMIN profile
- Create webapp to display Student info, Course info, and have a way to link them together
  - Create form to add students
  - Create form to add courses
  - Create form to update students
  - Create form to update courses
  - Create delete functionality for Students
  - Create delete functionality for Courses
  
  
### Questions for myself
1. Should COURSE be able to be deleted?
   1. Maybe give them status and only allow links for classes with a certain status
2. Should there be a way to mass import students / courses? through a file of JSON objects? CSV file?
3. Should I introduce a concept of a semester?
4. Should I introduce course prerequisites?
