package com.tomsc.decisiontree;

/**
 * Created by tomsc on 10.05.2018.
 */

public class Item implements Comparable<Item> {

    private String name, cut, category, topcategory;
    private String color, style;
    private int rank, randomizedRank, id;


    Item() {
        this.topcategory = "";
        this.category = "";
        this.cut = "";
        this.name = "";
        this.color = "";
        this.style = "";
        this.rank = 0;
        this.randomizedRank = 0;
        this.id = 0;

    }

    public Item(String name, String cut, String category, String topcategory, String color, String style, int rank, int randomizedRank, int id) {
        this.name = name;
        this.cut = cut;
        this.category = category;
        this.topcategory = topcategory;
        this.color = color;
        this.style = style;
        this.rank = rank;
        this.randomizedRank = randomizedRank;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCut() {
        return cut;
    }

    public void setCut(String cut) {
        this.cut = cut;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTopcategory() {
        return topcategory;
    }

    public void setTopcategory(String topcategory) {
        this.topcategory = topcategory;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRandomizedRank() {
        return randomizedRank;
    }

    public void setRandomizedRank(int randomizedRank) {
        this.randomizedRank = randomizedRank;
    }

    public int getId() {
        return id;
    }



    public int compareTo(Item compareitem) {
        int compareRandomizedRank= compareitem.getRandomizedRank();
        /* For Ascending order*/
        return this.randomizedRank-compareRandomizedRank;


    }
}