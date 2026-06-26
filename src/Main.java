import pipeline.DataPipeline;
import transform.FeatureTargetDataset;

// import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "data/student.csv";

        DataPipeline pipeline = new DataPipeline();
        FeatureTargetDataset data = pipeline.run(filePath);
    }
}