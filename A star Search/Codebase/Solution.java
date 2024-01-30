import java.util.*;
public class Solution
{
    public static PriorityQueue<State> pq = new PriorityQueue<State>(Comparator.comparing(State::getF));
    public static ArrayList<State> expanded = new ArrayList<State>();
    private int k; //grid size

    public Solution(State first, int k) {
        this.k =k;
        if(first==null) {
            System.out.println("Provide an input");
        }
        pq.add(first);
        ArrayList<State> list;

        while(!pq.isEmpty())
        {
            int visited;
            State current = pq.poll();
            expanded.add(current);
            if(Arrays.deepEquals(current.grid, current.goalState)) break;
            list = current.expand(current);

            for (State l:list)
            {
                visited = 0;
                for (State e:expanded)
                {
                    if(Arrays.deepEquals(l.grid, e.grid))
                    {
                        visited = 1;
                    }
                }
                if(visited==1) continue;
                pq.add(l);
            }
        }
    }

    public static PriorityQueue<State> getPq() {
        return pq;
    }

    public static void setPq(PriorityQueue<State> pq) {
        Solution.pq = pq;
    }

    public static ArrayList<State> getExpanded() {
        return expanded;
    }

    public static void setExpanded(ArrayList<State> expanded) {
        Solution.expanded = expanded;
    }

    public int print()
    {
        int x = 0;
        for(State states:expanded) {
            for (int l = 0;l<this.k;l++)
            {
                for (int m=0;m<this.k;m++)
                {
                    if (Objects.equals(states.grid[l][m], " ")) System.out.print("*  ");
                    else System.out.print(states.grid[l][m]+"  ");
                }
                System.out.println();
            }

            System.out.println("g(n) :"+states.getG());
            System.out.println("h(n) :"+states.getH());
            System.out.println("f(n) :"+states.getF());
            System.out.println();
            x=states.getF();
        }
        System.out.println("Total Nodes explored :"+expanded.size());
        System.out.println("Total Nodes expanded:"+(expanded.size()+pq.size()));
        pq.clear();
        expanded.clear();
        return x;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }
}
