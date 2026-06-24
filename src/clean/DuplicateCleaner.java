package clean;

import core.Dataset;
import java.util.*;

public class DuplicateCleaner {
    @SuppressWarnings("UnnecessaryContinue")
    public Dataset uniqueCleaner(Dataset cleaned) {
        Dataset unique = new Dataset();
        Set<String> set = new LinkedHashSet<>();

        for (Map<String,String> row : cleaned.getRows()) {
            StringBuilder sb = new StringBuilder();

            for (Map.Entry<String, String> entry : row.entrySet()) {
                if (entry.getKey().equals("student_id")) {
                    continue;
                }

                sb.append(entry);
                sb.append("|");
            }

            if (!set.contains(sb.toString())) {
                set.add(sb.toString());
                unique.addRow(new LinkedHashMap<>(row));
            }
        }

        return unique;
    }
}
