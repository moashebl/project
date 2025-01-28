<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Programming II Term Project Summary</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 20px;
        }
        h1, h2 {
            color: #333;
        }
        ul {
            list-style-type: disc;
            margin-left: 20px;
        }
        .section {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <h1>Programming II Term Project Summary</h1>
    <p>In this project, I implemented a student management system using Java. The system allows for the creation, search, update, and deletion of student records, as well as the management of courses associated with each student. Below is a summary of the steps I completed and the functionality I implemented:</p>

    <div class="section">
        <h2>1. Course Class Design</h2>
        <ul>
            <li>Designed a <code>Course</code> class with <code>id</code> and <code>name</code> as attributes.</li>
            <li>Implemented a constructor to initialize these fields.</li>
            <li>Used courses from the software engineering program as examples.</li>
        </ul>
    </div>

    <div class="section">
        <h2>2. Student Class Design</h2>
        <ul>
            <li>Designed a <code>Student</code> class with <code>id</code>, <code>fullName</code>, and a list of <code>Course</code> objects as attributes.</li>
            <li>Implemented a constructor to initialize these fields.</li>
            <li>Ensured that the student ID is unique for each student.</li>
        </ul>
    </div>

    <div class="section">
        <h2>3. Main Functionality</h2>
        <ul>
            <li>Implemented a main function that reads student data from a binary file (<code>students.bin</code>) and presents a menu to the user with the following options:
                <ul>
                    <li><strong>Create a student</strong>: Added a new student to the list, ensuring the ID is unique.</li>
                    <li><strong>Search for a student</strong>: Searched for students by ID or name and displayed their details.</li>
                    <li><strong>Delete a student</strong>: Removed a student from the list based on their ID.</li>
                    <li><strong>Update a student</strong>: Modified a student's details based on their ID.</li>
                    <li><strong>Print student names</strong>: Displayed the full names of all students in the list.</li>
                    <li><strong>Print student courses</strong>: Displayed the courses taken by a specific student based on their ID.</li>
                    <li><strong>Save and exit</strong>: Saved the list of students to a binary file and exited the application.</li>
                </ul>
            </li>
        </ul>
    </div>

    <div class="section">
        <h2>4. Binary File Handling</h2>
        <ul>
            <li>Implemented functionality to save the list of students to a binary file using <code>FileInputStream</code> and <code>FileOutputStream</code>.</li>
            <li>Ensured that the file format adheres to the specified structure, including fixed-length IDs and variable-length names with length indicators in Big Endian notation.</li>
        </ul>
    </div>

    <div class="section">
        <h2>5. Data Transfer Abstract Class</h2>
        <ul>
            <li>Created an abstract class <code>DataTransfer</code> with an abstract method <code>sendData</code> that takes a <code>String</code> as input.</li>
        </ul>
    </div>

    <div class="section">
        <h2>6. Email Data Transfer</h2>
        <ul>
            <li>Created a subclass <code>Email</code> that extends <code>DataTransfer</code> and overrides the <code>sendData</code> method.</li>
            <li>Implemented functionality to prompt the user for an email address and send the data to that address.</li>
        </ul>
    </div>

    <div class="section">
        <h2>7. File Data Transfer</h2>
        <ul>
            <li>Created a subclass <code>ToFile</code> that extends <code>DataTransfer</code> and overrides the <code>sendData</code> method.</li>
            <li>Implemented functionality to prompt the user for a filename and save the data in a text-based format.</li>
        </ul>
    </div>

    <div class="section">
        <h2>Deliverables</h2>
        <ul>
            <li>Provided the source code with output for all steps.</li>
            <li>Printed the source code with the cover page showing all completed steps circled.</li>
            <li>Ensured that the code is well-documented and adheres to the project requirements.</li>
        </ul>
    </div>

    <p>This project allowed me to practice object-oriented programming concepts, file handling, and polymorphism in Java. I successfully implemented all the required functionalities and ensured that the system is user-friendly and efficient.</p>
</body>
</html> 
