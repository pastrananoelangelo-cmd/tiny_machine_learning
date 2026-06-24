package transform;

import clean.MissingValueCleaner;
import core.Dataset;
import java.util.*;

public class Encoder {

    public Dataset encoder(Dataset cleaned) {
        MissingValueCleaner missValueCleaner = new MissingValueCleaner();
        Dataset encoded = new Dataset();
        String dataType;

        for (Map<String, String> row : cleaned.getRows()) {
            encoded.addRow(new LinkedHashMap<>(row));
        }

        Set<String> columns = encoded.getRows().get(0).keySet();
        List<Map<String, String>> rows = encoded.getRows();

        for (String column : columns) {
            dataType = missValueCleaner.detectType(column, rows);

            if (column.equals("student_id") || column.equals("passed")) {
                continue;
            }

            if (dataType.equals("CATEGORICAL")) {
                encodeColumn(column, rows);
            }
        }

        
        return encoded;
    }

    public void encodeColumn(String column, List<Map<String, String>> rows) {
        Map<String, String> map = new LinkedHashMap<>();
        String current;
        int value = 0;

        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            current = row.get(column);

            if (!map.containsKey(current)) {
                map.put(current, String.valueOf(value));
                value++;
            }

            row.put(column, map.get(current));
        }
    }
}