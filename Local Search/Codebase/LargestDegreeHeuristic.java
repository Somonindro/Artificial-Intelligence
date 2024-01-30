import java.util.ArrayList;
import java.util.Collections;

public class LargestDegreeHeuristic implements ExamSchedulingInterface{
    ArrayList<Course> courseArrayList;

    public LargestDegreeHeuristic(ArrayList<Course> courseArrayList) {
        this.courseArrayList = courseArrayList;
    }

    public int schedulingByLargestDegree()
    {
        Collections.sort(courseArrayList, (o1, o2) -> Integer.compare(o2.getOverlappingList().size(), o1.getOverlappingList().size()));
        return ExamSchedulingInterface.scheduleExam(courseArrayList);
    }

    @Override
    public int applyHeuristics() {
        return schedulingByLargestDegree();
    }
}
