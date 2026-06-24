package transform;

import clean.MissingValueCleaner;
import core.Dataset;
import java.util.*;

public class Normalizer {
    public Dataset normalizer (Dataset encoded, Encoder encoder) {
        MissingValueCleaner missValueCleaner = new MissingValueCleaner();
        Dataset normalized = new Dataset();
        Set<String> encodedColumn;
        String dataType;

        for (Map<String, String> row : encoded.getRows()) {
            normalized.addRow(new LinkedHashMap<>(row));
        }

        Set<String> columns = normalized.getRows().get(0).keySet();
        List<Map<String, String>> rows = normalized.getRows();

        encodedColumn = encoder.getEncodedColumns();
        for (String column : columns) {
            dataType = missValueCleaner.detectType(column, rows);

            if (column.equals("student_id") || column.equals("passed") || encodedColumn.contains(column)) {
                continue;
            }

            if (dataType.equals("NUMERIC")) {
                normalizeColumn(column, rows);
            }
        }

        return normalized;
    }

    public void normalizeColumn(String column, List<Map<String, String>> rows) {
        String current;
        int currNum;
        int min = getMin(column, rows);
        int max = getMax(column, rows);
        double result;

        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            current = row.get(column);
            currNum = Integer.parseInt(current);
            
            if (max == min) {
                result = 1.0 * (currNum - min) / 1;
            } else {
                result = 1.0 * (currNum - min) / (max - min);
            }
            result = Math.round(result * 100.0) / 100.0;

            row.put(column, String.valueOf(result));
        }
    }

    public int getMin(String column, List<Map<String, String>> rows) {
        String current;
        int min = Integer.MAX_VALUE;
        int val;

        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            current = row.get(column);
            val = Integer.parseInt(current);
            
            if (val < min) {
                min = val;
            }
        }

        return min;
    }

    public int getMax(String column, List<Map<String, String>> rows) {
        String current;
        int max = Integer.MIN_VALUE;
        int val;

        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            current = row.get(column);
            val = Integer.parseInt(current);

            if (val > max) {
                max = val;
            }
        }

        return max;
    }
}