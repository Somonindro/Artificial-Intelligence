import java.util.ArrayList;
import java.util.Objects;

public class KempeChainInterchange {
    ArrayList<Course> courseArrayList;
    ArrayList<Student> studentArrayList;
    String strategy;

    public KempeChainInterchange(ArrayList<Course> courseArrayList, ArrayList<Student> studentArrayList , String strategy) {

        this.courseArrayList = courseArrayList;
        this.studentArrayList = studentArrayList;
        this.strategy = strategy;
    }

    public void DFS(Course currentCourse , int neighbourTimeSlot)
    {
        currentCourse.setSituation("instack");
        ArrayList<Course> temp = currentCourse.getOverlappingList();
        Course[] overlappingCoursesArray = new Course[temp.size()];
        for (int i = 0; i < overlappingCoursesArray.length ; i++)
        {
            overlappingCoursesArray[i] = temp.get(i);
        }
        for(int i = 0; i < overlappingCoursesArray.length; i++)
        {
            if(Objects.equals(overlappingCoursesArray[i].getSituation(), "unexplored") && overlappingCoursesArray[i].getTimeSlot()==neighbourTimeSlot)
            {
                DFS(overlappingCoursesArray[i], currentCourse.getTimeSlot());
            }
        }
        currentCourse.setSituation("visited");
    }

    public void kempeChainInterchangeAlgorithm(Course currentCourse , int neighbourTimeSlot)
    {
        DFS(currentCourse, neighbourTimeSlot);
        int currentTimeSlot = currentCourse.getTimeSlot();
        AvgPenaltyCalculation calculation = new AvgPenaltyCalculation(studentArrayList,strategy);
        double currentPenalty = calculation.getAvgPenalty();

        for(int i = 0; i < courseArrayList.size(); i++)
        {
            if(Objects.equals(courseArrayList.get(i).getSituation(), "visited"))
            {
                if(courseArrayList.get(i).getTimeSlot() == currentTimeSlot)
                {
                    courseArrayList.get(i).setTimeSlot(neighbourTimeSlot);
                }
                else {
                    courseArrayList.get(i).setTimeSlot(currentTimeSlot);
                }
            }
        }

        AvgPenaltyCalculation newCalculation = new AvgPenaltyCalculation(studentArrayList,strategy);
        double newPenalty = newCalculation.getAvgPenalty();
        if(currentPenalty <= newPenalty) {
            //undoing changes
            for(int i = 0; i < courseArrayList.size(); i++)
            {
                if(Objects.equals(courseArrayList.get(i).getSituation(), "visited"))
                {
                    if(courseArrayList.get(i).getTimeSlot() == currentTimeSlot)
                    {
                        courseArrayList.get(i).setTimeSlot(neighbourTimeSlot);
                    }
                    else {
                        courseArrayList.get(i).setTimeSlot(currentTimeSlot);
                    }
                }
            }
        }

        for(int i = 0; i < courseArrayList.size(); i++)
        {
            if(Objects.equals(courseArrayList.get(i).getSituation(), "visited"))
            {
                courseArrayList.get(i).setSituation("unexplored");
            }
        }

    }
}
