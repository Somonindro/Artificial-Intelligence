import java.util.ArrayList;
import java.util.Random;

public class PenaltyReduction {
    String procedure; //kempe-chain or pair-swap
    String strategy; //linear or exponential
    ArrayList<Course> courseArrayList;
    ArrayList<Student> studentArrayList;

    public PenaltyReduction(ArrayList<Course> courseArrayList, ArrayList<Student> studentArrayList, String procedure, String strategy) {
        this.procedure = procedure;
        this.courseArrayList = courseArrayList;
        this.studentArrayList = studentArrayList;
        this.strategy = strategy;
    }

    public void reducePenalty()
    {
        Random random = new Random();

        for(int i = 0; i < 3000; i++)
        {
            if(procedure.equals("kempechain"))
            {
                int current = random.nextInt(courseArrayList.size());
                ArrayList<Course> temp = courseArrayList.get(current).getOverlappingList();
                Course[] overlappingCoursesArray = new Course[temp.size()];
                for (int j = 0; j < overlappingCoursesArray.length ; j++)
                {
                    overlappingCoursesArray[j] = temp.get(j);
                }

                if(overlappingCoursesArray.length != 0)
                {
                    //System.out.println("kempechain");
                    int neighbourTimeSlot = overlappingCoursesArray[random.nextInt(overlappingCoursesArray.length)].getTimeSlot();
                    KempeChainInterchange kempeChain = new KempeChainInterchange(courseArrayList,studentArrayList,strategy);
                    kempeChain.kempeChainInterchangeAlgorithm(courseArrayList.get(current),neighbourTimeSlot);

                }
            }
            else if(procedure.equals("pairswap"))
            {
                //System.out.println("pairswap");
                Course c1 = courseArrayList.get(random.nextInt(courseArrayList.size()));
                Course c2 = courseArrayList.get(random.nextInt(courseArrayList.size()));
                PairSwapOperator pairSwap = new PairSwapOperator(studentArrayList,strategy);
                pairSwap.pairSwapAlgorithm(c1,c2);
            }
        }
    }



}
