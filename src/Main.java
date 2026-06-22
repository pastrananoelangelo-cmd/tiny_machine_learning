import core.Dataset;
import dataset.CSVLoader;

// import java.util.*;

public class Main {
    public static void main(String[] args) {
        Dataset dataset; // Create Dataset Instance
        String filePath = "data/student.csv"; // Filepath

        // Load Dataset
        dataset = CSVLoader.load(filePath);
        // System.out.println(dataset);

       // Print Dataset
       dataset.printPreview(5);
    }
}