import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static List<Student> students;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        students = new ArrayList<>();
        readStudent();
        showMenu();
    }
    public static void readStudent() {
        File file = new File("students.bin");
        if (!file.exists()) {
            System.out.println("Students data file does not exist yet.");
            return;
        }
    
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    // Read student ID
                    String studentId = dis.readUTF();
                    System.out.println("\nStudent ID: " + studentId);
    
                    // Read student name length
                    int nameLength = dis.readUnsignedShort();
                    System.out.println("Name Length: " + nameLength);
                    String studentName = readFixedLengthString(dis, nameLength);
                    System.out.println("Name: " + studentName);
    
                    // Read courses data length
                    int coursesLength = dis.readUnsignedShort();
                    String coursesData = readFixedLengthString(dis, coursesLength);
    
                    // Split courses data into individual courses
                    String[] coursesArray = coursesData.split(":");
                    List<Course> courses = new ArrayList<>();
                    for (int i = 0; i < coursesArray.length; i += 2) {
                        String courseId = coursesArray[i];
                        String courseName = coursesArray[i + 1];
                        courses.add(new Course(courseId, courseName));
                        System.out.println("Course ID: " + courseId + ", Course Name: " + courseName + ", Course Name Length: " + courseName.length());
                    }
    
                    // Create student object and add to list
                    Student student = new Student(studentId, studentName);
                    student.getCoursesList().addAll(courses);
                    students.add(student);
    
                } catch (EOFException eof) {
                    // Gracefully handle end of file
                    System.out.println("End of File reached");
                    break;
                } catch (IOException e) {
                    System.err.println("Error reading student data: " + e.getMessage());
                    break; // or continue if you want to try to read the next student
                }
            }
    
            System.out.println("Students data (with courses) have been loaded from students.bin");
        } catch (IOException e) {
            System.err.println("An error occurred while opening the file: " + e.getMessage());
        }
    }
    

    
    
    // Helper method to read fixed-length string
    private static String readFixedLengthString(DataInputStream dis, int length) throws IOException {
        byte[] bytes = new byte[length];
        dis.readFully(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }
    
    

    public static void saveStudent() {
    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("students.bin"))) {
        for (Student student : students) {
            // Write student ID
            dos.writeUTF(student.getStudentId());

            // Write student name (UTF-8 encoded with length)
            String studentName = student.getStudentName();
            byte[] nameBytes = studentName.getBytes(StandardCharsets.UTF_8);
            dos.writeShort(nameBytes.length);  
            dos.write(nameBytes);

            // Write courses data (UTF-8 encoded with length)
            String coursesData = student.getCoursesList().stream()
                .map(course -> course.getCourseId() + ":" + course.getCourseName())
                .collect(Collectors.joining(":"));

            byte[] coursesBytes = coursesData.getBytes(StandardCharsets.UTF_8);
            dos.writeShort(coursesBytes.length); 
            dos.write(coursesBytes);
        }
        System.out.println("Students data have been saved to students.bin");
    } catch (IOException e) {
        System.out.println("An error occurred while saving students data: " + e.getMessage());
    }
}


    private static void showMenu() {
        while (true) {
            System.out.println();
            System.out.println("Choose the option that you need ");
            System.out.println();
            System.out.println("1. Create a new Student ");
            System.out.println("2. Searching for a Student ");
            System.out.println("3. Delete Student   ");
            System.out.println("4. Update Student   ");
            System.out.println("5. The full names of the Students");
            System.out.println("6. Print Courses    ");
            System.out.println("7. Exit and Save    ");

            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        createStudent();
                        saveStudent();
                        break;
                    case 2:
                        System.out.println(" Enter the ID or Name of Student : ");
                        String query = scanner.nextLine();
                        searchStudent(query);
                        break;
                    case 3:
                        System.out.println(" Enter the ID of Student that you want to delete : ");
                        String deleteId = scanner.nextLine().trim();
                        deleteStudent(deleteId);
                        break;
                    case 4:
                        System.out.println(" Enter the id of Student that you want to update ");
                        String updateId = scanner.nextLine().trim();
                        updateStudent(updateId);
                        break;
                    case 5:
                        System.out.println("          The List of Students Names        ");
                        for (Student student2 : students) {
                            System.out.println();
                            System.out.println("    Name : " + student2.getStudentName());
                        }
                        break;
                    case 6:
                        System.out.println(" Enter Student ID ");
                        String studentId = scanner.nextLine().trim();
                        searchStudent(studentId);
                        printStudentCourse(studentId);
                        break;
                    case 7:
                        saveStudent();
                        printList();
                        System.out.println(" Exiting .. ");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option please try again..");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid option.");
            }
        }
    }

    public static void createStudent() {
        System.out.println("Enter the Student ID : ");
        String Id = scanner.nextLine();

        for (Student student1 : students) {
            if (student1.getStudentId().equals(Id)) {
                System.out.println("ID already exists. Please enter a unique ID.");
                return;
            }
        }

        System.out.println("Enter the Student name : ");
        String name = scanner.nextLine();

        Student student = new Student(Id, name);
        students.add(student);
        System.out.println("       Student added successfully      ");
        System.out.println();
        printList();
    }

    public static void searchStudent(String query) {
        List<Student> matchingStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(query) ||
                student.getStudentName().toLowerCase().contains(query.toLowerCase())) {
                matchingStudents.add(student);
            }
        }
        if (!matchingStudents.isEmpty()) {
            System.out.println("     Matching Students :    ");
            System.out.println();
            for (Student student : matchingStudents) {
                System.out.println("    ID :   " + student.getStudentId());
                System.out.println("    Name : " + student.getStudentName());
                System.out.println();
            }
        } else {
            System.out.println("  No Students Matching the query !! ");
        }
    }

    public static void deleteStudent(String deleteId) {
        Iterator<Student> iterator = students.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getStudentId().equalsIgnoreCase(deleteId)) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Student deleted successfully.");
            printList();
        } else {
            System.out.println("No student found with the provided ID.");
        }
    }

    public static void printList() {
        System.out.println("      List of the Students     ");
        for (Student student : students) {
            System.out.println();
            System.out.println("    ID :   " + student.getStudentId());
            System.out.println("    Name : " + student.getStudentName());
        }
    }

    public static void updateStudent(String id) {
        boolean found = false;
        Student studentToUpdate = null;
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(id)) {
                found = true;
                studentToUpdate = student;
                break;
            }
        }

        if (!found) {
            System.out.println(" No Student found ");
            return;
        }

        searchStudent(id);
        System.out.println("  Enter the option : ");
        System.out.println("1. Update student name ");
        System.out.println("2. Add course to student  ");
        System.out.println("3. Remove course  from student  ");
        System.out.println("4. Exit and Save ");
        int option;
        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" Error, Please Enter a valid number ");
            return;
        }

        switch (option) {
            case 1:
                System.out.println("Enter the new name of the Student ");
                String newName = scanner.nextLine().trim();
                studentToUpdate.setStudentName(newName);
                System.out.println(" Student Name Successfully Added ");
                break;
            case 2:
                System.out.println(" Enter the course ID you want to add ");
                String courseId = scanner.nextLine().trim();
                System.out.println(" Enter the course name ");
                String courseName = scanner.nextLine().trim();
                Course courseToAdd = new Course(courseId, courseName);
                studentToUpdate.addCourse(courseToAdd);
                saveStudent();
                System.out.println(" Course added Successfully ");
                break;
            case 3:
                System.out.println(" Enter the course ID that you want to remove ");
                String courseRemoveId = scanner.nextLine().trim();
                Course courseToRemove = null;
                for (Course course : studentToUpdate.getCoursesList()) {
                    if (course.getCourseId().equals(courseRemoveId)) {
                        courseToRemove = course;
                        break;
                    }
                }
                if (courseToRemove != null) {
                    studentToUpdate.getCoursesList().remove(courseToRemove);
                    System.out.println(" Course Removed Successfully ");
                } else {
                    System.out.println(" No Course found ");
                }
                break;
            case 4:
                saveStudent();
                break;
            default:
                System.out.println(" Invalid option, Please try again.. ");
        }
    }

    public static void printStudentCourse(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                List<Course> courses = student.getCoursesList();
                if (courses.isEmpty()) {
                    System.out.println(" No Courses added ");
                } else {
                    System.out.println(" List of Courses : ");
                    for (Course course : courses) {
                        System.out.println(" Course ID : " + course.getCourseId());
                        System.out.println(" Course Name : " + course.getCourseName());
                    }
                }
                return;
            }
        }
        System.out.println(" No student found with the provided ID ");
    }

  


}
