import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //read from input.txt file


        File file = new File("d-10-01.txt");
        Scanner scanner = new Scanner(file);
        int n = Integer.parseInt(scanner.nextLine());
        Variable[][] matrix = new Variable[n][n];
        for (int i=0;i<n;i++)
        {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
//            int m = Integer.parseInt(parts[0]);
//            int k = Integer.parseInt(parts[1]);

            for (int j=0;j<n;j++)
            {
                //System.out.print(parts[j]);
                int value = Integer.parseInt(parts[j]);
                matrix[i][j] = new Variable(n,value,i,j);
            }
        }

        //remove assigned values from domain of other variables which value is zero in the same row, column
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
            {
                if (matrix[i][j].value != 0)
                {
                    for (int k=0;k<n;k++)
                    {
                        if (matrix[i][k].value == 0 )
                        {
                            matrix[i][k].domain.remove((Integer)matrix[i][j].value);
                        }
                        if (matrix[k][j].value == 0 )
                        {
                            matrix[k][j].domain.remove((Integer)matrix[i][j].value);
                        }
                    }
                }
            }
        }

//        for (int i=0;i<n;i++)
//        {
//            for (int j=0;j<n;j++)
//            {
//                for (int k=0;k<matrix[i][j].domain.size();k++)
//                {
//                    System.out.print(matrix[i][j].domain.get(k)+" ");
//                }
//                System.out.println();
//                System.out.println("for var"+matrix[i][j].value);
//            }
//        }

        //time calculate miliseconds
        long startTime = System.currentTimeMillis();

        CSPSolver solver = new CSPSolver(matrix,2);
        if (solver.solve())
        {
            long endTime = System.currentTimeMillis();
            //print the solution
            for (int i=0;i<n;i++)
            {
                for (int j=0;j<n;j++)
                {
                    System.out.print(matrix[i][j].value+" ");
                }
                System.out.println();
            }

            System.out.println("Number of nodes: "+solver.nodeCount);
            System.out.println("Number of backtracks: "+solver.backtrackCount);
            System.out.println("Time: "+(endTime-startTime)+" ms");
        }
        else {
            System.out.println("No solution");
        }




    }
}