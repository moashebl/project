Summary of the Programming II Term Project
In this project, I implemented a student management system using Java. The system allows for the creation, search, update, and deletion of student records, as well as the management of courses associated with each student. Below is a summary of the steps I completed and the functionality I implemented:

Course Class Design:

Designed a Course class with id and name as attributes.

Implemented a constructor to initialize these fields.

Used courses from the software engineering program as examples.

Student Class Design:

Designed a Student class with id, fullName, and a list of Course objects as attributes.

Implemented a constructor to initialize these fields.

Ensured that the student ID is unique for each student.

Main Functionality:

Implemented a main function that reads student data from a binary file (students.bin) and presents a menu to the user with the following options:

Create a student: Added a new student to the list, ensuring the ID is unique.

Search for a student: Searched for students by ID or name and displayed their details.

Delete a student: Removed a student from the list based on their ID.

Update a student: Modified a student's details based on their ID.

Print student names: Displayed the full names of all students in the list.

Print student courses: Displayed the courses taken by a specific student based on their ID.

Save and exit: Saved the list of students to a binary file and exited the application.

Binary File Handling:

Implemented functionality to save the list of students to a binary file using FileInputStream and FileOutputStream.

Ensured that the file format adheres to the specified structure, including fixed-length IDs and variable-length names with length indicators in Big Endian notation.

Data Transfer Abstract Class:

Created an abstract class DataTransfer with an abstract method sendData that takes a String as input.

Email Data Transfer:

Created a subclass Email that extends DataTransfer and overrides the sendData method.

Implemented functionality to prompt the user for an email address and send the data to that address.

File Data Transfer:

Created a subclass ToFile that extends DataTransfer and overrides the sendData method.

Implemented functionality to prompt the user for a filename and save the data in a text-based format.
