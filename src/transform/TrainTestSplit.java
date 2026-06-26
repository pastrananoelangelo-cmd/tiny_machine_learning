package transform;

import core.Dataset;
import java.util.*;

public class TrainTestSplit {
    public SplitDataset split(Dataset normalized) {
        Dataset train = new Dataset();
        Dataset test = new Dataset();

        List<Map<String, String>> rows = normalized.getRows();
        String target = "passed";

        List<Map<String, String>> passed = separate(rows, target, "yes");
        List<Map<String, String>> notPassed = separate(rows, target, "no");

        shuffle(passed);
        shuffle(notPassed);

        double splitNum = 0.8;
        int passNum, notPassNum;

        passNum = computeFloor(passed, splitNum);
        notPassNum = computeFloor(notPassed, splitNum);

        build(train, passed, notPassed, 0, passNum, 0, notPassNum);
        build(test, passed, notPassed, passNum, passed.size(), notPassNum, notPassed.size());
        
        shuffle(train.getRows());
        shuffle(test.getRows());

        System.out.println("=== TRAIN ===");
        train.printPreview(10);
        System.out.println();

        System.out.println("=== TEST ===");
        test.printPreview(10);
        System.out.println();

        return new SplitDataset(train, test);
    }

    public List<Map<String, String>> separate(List<Map<String, String>> rows, String target, String check) {
        List<Map<String, String>> result = new ArrayList<>();
        Map<String, String> row;

        for (int i = 0; i < rows.size(); i++) {
            row = rows.get(i);

            if (row.get(target).equals(check)) {
                result.add(row);
            }
        }

        return result;
    }

    public void shuffle(List<Map<String, String>> dataRows) {
        Collections.shuffle(dataRows);
    }

    public int computeFloor(List<Map<String, String>> dataRows, double splitNum) {
        return (int) Math.floor(dataRows.size() * splitNum);
    }

    public void build(
        Dataset data, 
        List<Map<String, String>> passed, List<Map<String, String>> notPassed, 
        int passStart, int passNumEnd, 
        int notPassStart, int notPassNumEnd) {
            for (int i = passStart; i < passNumEnd; i++) {
                Map<String, String> rowPass = passed.get(i);
                data.addRow(new LinkedHashMap<>(rowPass));
            }

            for (int i = notPassStart; i < notPassNumEnd; i++) {
                Map<String, String> rowNotPass = notPassed.get(i);
                data.addRow(new LinkedHashMap<>(rowNotPass));
            }
    }

    public static class SplitDataset {
        private final Dataset train;
        private final Dataset test;

        public SplitDataset(Dataset train, Dataset test) {
            this.train = train;
            this.test = test;
        }

        public Dataset getTrain() {
            return train;
        }

        public Dataset getTest() {
            return test;
        }
    }
}
