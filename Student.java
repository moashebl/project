import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Student implements Serializable {
    private final String studentId;
    private String studentName;
    private static final int ID_LENGTH = 9;
    List<Course> coursesList = new ArrayList<>();

    public Student(String studentId, String studentName) {
        if (studentId.length() != ID_LENGTH ) {
            throw new IllegalArgumentException("StudentId must be = " + ID_LENGTH );
        }
        this.studentId = studentId;
        this.studentName = studentName;
        this.coursesList = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void addCourse(Course course) {
        coursesList.add(course);
    }

    public void removeCourse(Course course) {
        coursesList.remove(course);
    }
}

