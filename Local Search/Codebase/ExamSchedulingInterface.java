import java.util.ArrayList;
import java.util.Arrays;

public interface ExamSchedulingInterface {
    int applyHeuristics();

    static int scheduleExam(ArrayList<Course> courseArrayList) {
        //graph coloring algorithm ( Courses are nodes and time slots are colors)
        int suitableTimeSlot = 0;
        ArrayList<Course> temp;
        Course[] overlappingCoursesArray;
        int timeSlotCount = 0;

        for(int i = 0; i < courseArrayList.size(); i++)
        {
            suitableTimeSlot = 0;
            temp = courseArrayList.get(i).getOverlappingList();
            overlappingCoursesArray = new Course[temp.size()];
            for (int j = 0; j < overlappingCoursesArray.length ; j++)
            {
                overlappingCoursesArray[j] = temp.get(j);
            }
            int[] slotOccupied = new int[overlappingCoursesArray.length];
            for(int j = 0; j < slotOccupied.length; j++)
            {
                slotOccupied[j] = overlappingCoursesArray[j].getTimeSlot();
            }
            Arrays.sort(slotOccupied);

            for(int j = 0; j < slotOccupied.length; j++)
            {
                if(slotOccupied[j] != -1)
                {
                    if(suitableTimeSlot == slotOccupied[j])
                    {
                        suitableTimeSlot++;
                    }
                    if(suitableTimeSlot < slotOccupied[j])
                    {
                        courseArrayList.get(i).setTimeSlot(suitableTimeSlot);
                    }
                }
            }
            if(courseArrayList.get(i).getTimeSlot() == -1)
            {
                if(suitableTimeSlot == timeSlotCount)
                {
                    courseArrayList.get(i).setTimeSlot(timeSlotCount++);
                }
                else {
                    courseArrayList.get(i).setTimeSlot(suitableTimeSlot);
                }
            }
        }
        return timeSlotCount;
    }
}
