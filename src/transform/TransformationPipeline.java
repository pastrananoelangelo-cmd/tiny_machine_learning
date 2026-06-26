package transform;

import core.Dataset;

public class TransformationPipeline {
    public FeatureTargetDataset transform(Dataset cleaned) {
        Dataset encoded; // Create Encoded Dataset Instance
        Dataset normalized; // Create Normalized Dataset Instance
        Dataset train; // Create Train Dataset Instance
        Dataset test; // Create Test Dataset Instance

        // Create Encoder
        Encoder encode = new Encoder();
        encoded = encode.encoder(cleaned);

        // Print Encoded Dataset
        System.out.println("=== ENCODED ===");
        encoded.printPreview(10);
        System.out.println();

        // Create Normalizer
        Normalizer normalize = new Normalizer();
        normalized = normalize.normalizer(encoded, encode);

        // Print Normalized Dataset
        System.out.println("=== NORMALIZED ===");
        normalized.printPreview(10);
        System.out.println();

        // Create TrainTestSplit
        TrainTestSplit splitter = new TrainTestSplit();
        TrainTestSplit.SplitDataset split = splitter.split(normalized);

        train = split.getTrain();
        test = split.getTest();

        // Create Feature Target Split
        FeatureTargetSplit featureTargetSplit = new FeatureTargetSplit();
        FeatureTargetDataset featureTargetDataset = featureTargetSplit.split(train, test);

        return featureTargetDataset;
    }
}
