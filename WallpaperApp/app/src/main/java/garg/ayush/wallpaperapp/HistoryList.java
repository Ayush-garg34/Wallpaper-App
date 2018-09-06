package garg.ayush.wallpaperapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

public class HistoryList {

    static ArrayList<String> historyArrayList = new ArrayList<>();


    public static void addInHistory(String wallpaper) {
        if (historyArrayList.contains(wallpaper))
            return;
        else
            historyArrayList.add(wallpaper);
    }

    public static ArrayList<String> getHistoryArrayList() {
        return historyArrayList;
    }

}
