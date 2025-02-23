package org.example.service.csv_filtr.csv;

import java.util.Objects;

public class StructureCSV {
    private final String name;
    private final String articular;
    private final int price;
    private int item;

    public StructureCSV(String name, String articular, int price, int item) {
        this.name = name;
        this.articular = articular;
        this.price = price;
        this.item = item;
    }


    public String getName() {
        return name;
    }

    public String getArticular() {
        return articular;
    }

    public int getPrice() {
        return price;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "name= " + name + ", artucul= " + articular + ", price= " + price + ", item= " + item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StructureCSV that = (StructureCSV) o;
        return price == that.price && item == that.item && Objects.equals(name, that.name) && Objects.equals(articular, that.articular);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, articular, price, item);
    }

}
