import java.util.ArrayList;

public class Variable {
    //constructor
    int gridSize;
    public int value;
    int row;
    int column;
    public ArrayList<Integer> domain;

    //constructor
    public Variable(int gridSize,int value,int row,int column) {
        this.row = row;
        this.column = column;
        this.gridSize = gridSize;
        this.value = value;
        domain=new ArrayList<>();
        if (value == 0)//not assigned
        {
            for (int i=1;i<=gridSize;i++)
            {
                domain.add(i);
            }
        }
    }


}
