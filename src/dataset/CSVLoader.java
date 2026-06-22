package dataset;

import core.Dataset;
import java.io.*;
import java.util.*;

public class CSVLoader {
    @SuppressWarnings({"CallToPrintStackTrace", "ConvertToTryWithResources"})

    public static Dataset load(String filePath) {
        // TODO 1: Create Dataset
        Dataset dataset = new Dataset();

        try {
            // TODO 2: Open CSV
            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(filePath));

            // TODO 3: Read header
            String headerLine = reader.readLine();

            // Split headers
            String[] headers = headerLine.split(",");
            // System.out.println("Headers:");
            // System.out.println(Arrays.toString(headers));
            // System.out.println();

            // System.out.println(headers.length);

            // TODO 4: Loop rows
            String line;
            while ((line = reader.readLine()) != null) {
                // Create row map
                Map<String, String> row = new LinkedHashMap<>();

                // Split row
                String[] values = line.split(",");
                // System.out.println("Values:");
                // System.out.println(Arrays.toString(values));
                // System.out.println();

                // System.out.println(values.length);

                // Match headers
                for (int i = 0; i < headers.length; i++) {
                    if (values.length > i) {
                        row.put(headers[i].trim(),
                                values[i].trim());
                    } else {
                        // Fill missing
                        row.put(headers[i], null);
                    }
                }

                // Add row
                dataset.addRow(row);

                // System.out.println("Row:");
                // System.out.println(row);
                // System.out.println();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO 5: Return Dataset
        return dataset;
    }
}