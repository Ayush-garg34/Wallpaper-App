package garg.ayush.wallpaperapp;

import java.io.Serializable;

class Category implements Serializable{

    String name;
    String imageUrl;

    public Category(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
