import clean.DuplicateCleaner;
import clean.MissingValueCleaner;
import core.Dataset;
import dataset.CSVLoader;

// import java.util.*;

public class Main {
    public static void main(String[] args) {
        Dataset dataset; // Create Dataset Instance
        Dataset cleaned; // Create Clean Dataset Instance
        Dataset unique; // Create Unique Dataset Instance
        String filePath = "data/student.csv"; // Filepath

        // Load Dataset
        dataset = CSVLoader.load(filePath);
        // System.out.println(dataset);
        // System.out.println();

        // Print Original Dataset
        System.out.println("=== ORIGINAL ===");
        dataset.printPreview(10);
        System.out.println();

        // Create cleaner
        MissingValueCleaner cleaner = new MissingValueCleaner();

        // Clean
        cleaned = cleaner.clean(dataset);

        // Preview Cleaned Dataset
        System.out.println("=== CLEANED ===");
        cleaned.printPreview(10);
        System.out.println();

        // Create duplicate
        DuplicateCleaner duplicateCleaner = new DuplicateCleaner();

        // Dupicate
        unique = duplicateCleaner.uniqueCleaner(cleaned);

        // Preview Unique Dataset
        System.out.println("=== UNIQUE ===");
        unique.printPreview(10);
        System.out.println();
        
    }
}