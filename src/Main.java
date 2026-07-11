import model.LinearRegression;
import pipeline.DataPipeline;
import transform.FeatureTargetDataset;

public class Main {
    public static void main(String[] args) {
        String filePath = "data/student.csv";

        DataPipeline pipeline = new DataPipeline();
        FeatureTargetDataset data = pipeline.run(filePath);
        LinearRegression linearRegression = new LinearRegression();
        linearRegression.fit(data.getXTrain(), data.getYTrain());

    }
}