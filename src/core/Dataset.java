package core;

import java.util.*;

public class Dataset {
    // TODO 1: Internal storage for rows
    // Hint: each row = Map<String, String>
    private final List<Map<String, String>> rows;

    // TODO 2: Constructor
    // Initialize your list here
    public Dataset() {
        // Initialize rows
        rows = new ArrayList<>();
    }

    // TODO 3: Add a row into dataset
    public void addRow(Map<String, String> row) {
        // TODO: add row to list
        rows.add(row);
    }

    // TODO 4: Get all rows
    public List<Map<String, String>> getRows() {
        // TODO: return rows
        // Returns a copy, not the original list.
        return new ArrayList<>(rows);
    }

    // TODO 5: Get Dataset size
    public int size() {
        // TODO: return number of rows
        return rows.size();
    }

    // TODO 6: Print Dataset (for debugging)
    public void printPreview(int limit) {
        // TODO: print first N rows

        if (rows.isEmpty()) {
            System.out.println("Dataset is empty.");
            return;
        }

        // Get column names from first row
        Set<String> columns = rows.get(0).keySet();

        // Print header
        System.out.print("Row\t");
        for (String col : columns) {
            System.out.print(col + "\t");
        }
        System.out.println();

        // Divider
        System.out.println("-".repeat(120));

        // Print rows
        for (int i = 0; i < Math.min(limit, rows.size()); i++) {
            Map<String, String> row = rows.get(i);

            System.out.print(i + "\t");

            for (String col : columns) {
                if (col.equals("age") || 
                    col.equals("attendance") ||
                    col.equals("department") ||
                    col.equals("internet_access") ||
                    col.equals("previous_score") ||
                    col.equals("passed")) {
                    System.out.print("\t");
                }
                System.out.print(row.get(col) + "\t");
            }

            System.out.println();
        }
    }
}