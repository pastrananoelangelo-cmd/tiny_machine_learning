package pipeline;

import clean.DataCleaner;
import core.Dataset;
import dataset.CSVLoader;
import transform.FeatureTargetDataset;
import transform.TransformationPipeline;

public class DataPipeline {
    public FeatureTargetDataset run(String path) {
        Dataset dataset; // Create Dataset Instance
        Dataset cleaned; // Create Clean Dataset Instance

        // Load Dataset
        dataset = CSVLoader.load(path);
        // System.out.println(dataset);
        // System.out.println();

        // Print Original Dataset
        System.out.println("=== ORIGINAL ===");
        dataset.printPreview(10);
        System.out.println();

        // Create Cleaner Dataset
        DataCleaner cleaner = new DataCleaner();
        cleaned = cleaner.clean(dataset);

        // Preview Cleaned Dataset
        System.out.println("=== CLEANED ===");
        cleaned.printPreview(10);
        System.out.println();

        // Create Transform Dataset
        TransformationPipeline transform = new TransformationPipeline();
        FeatureTargetDataset featureTargetDataset = transform.transform(cleaned);

        return featureTargetDataset;
    }
}