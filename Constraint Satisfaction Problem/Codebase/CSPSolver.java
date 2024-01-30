public class CSPSolver {
    int heuristicNumber;
    Constraint constraint;
    int nodeCount;
    int backtrackCount;
    VariableOrderHeuristic heuristic;
    Variable[][] grid;

    //constructor
    public CSPSolver(Variable[][] grid,int heuristicNumber) {
        this.heuristicNumber = heuristicNumber;
        this.grid = grid;
        constraint = new Constraint(grid);
        heuristic = new VariableOrderHeuristic(grid,heuristicNumber);
    }

    public boolean isSolved() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].value == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    //backtrack returns true if the CSP is solved
    public boolean backtrack(VariableOrderHeuristic heuristic) {
        //if the CSP is solved return true
        //else return false
        if (isSolved()) {

            return true;
        }
        //printGrid();
        nodeCount++;
        Variable var = heuristic.nextVariable();


        for (int i = 0; i < var.domain.size(); i++) {
            if (constraint.isConsistent(var, var.domain.get(i))) {
                var.value = var.domain.get(i);

                //var.domain.clear();
                if (backtrack(heuristic)) {
                    return true;
                }
                var.value = 0;
                //var.domain.add(var.domain.get(i));
            }
        }
        backtrackCount++;
        return false;
    }

    //forwardChecking method
    public boolean forwardChecking(VariableOrderHeuristic heuristic) {
        //if the CSP is solved return true
        //else return false
        if (isSolved()) {
            return true;
        }
        nodeCount++;
        Variable var = heuristic.nextVariable();
        for (int i = 0; i < var.domain.size(); i++) {
            if (constraint.isConsistent(var, var.domain.get(i))) {
                var.value = var.domain.get(i);
                //var.domain.clear();
                reduceDomain(var);
                if (checkDomain(var))
                {
                    //System.out.println("hello");
                    if (forwardChecking(heuristic)) {
                        //System.out.println("h");
                        return true;
                    }
                }
                fixDomain(var);
                var.value = 0;
            }
        }
        backtrackCount++;
        return false;
    }

    //check if the domain of the neighboring variables is empty
    public boolean checkDomain(Variable var) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][var.column].value == 0 && grid[i][var.column].domain.size() == 0 && i != var.row) {
                return false;
            }
            if (grid[var.row][i].value == 0 && grid[var.row][i].domain.size() == 0 && i != var.column) {
                return false;
            }
        }
        return true;
    }

    public void reduceDomain(Variable var) {
        //System.out.println("reduceDomain");
        for (int i = 0; i < grid.length; i++) {
            if (grid[var.row][i].value == 0 && i != var.column) {
                grid[var.row][i].domain.remove((Integer) var.value);
            }
            if (grid[i][var.column].value == 0 && i != var.row) {
                grid[i][var.column].domain.remove((Integer) var.value);
            }
        }
    }

    public void fixDomain(Variable var) {
        //System.out.println("fix");
        for (int i = 0; i < grid.length; i++) {
            if (grid[var.row][i].value == 0 && i != var.column) {
                grid[var.row][i].domain.add(var.value);
            }
            if (grid[i][var.column].value == 0 && i != var.row) {
                grid[i][var.column].domain.add(var.value);
            }
        }
    }






    public boolean solve() {
        return forwardChecking(heuristic);
    }

    //printGrid method that prints the grid
    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j].value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
