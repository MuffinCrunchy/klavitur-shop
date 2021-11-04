package com.muffincrunchy.klavitur_shop;

class Item {
    private final int img;
    private String name;
    private String price;
    private String desc;

    public Item(String name, String price, String desc, int img) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.img = img;

    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

}
