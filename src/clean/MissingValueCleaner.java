package clean;

import core.Dataset;
import java.util.*;

public class MissingValueCleaner {
    public Dataset clean(Dataset dataset) {
        Dataset cleaned = new Dataset();
        String dataType, value;

        for (Map<String, String> row : dataset.getRows()) {
            cleaned.addRow(new LinkedHashMap<>(row));
        }

        Set<String> columns = cleaned.getRows().get(0).keySet();
        List<Map<String, String>> rows = cleaned.getRows();

        for (String column : columns) {
            dataType = detectType(column, rows);

            if (dataType.equals("NUMERIC")) {
                value = computeMean(column, rows);
            } else {
                value = computeMode(column, rows);
            }

            fillColumn(column, rows, value);
        }

        return cleaned;
    }

    @SuppressWarnings("UseSpecificCatch")
    public String detectType(String column, List<Map<String, String>> rows) {
        boolean isCategoric = false;

        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);

            if (row.get(column) != null) {
                try {
                    Integer.valueOf(row.get(column));
                } catch (Exception e) {
                    isCategoric = true;
                }
            } 

        }

        if (!isCategoric) {
            return "NUMERIC";
        }
        return "CATEGORICAL";
        
    }

    public String computeMean(String column, List<Map<String, String>> rows) {
        String curr_column;
        int num, count_valid = 0;
        double ans = 0;
        
        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            curr_column = row.get(column);

            if (curr_column != null) {
                num = Integer.parseInt(curr_column);
                ans += num;
                count_valid++;
            }
        }

        ans /= count_valid;
        
        return String.valueOf(Math.round(ans));
    }

    public String computeMode(String column, List<Map<String, String>> rows) {
        HashMap<String, Integer> map = new LinkedHashMap<>();
        String curr_value, highestValueCount = "";
        int highestCount = 0;

        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            curr_value = row.get(column);

            if (curr_value != null) {
                if (!map.containsKey(curr_value)) {
                    map.put(curr_value, 0);
                }
                map.put(curr_value, map.get(curr_value) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > highestCount) {
                highestCount = entry.getValue();
                highestValueCount = entry.getKey();
            }
        }

        
        return highestValueCount;
    }

    public void fillColumn(String column, List<Map<String, String>> rows, String value) {
        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            if (row.get(column) == null) {
                row.put(column, value);
            }
        }
    }
}
