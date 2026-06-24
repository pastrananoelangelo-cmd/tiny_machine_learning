package clean;

import core.Dataset;

public class DataCleaner {
    public Dataset clean(Dataset dataset) {
        Dataset missingCleaner; // Create Clean Dataset Instance
        Dataset unique; // Create Unique Dataset Instance
        
        // Create cleaner
        MissingValueCleaner cleaner = new MissingValueCleaner();
        
         // Create duplicate
        DuplicateCleaner duplicateCleaner = new DuplicateCleaner();

        // Clean
        missingCleaner = cleaner.clean(dataset);

        // // Preview Cleaned Dataset
        // System.out.println("=== CLEANED ===");
        // missingCleaner.printPreview(10);
        // System.out.println();

        // Dupicate
        unique = duplicateCleaner.uniqueCleaner(missingCleaner);

        // // Preview Unique Dataset
        // System.out.println("=== UNIQUE ===");
        // unique.printPreview(10);
        // System.out.println();

        return unique;
    }
}
