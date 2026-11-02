import java.util.*;

class Course {

    private String courseCode;
    private String courseName;
    private int capacity;

    // Unique enrolled students
    private HashSet<String> enrolledStudents;

    // Ordered list of prerequisites
    private ArrayList<String> prerequisites;

    // First-Come, First-Served waitlist
    private LinkedList<String> waitlist;

    public Course(String courseCode, String courseName, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.capacity = capacity;

        enrolledStudents = new HashSet<>();
        prerequisites = new ArrayList<>();
        waitlist = new LinkedList<>();
    }

    // Add prerequisite
    public void addPrerequisite(String courseCode) {
        prerequisites.add(courseCode);
    }

    // Register student
    public void registerStudent(String studentId) {

        // Check if already enrolled
        if (enrolledStudents.contains(studentId)) {
            System.out.println(studentId +
                    " is already enrolled in " + courseCode);
            return;
        }

        // Check if already on waitlist
        if (waitlist.contains(studentId)) {
            System.out.println(studentId +
                    " is already on the waitlist.");
            return;
        }

        // Check course capacity
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(studentId);

            System.out.println(studentId +
                    " successfully registered for " + courseCode);
        } else {
            waitlist.addLast(studentId);

            System.out.println("Course is full. " + studentId +
                    " added to waitlist.");
        }
    }

    // Drop student and promote from waitlist
    public void dropStudent(String studentId) {

        if (enrolledStudents.remove(studentId)) {

            System.out.println(studentId +
                    " dropped from " + courseCode);

            // Promote first student from waitlist
            if (!waitlist.isEmpty()) {

                String nextStudent = waitlist.removeFirst();
                enrolledStudents.add(nextStudent);

                System.out.println(nextStudent +
                        " promoted from waitlist to enrolled list.");
            }

        } else {
            System.out.println(studentId +
                    " is not enrolled in this course.");
        }
    }

    // Display course details
    public void displayCourseDetails() {

        System.out.println("\n--- Course Details ---");
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Name: " + courseName);
        System.out.println("Capacity: " + capacity);
        System.out.println("Enrolled Students: " + enrolledStudents);
        System.out.println("Prerequisites: " + prerequisites);
        System.out.println("Waitlist: " + waitlist);
    }
}


public class CourseRegistrationPortal {

    // HashMap for fast course lookup
    private HashMap<String, Course> courses;

    public CourseRegistrationPortal() {
        courses = new HashMap<>();
    }

    // Add a course
    public void addCourse(Course course, String courseCode) {
        courses.put(courseCode, course);
    }

    // Find course using course code
    public Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }

    public static void main(String[] args) {

        CourseRegistrationPortal portal =
                new CourseRegistrationPortal();

        // Create course
        Course javaCourse = new Course(
                "CS101",
                "Java Programming",
                2
        );

        // Add prerequisites
        javaCourse.addPrerequisite("CS100");
        javaCourse.addPrerequisite("MATH101");

        // Add course to HashMap
        portal.addCourse(javaCourse, "CS101");

        // Fast course lookup
        Course course = portal.getCourse("CS101");

        if (course != null) {

            // Registration requests
            course.registerStudent("S101");
            course.registerStudent("S102");
            course.registerStudent("S103");
            course.registerStudent("S104");

            // Display details
            course.displayCourseDetails();

            // Drop a student
            System.out.println("\n--- Dropping Student ---");
            course.dropStudent("S101");

            // Display updated details
            course.displayCourseDetails();
        }
    }
}