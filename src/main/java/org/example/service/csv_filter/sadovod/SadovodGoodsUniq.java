package org.example.service.csv_filter.sadovod;

import org.example.service.csv_filter.csv.StructureCSV;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SadovodGoodsUniq {
    private List<SadovodCSV> duplicateNames;


    public List<SadovodCSV> findUniqueGoods(List<SadovodCSV> dataWithItem) {

        List<SadovodCSV> uniqueValues = new ArrayList<>();
        duplicateNames = new ArrayList<>();

        /*
        Для того, чтобы имена не меняли порядок, можно использовать LinkedHashMap
        для подсчета количества повторений имен. LinkedHashMap сохраняет порядок
         элементов в том порядке, в котором они были добавлены в мапу,
         в отличие от обычного HashMap.
         В первом цикле мы используем LinkedHashMap `nameCounts` для подсчета
         количества повторений имен и сохраняем порядок элементов.
         Во втором цикле мы добавляем элементы в соответствующие списки
         в зависимости от количества повторений имени в мапе `nameCounts`.
         */
        Map<String, Integer> nameCounts = new LinkedHashMap<>();
        for (StructureCSV structureCSV : dataWithItem) {
            String name = structureCSV.getName();
            int count = nameCounts.getOrDefault(name, 0) + 1;
            nameCounts.put(name, count);
        }
        for (SadovodCSV structureCSV : dataWithItem) {
            String name = structureCSV.getName();
            if (nameCounts.get(name) == 1) {
                uniqueValues.add(structureCSV);
            } else {
                duplicateNames.add(structureCSV);
            }
        }
        duplicateNames.sort(Comparator.comparing(StructureCSV::getName));

        return uniqueValues;
    }


    public List<SadovodCSV> getDuplicateNames() {
        return duplicateNames;
    }
}
