import clean.DataCleaner;
import core.Dataset;
import dataset.CSVLoader;
import transform.Encoder;

// import java.util.*;

public class Main {
    public static void main(String[] args) {
        Dataset dataset; // Create Dataset Instance
        Dataset cleaned; // Create Clean Dataset Instance
        Dataset encoded; // Create Encoded Dataset Instance
        String filePath = "data/student.csv"; // Filepath

        // Load Dataset
        dataset = CSVLoader.load(filePath);
        // System.out.println(dataset);
        // System.out.println();

        // Print Original Dataset
        System.out.println("=== ORIGINAL ===");
        dataset.printPreview(10);
        System.out.println();

        // Create Cleaner
        DataCleaner cleaner = new DataCleaner();
        cleaned = cleaner.clean(dataset);

        // Preview Cleaned Dataset
        System.out.println("=== CLEANED ===");
        cleaned.printPreview(10);
        System.out.println();
        
        // Create Encoder
        Encoder encode = new Encoder();
        encoded = encode.encoder(cleaned);

        // Print Original Dataset
        System.out.println("=== ENCODED ===");
        encoded.printPreview(10);
        System.out.println();
        
    }
}