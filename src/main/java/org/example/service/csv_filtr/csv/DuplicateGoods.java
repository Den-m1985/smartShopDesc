package org.example.service.csv_filtr.csv;

import java.util.ArrayList;
import java.util.List;

public class DuplicateGoods {

    public List<StructureCSV> findDuplicateGoods(List<StructureCSV> duplicateNames) {
        List<StructureCSV> Names = new ArrayList<>();
        for (StructureCSV row : duplicateNames) {
            String name = row.getName();
            String size = row.getArticular();
            int price = row.getPrice();
            int item = row.getItem();
            boolean duplicated = false;
            for (StructureCSV existing : Names) {
                String str1 = existing.getName();
                String str2 = existing.getArticular();
                int existingItem = existing.getItem();
                if (str1.equals(name)) {
                    if (str2.equals(size)) {
                        existing.setItem(existingItem + item);
                        duplicated = true;
                        break;
                    }
                }
            }
            if (!duplicated) {
                Names.add(new StructureCSV(name, size, price, item));
            }
        }
        return Names;
    }

}
