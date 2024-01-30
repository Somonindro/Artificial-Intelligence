public class Solvable {
    //check if the grid is solvable or not
    int numberInversion;
    int index;
    int[][] current;
    int[] linear;
    int k;

    public Solvable(int[][] current, int k) {
        this.current = current;
        this.k = k;
        linear=new int[k*k];
        int p=0;
        index=0;
        for (int i=0 ; i<this.k ; i++)
        {
            for (int j=0 ; j<this.k ; j++)
            {
                if (current[i][j]==0) index=i;
                linear[p++]=current[i][j];
            }
        }

        numberInversion=0;
        for (int i=0 ; i<(this.k*this.k) ; i++)
        {
            for (int j=i+1 ; j<(this.k*this.k) ; j++)
            {
                if (linear[i]==0) continue;
                if (linear[i]>linear[j] && linear[j]!=0) numberInversion++;
            }
        }
        index=k-index;

    }

    public boolean check_solvable()
    {
        if (this.k%2!=0)
        {
            if (this.numberInversion%2==0) return true;
            else return false;
        }
        else {
            if ((this.numberInversion%2 ==0 && this.index%2!=0) || (this.numberInversion%2 !=0 && this.index%2 ==0)) return true;
            else return false;
        }
    }

}
