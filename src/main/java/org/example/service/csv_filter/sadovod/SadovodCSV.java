package org.example.service.csv_filter.sadovod;

import org.example.service.csv_filter.csv.StructureCSV;

import java.util.Objects;

public class SadovodCSV extends StructureCSV {
    private final String size;

    public SadovodCSV(String name, String articular, String size, int price, int item) {
        super(name, articular, price, item);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    @Override
    public SadovodCSV copyWithNewValues(String name, String articular, int price, int item) {
        // Используем текущие значения category и size из этого объекта
        return new SadovodCSV(name, articular, this.size, price, item);
    }


    public boolean equalsWithOutItem(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equalsWithoutItem(o)) return false;
        SadovodCSV that = (SadovodCSV) o;
        return Objects.equals(size, that.size);
    }

}
