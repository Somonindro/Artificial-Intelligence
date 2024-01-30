import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class InputProcessing {
    static ArrayList<Student> studentArrayList = new ArrayList<Student>();
    static ArrayList<Course> courseArrayList = new ArrayList<Course>();
    static int[][] conflictMatrix;
    public static void extractCourseInput(String str)
    {
        String[] arrayStr = str.split(" ");
        int enrollmentCount = Integer.parseInt(arrayStr[1]);
        String courseId = arrayStr[0];

        Course course = new Course(enrollmentCount,courseId,-1);//initially coloring all nodes with -1
        courseArrayList.add(course);
    }
    public static int[] extractStudentInput(String str , int totalStudent)
    {
        String[] arrayStr = str.split(" ");
        int[] arrayInt = new int[arrayStr.length];
        for(int i=0; i<arrayInt.length; i++) {
            arrayInt[i] = Integer.parseInt(arrayStr[i]);
        }
        studentArrayList.add(new Student(0,0));
        for(int i=0; i<arrayInt.length; i++) {
            studentArrayList.get(totalStudent).addEnrolledCourse(courseArrayList.get(arrayInt[i]-1));
        }
        return arrayInt;
    }

    public static void produceOutput(File outputFile, String strategy, int heuristic) throws IOException {
        FileWriter writer = new FileWriter(outputFile);

        ExamSchedulingInterface scheduling = null;
        if (heuristic == 1)
        {
            scheduling = new LargestDegreeHeuristic(courseArrayList);
        }
        else if (heuristic == 2)
        {
            scheduling = new SaturationDegreeHeuristic(courseArrayList);
        }
        else if (heuristic == 3)
        {
            scheduling = new LargestEnrollmentHeuristic(courseArrayList);
        }
        else if (heuristic == 4)
        {
            scheduling = new RandomOrderingHeuristic(courseArrayList);
        }
        assert scheduling != null;
        System.out.println(scheduling.applyHeuristics());

        AvgPenaltyCalculation avgPenalty = new AvgPenaltyCalculation(studentArrayList,strategy);
        System.out.println("Before reducing : "+avgPenalty.getAvgPenalty());

        PenaltyReduction kempeChainReduction = new PenaltyReduction(courseArrayList,studentArrayList,"kempechain",strategy);
        kempeChainReduction.reducePenalty();
        AvgPenaltyCalculation KempeAvgPenalty = new AvgPenaltyCalculation(studentArrayList,strategy);
        System.out.println("After reducing (By kempe chain) : "+KempeAvgPenalty.getAvgPenalty());

        PenaltyReduction pairSwapReduction = new PenaltyReduction(courseArrayList,studentArrayList,"pairswap",strategy);
        pairSwapReduction.reducePenalty();
        AvgPenaltyCalculation PairSwapAvgPenalty = new AvgPenaltyCalculation(studentArrayList,strategy);
        System.out.println("After reducing (By pair swap operator) : "+PairSwapAvgPenalty.getAvgPenalty());

        Collections.sort(courseArrayList, Comparator.comparingInt(Course::getTimeSlot));
        for (Course course : courseArrayList) {
            //System.out.println(courseArrayList.get(i).getCourseNumber()+" "+courseArrayList.get(i).getTimeSlot());
            writer.append(course.getCourseNumber()).append(" ").append(String.valueOf(course.getTimeSlot())).append("\n");
        }
        writer.close();
    }

    public static void processInput(String fileName, String strategy, int heuristic) throws IOException {
        int totalStudent = 0 ;
        File courseFile = new File(fileName+".crs");
        File studentFile = new File(fileName+".stu");
        File outputFile = new File(fileName+".sol");
        outputFile.createNewFile();

        Scanner scnCourse = new Scanner(courseFile);
        Scanner scnStudent = new Scanner(studentFile);

        while (scnCourse.hasNext())
        {
            extractCourseInput(scnCourse.nextLine());
        }
        scnCourse.close();

        conflictMatrix = new int[courseArrayList.size()][courseArrayList.size()];
        for(int i=0; i<conflictMatrix.length; i++)
        {
            for(int j=0; j<conflictMatrix[0].length; j++)
            {
                conflictMatrix[i][j] = 0;
            }
        }

        while (scnStudent.hasNext())
        {
            int[] arrayInt = extractStudentInput(scnStudent.nextLine(),totalStudent);
            totalStudent++;
            for(int i=0; i<arrayInt.length-1; i++) {
                for(int j=i+1; j<arrayInt.length; j++) {
                    if(conflictMatrix[arrayInt[i]-1][arrayInt[j]-1] == 0) {
                        conflictMatrix[arrayInt[i]-1][arrayInt[j]-1] = conflictMatrix[arrayInt[j]-1][arrayInt[i]-1] = 1;
                    }
                }
            }
        }
        scnStudent.close();

        for(int i=0; i<conflictMatrix.length; i++) {
            for(int j=0; j<conflictMatrix[0].length; j++) {
                if(conflictMatrix[i][j] == 1) {
                    courseArrayList.get(i).addOverlappingCourseList(courseArrayList.get(j));
                }
            }
        }
        produceOutput(outputFile,strategy,heuristic);
    }
}
