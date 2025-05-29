package org.example.service.csv_filter.sadovod;

import org.example.service.csv_filter.csv.StructureCSV;

import java.util.ArrayList;
import java.util.List;

public class SadovodGoodsDuplicate <T extends StructureCSV> {

    public List<T> findDuplicateGoods(List<T> inputData) {
        List<T> result = new ArrayList<>();

        for (T currentItem : inputData) {
            boolean foundDuplicate = false;

            for (T existingItem : result) {
                if (itemsAreEqual(currentItem, existingItem)) {
                    // Если нашли дубликат - увеличиваем количество
                    existingItem.setItem(existingItem.getItem() + currentItem.getItem());
                    foundDuplicate = true;
                    break;
                }
            }

            // Если дубликат не найден - добавляем новый элемент
            if (!foundDuplicate) {
                T newItem = (T) currentItem.copyWithNewValues(
                        currentItem.getName(),
                        currentItem.getArticular(),
                        currentItem.getPrice(),
                        currentItem.getItem()
                );
                result.add(newItem);
            }
        }

        return result;
    }

    private boolean itemsAreEqual(T item1, T item2) {
        if (item1 == item2) return true;

        if (item1 instanceof SadovodCSV sadovod1 && item2 instanceof SadovodCSV sadovod2) {
            return sadovod1.equalsWithOutItem(sadovod2);
        }

        return item1.equalsWithoutItem(item2);
    }
}
