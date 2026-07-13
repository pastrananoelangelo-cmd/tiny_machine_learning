package model;

import core.Dataset;
import java.util.*;

public class LinearRegression implements Model {

    private static final double INITIAL_WEIGHT = 1.0;
    private static final double INITIAL_BIAS = 1.0;
    private static final double LEARNING_RATE = 0.01;
    private static final int EPOCH = 10;
    private final Map<String, Double> weights;
    private final double bias;

    public LinearRegression() {
        weights = new LinkedHashMap<>();
        bias = INITIAL_BIAS;
    }

    @Override
    public void fit(Dataset xTrain, List<String> yTrain) {
        initializeWeights(xTrain);
        printWeights();
        
        List<Map<String, String>> rows = xTrain.getRows();
        for (int iter = 0; iter < EPOCH; iter++) {
            double epochLoss = 0;

            for (int i = 0; i < rows.size(); i++) {
                Map<String, String> row = rows.get(i);

                double prediction = predict(row);
                double actual = Double.parseDouble(yTrain.get(i));
                double error = prediction - actual;
                // System.out.println("Prediction: " + prediction);
                // System.out.println("Actual: " + actual);
                // System.out.println("Error: " + error);
                // System.out.println();

                updateWeights(row, error);

                epochLoss += loss(error);
            }

            double averageLoss = epochLoss / rows.size();
            System.out.println("Epoch " + (iter + 1));
            System.out.println("Average Loss " + averageLoss);
            System.out.println();

            printWeights();
        }

    }

    private void printWeights() {
        for (Map.Entry<String, Double> entry : weights.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.err.println();
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

    private double loss(double error) {
        return error * error;
    } 

    private void updateWeights(Map<String, String> student, double error) {
        for (String feature : student.keySet()) {
            double value = Double.parseDouble(student.get(feature));
            double weight = weights.get(feature);

            weight -= LEARNING_RATE * error * value;
            weights.put(feature, weight);

            // System.out.println(feature + " " + weight);
        }

        // System.out.println();
    }
}