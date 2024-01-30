import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SaturationDegreeHeuristic implements ExamSchedulingInterface{
    ArrayList<Course> courseArrayList;

    public SaturationDegreeHeuristic(ArrayList<Course> courseArrayList) {
        this.courseArrayList = courseArrayList;
    }

    public int schedulingBySaturationDegree()
    {
        //DSatur Algorithm
        Collections.sort(courseArrayList, (o1, o2) -> Integer.compare(o2.getOverlappingList().size(), o1.getOverlappingList().size()));
        courseArrayList.get(0).setTimeSlot(0);
        int totalTimeSlot = 1;
        for(int i = 1; i<courseArrayList.size(); i++) {
            ArrayList<Integer> temp;
            ArrayList<Integer> selected = null;
            int maxSaturationDegree=-1;
            int maxIndex=-1;

            for(int j=0; j<courseArrayList.size(); j++) {
                if(courseArrayList.get(j).getTimeSlot() == -1) {
                    temp = new ArrayList<>();

                    ArrayList<Course> tempCourse = courseArrayList.get(j).getOverlappingList();
                    Course[] overlappingCoursesArray = new Course[tempCourse.size()];
                    for (int k = 0; k < overlappingCoursesArray.length ; k++)
                    {
                        overlappingCoursesArray[k] = tempCourse.get(k);
                    }

                    for (Course overlappingCourses : overlappingCoursesArray) {
                        if (overlappingCourses.getTimeSlot() != -1) {
                            temp.add(overlappingCourses.getTimeSlot());
                        }
                    }

                    if(temp.size()>maxSaturationDegree || (temp.size()==maxSaturationDegree && courseArrayList.get(j).getOverlappingList().size()>courseArrayList.get(maxIndex).getOverlappingList().size())) {
                        maxSaturationDegree = temp.size();
                        maxIndex = j;
                        selected = temp;
                    }
                }
            }

            maxSaturationDegree = 0;
            while(courseArrayList.get(maxIndex).getTimeSlot() == -1) {
                if(!selected.contains(maxSaturationDegree)) {
                    courseArrayList.get(maxIndex).setTimeSlot(maxSaturationDegree);
                    if(maxSaturationDegree == totalTimeSlot) {
                        totalTimeSlot++;
                    }
                } else {
                    maxSaturationDegree++;
                }
            }
        }
        return totalTimeSlot;
    }

    @Override
    public int applyHeuristics() {
        return schedulingBySaturationDegree();
    }
}
