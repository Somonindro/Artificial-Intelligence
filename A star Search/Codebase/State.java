import java.util.ArrayList;
import java.util.Objects;

public class State
{
    private int g; // g(n) is distance from current node to root node
    private int h;
    private int f;
    public String[][] grid;
    private String algo;
    public  String[][] goalState;

    public State(String[][] a, int g , String algo)
    {
        int N = a.length;
        this.grid = new String[N][N];
        goalState = new String[N][N];
        define_goal_state(N);
        for (int i=0;i<N;i++)
        {
            for (int j=0; j<N; j++)
            {
                this.grid[i][j] = a[i][j];
            }
        }
        this.g = g;
        this.algo = algo;
        if (Objects.equals(algo, "manhattan")) this.h = manhattan_distance();
        if (Objects.equals(algo, "hamming")) this.h = hamming_distance();
        this.f=this.h+g;

    }

    public int getF() {
        return f;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public String getAlgo() {
        return algo;
    }

    public void define_goal_state(int n)
    {
        //defining the goal state
        int t=1;
        for (int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                goalState[i][j] = String.valueOf(t);
                t++;
            }
        }
        goalState[n-1][n-1]=" ";
    }

    //misplaced tiles
    private int hamming_distance()
    {
        int sum =0,t=1;
        int N = goalState.length;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!Objects.equals(this.grid[i][j], String.valueOf(t))) sum++; //matching with goal state
                t++;
            }
        }
        return (sum-1);
    }

    private int manhattan_distance()
    {
        int sum=0;
        int[] index= new int[2];
        int N = goalState.length;
        for (int i = 0;i<N;i++)
        {
            for (int j = 0; j<N; j++)
            {
                if(this.grid[i][j].trim().isEmpty())
                {
                    continue;//for space
                }
                index = find_index(Integer.parseInt(this.grid[i][j]));
                sum += (Math.abs(i-index[0])+Math.abs(j-index[1]));
            }
        }
        return sum;
    }

    private int[] find_index(int a)
    {
        int[] index = new int[2];
        int N = goalState.length;
        for (int i = 0 ; i<N ; i++)
        {
            for (int j = 0; j<N ; j++)
            {
                if(goalState[i][j].trim().isEmpty()) continue;
                if (goalState[i][j].trim().equals(String.valueOf(a)))
                {
                    index[0]=i;
                    index[1]=j;
                    return index;
                }
            }
        }
        return index;
    }

    public ArrayList<State> expand(State parent)
    {
        ArrayList<State> successor= new ArrayList<State>();
        int N = this.grid.length;
        for (int i=0; i< N; i++)
        {
            for (int j = 0; j<N; j++)
            {
                if (parent.grid[i][j].trim().isEmpty())
                {
                    if(i-1>=0)
                    {
                        String[][] a = new String[N][N];
                        for (int l=0;l<N;l++)
                        {
                            for(int m=0;m<N;m++)
                            {
                                a[l][m]=parent.grid[l][m];
                            }
                        }
                        a = swap(a,i,j,i-1,j);
                        State b = new State(a,parent.g+1,this.algo);
                        successor.add(b);
                    }
                    if(j-1>=0)
                    {
                        String[][] a = new String[N][N];
                        for (int l=0;l<N;l++)
                        {
                            for(int m=0;m<N;m++)
                            {
                                a[l][m]=parent.grid[l][m];
                            }
                        }
                        a = swap(a,i,j,i,j-1);
                        State b = new State(a,parent.g+1,this.algo);
                        successor.add(b);
                    }
                    if(i+1<N)
                    {
                        String[][] a = new String[N][N];
                        for (int l=0;l<N;l++)
                        {
                            for(int m=0;m<N;m++)
                            {
                                a[l][m]=parent.grid[l][m];
                            }
                        }
                        a = swap(a,i,j,i+1,j);
                        State b = new State(a,parent.g+1,this.algo);
                        successor.add(b);
                    }
                    if(j+1<N)
                    {
                        String[][] a = new String[N][N];
                        for (int l=0;l<N;l++)
                        {
                            for(int m=0;m<N;m++)
                            {
                                a[l][m]=parent.grid[l][m];
                            }
                        }
                        a = swap(a,i,j,i,j+1);
                        State b = new State(a,parent.g+1,this.algo);
                        successor.add(b);
                    }
                }
            }
        }
        return successor;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setAlgo(String algo) {
        this.algo = algo;
    }

    private String[][] swap(String[][] a,int row1, int col1, int row2, int col2)
    {
        String[][] str = a;
        String tmpStr = str[row1][col1];
        str[row1][col1] = str[row2][col2];
        str[row2][col2] = tmpStr;
        return str;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

}