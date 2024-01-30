import java.util.ArrayList;
import java.util.Collections;

public class RandomOrderingHeuristic implements ExamSchedulingInterface{
    ArrayList<Course> courseArrayList;

    public RandomOrderingHeuristic(ArrayList<Course> courseArrayList) {
        this.courseArrayList = courseArrayList;
    }

    public int schedulingByRandomOrdering()
    {
        Collections.shuffle(courseArrayList);
        return ExamSchedulingInterface.scheduleExam(courseArrayList);
    }

    @Override
    public int applyHeuristics() {
        return schedulingByRandomOrdering();
    }
}
