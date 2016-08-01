package com.example.twins.nicolinska.Model;

/**
 * Created by Twins on 06.07.2016.
 */

public class SaleModel {
    public String title;
    public String description;
    public String name;
    private int value = 0;
    public double priceRozniza;
    public double priceOpt;

    public SaleModel(String title, String description, String name, double priceRozniza, double priceOpt) {
        this.title = title;
        this.description = description;
        this.name = name;
        this.priceRozniza = priceRozniza;
        this.priceOpt = priceOpt;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
