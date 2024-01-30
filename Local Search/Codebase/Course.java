import java.util.ArrayList;

public class Course {

    private ArrayList<Course> overlappingList;
    private String situation;
    private int conflict;
    private int enrollmentCount;
    private boolean isConflict;
    private String courseNumber;
    private int timeSlot;

    public Course(int enrollmentCount, String courseNumber, int timeSlot) {
        overlappingList = new ArrayList<Course>();
        this.enrollmentCount = enrollmentCount;
        this.courseNumber = courseNumber;
        this.timeSlot = timeSlot;
    }

    public ArrayList<Course> getOverlappingList() {
        return overlappingList;
    }

    public void setOverlappingList(ArrayList<Course> overlappingList) {
        this.overlappingList = overlappingList;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public int getConflict() {
        return conflict;
    }

    public void setConflict(int conflict) {
        this.conflict = conflict;
    }

    public int getEnrollmentCount() {
        return enrollmentCount;
    }

    public void setEnrollmentCount(int enrollmentCount) {
        this.enrollmentCount = enrollmentCount;
    }

    public boolean isConflict() {
        return isConflict;
    }

    public void setConflict(boolean conflict) {
        isConflict = conflict;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void addOverlappingCourseList(Course c)
    {
        overlappingList.add(c);
    }


}
