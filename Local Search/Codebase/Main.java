import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "tre-s-92";
        String strategy = "exponential";
        int heuristic = 2;

        InputProcessing.processInput(fileName,strategy,heuristic);
    }
}

//largest degree heuristic = 1
//saturation degree heuristic = 2
//largest enrollment heuristic = 3
//random ordering heuristic = 4