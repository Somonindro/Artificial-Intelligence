import java.util.ArrayList;

public class PairSwapOperator {
    ArrayList<Student> studentArrayList;
    String strategy;

    public PairSwapOperator(ArrayList<Student> studentArrayList, String strategy) {
        this.studentArrayList = studentArrayList;
        this.strategy = strategy;
    }

    public void pairSwapAlgorithm(Course course1, Course course2)
    {
        if (isPairSwapPossible(course1,course2))
        {
            int course1TimeSlot = course1.getTimeSlot();
            int course2TimeSlot = course2.getTimeSlot();
            AvgPenaltyCalculation calculation = new AvgPenaltyCalculation(studentArrayList,strategy);
            double currentPenalty = calculation.getAvgPenalty();

            course1.setTimeSlot(course2TimeSlot);
            course2.setTimeSlot(course1TimeSlot);

            AvgPenaltyCalculation newCalculation = new AvgPenaltyCalculation(studentArrayList,strategy);
            double newPenalty = newCalculation.getAvgPenalty();

            if(currentPenalty <= newPenalty) //pair swap is not needed
            {
                course1.setTimeSlot(course1TimeSlot);
                course2.setTimeSlot(course2TimeSlot);
            }
        }
    }

    public boolean isPairSwapPossible(Course course1, Course course2)
    {
        ArrayList<Course> temp;
        Course[] overlappingCoursesArray;

        int course1TimeSlot = course1.getTimeSlot();
        int course2TimeSlot = course2.getTimeSlot();

        if(course1TimeSlot == course2TimeSlot)
        {
            return false;//pair swapping is not possible
        }

        temp = course1.getOverlappingList();
        overlappingCoursesArray = new Course[temp.size()];
        for (int i = 0; i < overlappingCoursesArray.length ; i++)
        {
            overlappingCoursesArray[i] = temp.get(i);
        }

        for(int i = 0; i < overlappingCoursesArray.length; i++)
        {
            if(overlappingCoursesArray[i].getTimeSlot() == course2TimeSlot)
            {
                return false;
            }
        }

        temp = course2.getOverlappingList();
        overlappingCoursesArray = new Course[temp.size()];
        for (int i = 0; i < overlappingCoursesArray.length ; i++)
        {
            overlappingCoursesArray[i] = temp.get(i);
        }

        for(int i = 0; i < overlappingCoursesArray.length; i++)
        {
            if(overlappingCoursesArray[i].getTimeSlot() == course1TimeSlot)
            {
                return false;
            }
        }
        return true;
    }

}
