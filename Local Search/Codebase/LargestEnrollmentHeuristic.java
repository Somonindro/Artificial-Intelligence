import java.util.ArrayList;
import java.util.Collections;

public class LargestEnrollmentHeuristic implements ExamSchedulingInterface{
    ArrayList<Course> courseArrayList;

    public LargestEnrollmentHeuristic(ArrayList<Course> courseArrayList) {
        this.courseArrayList = courseArrayList;
    }

    public int schedulingByLargestEnrollment()
    {
        Collections.sort(courseArrayList, (o1, o2) -> Integer.compare(o2.getEnrollmentCount(), o1.getEnrollmentCount()));
        return ExamSchedulingInterface.scheduleExam(courseArrayList);
    }

    @Override
    public int applyHeuristics() {
        return schedulingByLargestEnrollment();
    }
}
