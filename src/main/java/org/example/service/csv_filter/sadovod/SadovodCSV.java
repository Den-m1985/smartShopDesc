package org.example.service.csv_filter.sadovod;

import org.example.service.csv_filter.csv.StructureCSV;

import java.util.Objects;

public class SadovodCSV extends StructureCSV {
    private final String category;
    private final String size;

    public SadovodCSV(String name, String articular, int price, int item, String category, String size) {
        super(name, articular, price, item);
        this.category = category;
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public String getSize() {
        return size;
    }

    @Override
    public SadovodCSV copyWithNewValues(String name, String articular, int price, int item) {
        // Используем текущие значения category и size из этого объекта
        return new SadovodCSV(name, articular, price, item, this.category, this.size);
    }


    public boolean equalsWithOutItem(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equalsWithoutItem(o)) return false;
        SadovodCSV that = (SadovodCSV) o;
        return Objects.equals(category, that.category) && Objects.equals(size, that.size);
    }

}
