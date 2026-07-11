package model;

import core.Dataset;
import java.util.*;

public interface Model {

    void fit(
        Dataset xTrain,
        List<String> yTrain
    );

    double predict(
        Map<String, String> sample
    );
}