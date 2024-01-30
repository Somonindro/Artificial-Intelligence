import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws FileNotFoundException {

        Scanner scanner = new Scanner( new File( "input.txt" ));

        String line1= scanner.nextLine();
        String line2= scanner.nextLine();
        String[] row = line2.split(" ");

        int k = Integer.parseInt(line1); //grid size
        String[][] a = new String[k][k];
        int[][] check = new int[k][k];
//        System.out.println("Input the elements for initial state grid:");
//        System.out.println("(for blank tile use *)");
        int max_in=(k*k)-1;


        int p=0;
        for (int i=0;i<k;i++)
        {
            for(int j=0;j<k;j++)
            {
                a[i][j] = row[p++];
                if(!Objects.equals(a[i][j], "*") && (Integer.parseInt(a[i][j])< 1 || Integer.parseInt(a[i][j])>max_in))
                {
                    System.out.println("Input is any number between 1 and "+max_in+" or *");//check for invalid input
                    return;
                }
                if (Objects.equals(a[i][j], "*"))
                {
                    a[i][j]=" ";//converting * to blank space
                    check[i][j]=0;
                }
                else check[i][j]=Integer.parseInt(a[i][j]);
            }
        }


//        for (int i=0;i<k;i++)
//        {
//            for (int j=0;j<k;j++) System.out.print(check[i][j]+"  ");
//            System.out.println();
//        }

        Solvable solvable=new Solvable(check,k);
        if (!solvable.check_solvable())
        {
            System.out.println("It can't be solved for provided input");
            System.out.println("Number of inversion: "+ solvable.numberInversion);
            return;
        }
        System.out.println("Number of inversion: "+ solvable.numberInversion);

        Solution sol1=new Solution(new State(a,0,"manhattan"),k);
        int y=sol1.print();
        System.out.println("Optimal cost by manhattan distance: "+y);
        Solution sol2=new Solution(new State(a,0,"hamming"),k);
        y=sol2.print();
        System.out.println("Optimal cost by hamming distance: "+y);

    }
}