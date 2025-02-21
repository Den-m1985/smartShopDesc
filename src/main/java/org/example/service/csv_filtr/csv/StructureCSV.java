package org.example.service.csv_filtr.csv;

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
}
