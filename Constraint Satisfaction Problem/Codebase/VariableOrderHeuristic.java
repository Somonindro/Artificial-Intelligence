import java.util.Random;

public class VariableOrderHeuristic {
    //nextVariable method that takes a matrix and returns the next variable to be assigned
    int heuristicNumber;
    public Variable[][] grid;

    //constructor
    public VariableOrderHeuristic(Variable[][] grid,int heuristicNumber) {
        this.grid = grid;
        this.heuristicNumber = heuristicNumber;
    }

    public int max_degree_calculation(Variable var)
    {
        int count = 1;
        for (int k=0;k<grid.length;k++)
        {
            if (grid[var.row][k].value==0 && k!= var.column)
            {
                count++;
            }
            if (grid[k][var.column].value==0 && k!= var.row)
            {
                count++;
            }
        }
        return count;
    }

    public Variable nextVariable() {
        if (this.heuristicNumber == 1)//VAH1
        {
            int min=grid.length+1;
            Variable var = null;
            for (int i=0;i<grid.length;i++)
            {
                for (int j=0;j<grid.length;j++)
                {
                    if (grid[i][j].domain.size() < min && grid[i][j].value == 0)
                    {
                        min = grid[i][j].domain.size();
                        var = grid[i][j];
                    }
                }
            }
            //System.out.println("hello");
            return var;
        }
        else if (this.heuristicNumber == 2)//VAH2
        {
            int max=0,count;
            Variable var = null;
            for (int i=0;i<grid.length;i++)
            {
                for (int j=0;j<grid.length;j++)
                {
                    if (grid[i][j].value==0)
                    {
                        count = max_degree_calculation(grid[i][j]);
                        if (count > max)
                        {
                            max = count;
                            var = grid[i][j];
                        }
                    }
                }
            }
            return var;
        }
        else if (this.heuristicNumber == 3) //VAH3
        {

            int min=grid.length+1;
            Variable var = null;
            for (int i=0;i<grid.length;i++)
            {
                for (int j=0;j<grid.length;j++)
                {
                    if (grid[i][j].domain.size() < min && grid[i][j].value == 0)
                    {
                        min = grid[i][j].domain.size();
                        var = grid[i][j];
                    }
                    else if (grid[i][j].domain.size() == min && grid[i][j].value == 0)//tie by VAH1, break by VAH2
                    {
                        if (max_degree_calculation(grid[i][j])>max_degree_calculation(var))
                        {
                            var = grid[i][j];
                        }
                    }

                }
            }
            return var;
        }
        else if (this.heuristicNumber == 4) //VAH4
        {
            //The variable chosen is the one that minimizes the (VAH1 / VAH2)

            int resultMin = grid.length+2;
            int min,max;//min for VAH1, max for VAH2
            Variable var = null;

            for (int i=0;i<grid.length;i++)
            {
                for (int j=0;j<grid.length;j++)
                {
                    if (grid[i][j].value==0)
                    {
                        min = grid[i][j].domain.size();
                        max = max_degree_calculation(grid[i][j]);
                        if (min/max < resultMin)
                        {
                            resultMin = min/max;
                            var = grid[i][j];
                        }
                    }
                }
            }

            return var;

        }

        else if (this.heuristicNumber == 5) //VAH5
        {
            //a random unassigned variable is chosen and returned
            Random rand = new Random();
            int row = rand.nextInt(grid.length);
            int column = rand.nextInt(grid.length);
            while (grid[row][column].value != 0)
            {
                row = rand.nextInt(grid.length);
                column = rand.nextInt(grid.length);
            }
            //System.out.println("r "+row+" c "+column);
            return grid[row][column];

        }
        else return null;
    }
}