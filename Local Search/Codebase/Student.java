import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Student {
    private ArrayList<Course> enrolledList;
    private double totalPenalty;
    private int totalGap;

    public Student(double totalPenalty ,int totalGap)
    {
        this.totalPenalty = totalPenalty;
        this.enrolledList = new ArrayList<Course>();
        this.totalGap = totalGap;
    }

    public ArrayList<Course> getEnrolledList() {
        return enrolledList;
    }

    public void setEnrolledList(ArrayList<Course> enrolledList) {
        this.enrolledList = enrolledList;
    }

    public double getTotalPenalty(String strategy) {
        this.totalPenalty = 0;
        Collections.sort(enrolledList, Comparator.comparingInt(Course::getTimeSlot));
        for (int i = 0 ; i < enrolledList.size()-1 ; i++)
        {
            for (int j = i+1 ; j < enrolledList.size() ; j++)
            {
                this.totalGap = this.enrolledList.get(j).getTimeSlot() - this.enrolledList.get(i).getTimeSlot();
                if (this.totalGap <= 5)
                {
                    //Exponential Strategy
                    if (strategy.equals("exponential"))
                    {
                        this.totalPenalty += Math.pow(2,5-this.totalGap);
                    }
                    //Linear Strategy
                    else if (strategy.equals("linear"))
                    {
                        this.totalPenalty += (2*(5-this.totalGap));
                    }

                }
            }
        }
        return totalPenalty;

    }

    public void setTotalPenalty(double totalPenalty) {
        this.totalPenalty = totalPenalty;
    }

    public int getTotalGap() {
        return totalGap;
    }

    public void setTotalGap(int totalGap) {
        this.totalGap = totalGap;
    }

    public void addEnrolledCourse(Course c) {
        enrolledList.add(c);
    }

}
