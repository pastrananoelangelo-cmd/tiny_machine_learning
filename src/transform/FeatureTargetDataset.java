package transform;

import core.Dataset;
import java.util.*;

public class FeatureTargetDataset {

    private final Dataset xTrain;
    private final List<String> yTrain;

    private final Dataset xTest;
    private final List<String> yTest;

    public FeatureTargetDataset(
            Dataset xTrain,
            List<String> yTrain,
            Dataset xTest,
            List<String> yTest) {

        this.xTrain = xTrain;
        this.yTrain = yTrain;

        this.xTest = xTest;
        this.yTest = yTest;
    }

    public Dataset getXTrain() {
        return xTrain;
    }

    public List<String> getYTrain() {
        return yTrain;
    }

    public Dataset getXTest() {
        return xTest;
    }

    public List<String> getYTest() {
        return yTest;
    }
}