package transform;

import core.Dataset;
import java.util.*;

public class FeatureTargetSplit {
    public FeatureTargetDataset split(Dataset train, Dataset test) {
        Dataset xTrain = new Dataset();
        List<String> yTrain = new ArrayList<>();
        Dataset xTest = new Dataset();
        List<String> yTest = new ArrayList<>();
        String targetName = "passed";

        // Split X (features) and y (target)
        split(train, xTrain, yTrain, targetName);
        split(test, xTest, yTest, targetName);

        // Encode Target
        encodeTarget(yTrain);
        encodeTarget(yTest);

        // Print xTrain Dataset
        System.out.println("=== xTRAIN ===");
        xTrain.printPreview(10);
        System.out.println();

        // Print yTrain List
        System.out.println("=== yTRAIN ===");
        System.out.println(Arrays.toString(yTrain.toArray()));
        System.out.println();

        // Print xTest Dataset
        System.out.println("=== xTEST ===");
        xTest.printPreview(10);
        System.out.println();

        // Print yTrain List
        System.out.println("=== yTEST ===");
        System.out.println(Arrays.toString(yTest.toArray()));
        System.out.println();

        return new FeatureTargetDataset(
                xTrain,
                yTrain,
                xTest,
                yTest
        );
    }

    public void split(Dataset dataset, Dataset features, List<String> target, String targetName) {
        List<Map<String, String>> rows = dataset.getRows();
        Set<String> columns = dataset.getRows().get(0).keySet();

        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> map = new LinkedHashMap<>();
            Map<String, String> row = rows.get(i);

            for (String column : columns) {
                if (column.equals("student_id")) {
                    continue;
                }

                if (!column.equals(targetName)) {
                    map.put(column, row.get(column));
                } else {
                    target.add(row.get(column));
                }
            }

            features.addRow(map);
        }

    }

    public void encodeTarget(List<String> target) {
        int targetLength = target.size();

        for (int i = 0; i < targetLength; i++) {
            if (target.get(i).equals("yes")) {
                target.set(i, "1");
            } else {
                target.set(i, "0");
            }
        }
    }

}