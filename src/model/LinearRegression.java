package model;

import core.Dataset;
import java.util.*;

public class LinearRegression implements Model {

    private static final double INITIAL_WEIGHT = 1.0;
    private static final double INITIAL_BIAS = 1.0;
    private final Map<String, Double> weights;
    private double bias;

    public LinearRegression() {
        weights = new LinkedHashMap<>();
        bias = INITIAL_BIAS;
    }

    @Override
    public void fit(Dataset xTrain, List<String> yTrain) {
        initializeWeights(xTrain);
        
        List<Map<String, String>> rows = xTrain.getRows();
        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            double prediction = predict(row);
            System.out.println(prediction);
        }

    }

    private void initializeWeights(Dataset xTrain) {
        // create one weight for every feature
        Set<String> columns = xTrain.getRows().get(0).keySet();

        for (String column : columns) {
            weights.put(column, INITIAL_WEIGHT);
        }
    }

    @Override
    public double predict(Map<String, String> student) {
        double prediction = bias;

        for (String feature : student.keySet()) {
            double value = Double.parseDouble(student.get(feature));
            double weight = weights.get(feature);

            prediction += value * weight;
        }

        return prediction;
    }
}