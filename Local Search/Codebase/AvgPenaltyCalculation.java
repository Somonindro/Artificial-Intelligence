import java.util.ArrayList;

public class AvgPenaltyCalculation {
    ArrayList<Student> studentArrayList;
    double avgPenalty;
    String strategy;

    public AvgPenaltyCalculation(ArrayList<Student> studentArrayList , String strategy) {
        this.studentArrayList = studentArrayList;
        this.avgPenalty = 0;
        this.strategy = strategy;
    }

    public double getAvgPenalty() {
        this.avgPenalty = 0;
        for(int i = 0; i < studentArrayList.size(); i++)
        {
            this.avgPenalty += studentArrayList.get(i).getTotalPenalty(this.strategy);
        }
        this.avgPenalty /= studentArrayList.size();
        return this.avgPenalty;
    }

    public void setAvgPenalty(double avgPenalty) {
        this.avgPenalty = avgPenalty;
    }
}
