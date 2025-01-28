public class Course {
    private final String courseId; // final used to be fixed..
    private String courseName;
    private static final int Courseidlength = 6;

    public Course(String courseId, String courseName){
        if(courseId.length() != Courseidlength){
            throw new IllegalArgumentException("CourseId length must be = " + Courseidlength);
        }
        this.courseId = courseId;
        this.courseName = courseName;
    }

    // Getters
    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }
}