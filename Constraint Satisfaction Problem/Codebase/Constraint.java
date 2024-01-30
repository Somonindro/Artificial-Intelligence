public class Constraint {
    public Variable[][] grid;

    public Constraint(Variable[][] grid) {
        this.grid = grid;
    }

    //isConsistent method that takes a variable and a value as input and returns true if the value is consistent with the neighboring variables
    public boolean isConsistent(Variable var, int value) {
        //check if the value is consistent with the neighboring variables
        //if it is consistent return true
        //else return false
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][var.column].value == value && i != var.row) {
                return false;
            }
            if (grid[var.row][i].value == value && i != var.column) {
                return false;
            }

        }
        return true;
    }

}
