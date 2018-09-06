package garg.ayush.wallpaperapp;

import java.util.ArrayList;

public class AutoWallpaperList {

    static ArrayList<String> autoWallpaper = new ArrayList<>();

    public static void addInAutoWallpaper(String wallpaper){
        autoWallpaper.add(wallpaper);
    }

    public static void emptyAutoWallpaper(){
        autoWallpaper=new ArrayList<>();
    }
    public static ArrayList<String> getAutoWallpaper() {
        return autoWallpaper;
    }
}
