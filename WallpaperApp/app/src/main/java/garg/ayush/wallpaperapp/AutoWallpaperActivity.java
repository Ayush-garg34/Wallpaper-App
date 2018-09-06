package garg.ayush.wallpaperapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import androidx.work.Data;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class AutoWallpaperActivity extends AppCompatActivity {


    int time;
    CheckBox all;
    CheckBox abstrct;
    CheckBox texture, quotes, nature, flowers;

    CheckBox t_24, t_12, t_8, t_4, t_2, t_1, t_30;

    Button saveCategory;

    Button saveTime;

    Button setAutoWallpaper;

    Button setScreen;

    CheckBox both,homeSrceen,lock;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_wallpaper);



        SharedPreferences sharedPreferences = getSharedPreferences("CategoryData", MODE_PRIVATE);

        setAutoWallpaper = findViewById(R.id.setAutoWallpaper);

        setScreen=findViewById(R.id.saveScreen);

        saveCategory = findViewById(R.id.saveCategory);
        all = findViewById(R.id.CBall);
        abstrct = findViewById(R.id.CBabstract);
        quotes = findViewById(R.id.CBquotes);
        flowers = findViewById(R.id.CBflowers);
        nature = findViewById(R.id.CBnature);
        texture = findViewById(R.id.CBtexture);

        t_1 = findViewById(R.id.time1);
        t_2 = findViewById(R.id.time2);
        t_4 = findViewById(R.id.time4);
        t_8 = findViewById(R.id.time8);
        t_12 = findViewById(R.id.time12);
        t_24 = findViewById(R.id.time24);
        t_30 = findViewById(R.id.time30);

        homeSrceen=findViewById(R.id.Home);
        lock=findViewById(R.id.Lock);
        both=findViewById(R.id.Both);

        saveTime = findViewById(R.id.saveTime);
        if (sharedPreferences.contains("texture")) {
            texture.setChecked(sharedPreferences.getBoolean("texture", false));
        }
        if (sharedPreferences.contains("quotes")) {
            quotes.setChecked(sharedPreferences.getBoolean("quotes", false));
        }
        if (sharedPreferences.contains("flowers")) {
            flowers.setChecked(sharedPreferences.getBoolean("flowers", false));
        }
        if (sharedPreferences.contains("nature")) {
            nature.setChecked(sharedPreferences.getBoolean("nature", false));
        }
        if (sharedPreferences.contains("abstract")) {
            abstrct.setChecked(sharedPreferences.getBoolean("abstract", false));
        }
        if (sharedPreferences.contains("all")) {
            all.setChecked(sharedPreferences.getBoolean("all", false));
        }

        SharedPreferences sharedPreferencesScreen = getSharedPreferences("ScreenData", MODE_PRIVATE);

        if (sharedPreferencesScreen.contains("home")) {
            homeSrceen.setChecked(sharedPreferences.getBoolean("home", false));
        }
        if (sharedPreferencesScreen.contains("lock")) {
            lock.setChecked(sharedPreferences.getBoolean("lock", false));
        }
        if (sharedPreferencesScreen.contains("both")) {
            both.setChecked(sharedPreferences.getBoolean("both", false));
        }

        SharedPreferences sharedPreferencesTime = getSharedPreferences("TimeData", MODE_PRIVATE);

        if(sharedPreferencesTime.contains("1")){
            t_1.setChecked(sharedPreferencesTime.getBoolean("1",false));
        }
        if(sharedPreferencesTime.contains("2")){
            t_2.setChecked(sharedPreferencesTime.getBoolean("2",false));
        }
        if(sharedPreferencesTime.contains("4")){
            t_4.setChecked(sharedPreferencesTime.getBoolean("4",false));
        }
        if(sharedPreferencesTime.contains("8")){
            t_8.setChecked(sharedPreferencesTime.getBoolean("8",false));
        }
        if(sharedPreferencesTime.contains("12")){
            t_12.setChecked(sharedPreferencesTime.getBoolean("12",false));
        }
        if(sharedPreferencesTime.contains("24")){
            t_24.setChecked(sharedPreferencesTime.getBoolean("24",false));
        }
        if(sharedPreferencesTime.contains("1")){
            t_30.setChecked(sharedPreferencesTime.getBoolean("30",false));
        }


        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (all.isChecked()) {
                    texture.setChecked(true);
                    abstrct.setChecked(true);
                    quotes.setChecked(true);
                    nature.setChecked(true);
                    flowers.setChecked(true);
                } else {
                    texture.setChecked(false);
                    abstrct.setChecked(false);
                    quotes.setChecked(false);
                    nature.setChecked(false);
                    flowers.setChecked(false);

                }
            }
        });

        saveCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("CategoryData", MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("all", all.isChecked());
                editor.putBoolean("texture", texture.isChecked());
                editor.putBoolean("flowers", flowers.isChecked());
                editor.putBoolean("abstract", abstrct.isChecked());
                editor.putBoolean("quotes", quotes.isChecked());
                editor.putBoolean("nature", nature.isChecked());
                editor.apply();

                AutoWallpaperList.emptyAutoWallpaper();
                if (all.isChecked()) {
                    addArrayList(AbstractList.getAbstractArrayList());
                    addArrayList(FlowerList.getFlowerArrayList());
                    addArrayList(NatureList.getNatureArrayList());
                    addArrayList(QuotesList.getQuotesArrayList());
                    addArrayList(TextureList.getTextureArraylist());
                } else if (abstrct.isChecked())
                    addArrayList(AbstractList.getAbstractArrayList());
                else if (flowers.isChecked())
                    addArrayList(FlowerList.getFlowerArrayList());
                else if (nature.isChecked())
                    addArrayList(NatureList.getNatureArrayList());
                else if (quotes.isChecked())
                    addArrayList(QuotesList.getQuotesArrayList());
                else if (texture.isChecked())
                    addArrayList(TextureList.getTextureArraylist());

                Log.e("TAG", "onClick: " + AutoWallpaperList.getAutoWallpaper().size());
            }

        });

        t_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t_1.isChecked()) {
                    t_2.setChecked(false);
                    t_4.setChecked(false);
                    t_8.setChecked(false);
                    t_12.setChecked(false);
                    t_24.setChecked(false);
                    t_30.setChecked(false);
                }

            }
        });
        t_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t_2.isChecked()) {
                    t_1.setChecked(false);
                    t_4.setChecked(false);
                    t_8.setChecked(false);
                    t_12.setChecked(false);
                    t_24.setChecked(false);
                    t_30.setChecked(false);
                }

            }
        });
        t_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t_4.isChecked()) {
                    t_2.setChecked(false);
                    t_1.setChecked(false);
                    t_8.setChecked(false);
                    t_12.setChecked(false);
                    t_24.setChecked(false);
                    t_30.setChecked(false);
                }

            }
        });
        t_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t_8.isChecked()) {
                    t_2.setChecked(false);
                    t_4.setChecked(false);
                    t_1.setChecked(false);
                    t_12.setChecked(false);
                    t_24.setChecked(false);
                    t_30.setChecked(false);
                }
            }
        });
        t_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t_12.isChecked()) {
                    t_2.setChecked(false);
                    t_4.setChecked(false);
                    t_8.setChecked(false);
                    t_1.setChecked(false);
                    t_24.setChecked(false);
                    t_30.setChecked(false);
                }
            }
        });
        t_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t_24.isChecked()) {
                    t_2.setChecked(false);
                    t_4.setChecked(false);
                    t_8.setChecked(false);
                    t_12.setChecked(false);
                    t_1.setChecked(false);
                    t_30.setChecked(false);
                }
            }
        });
        t_30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t_30.isChecked()) {
                    t_2.setChecked(false);
                    t_4.setChecked(false);
                    t_8.setChecked(false);
                    t_12.setChecked(false);
                    t_24.setChecked(false);
                    t_1.setChecked(false);
                }
            }
        });


        saveTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = 60 * 60;
                if (t_1.isChecked())
                    time = time;
                else if (t_2.isChecked())
                    time = time * 2;
                else if (t_4.isChecked())
                    time = time * 4;
                else if (t_8.isChecked())
                    time = time * 8;
                else if (t_12.isChecked())
                    time = time * 12;
                else if (t_24.isChecked())
                    time = time * 24;
                else if (t_30.isChecked())
                    time = 60 * 30;
                SharedPreferences sharedPreferences = getSharedPreferences("TimeData", MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("time", time);
                editor.putBoolean("1",t_1.isChecked());
                editor.putBoolean("2",t_2.isChecked());
                editor.putBoolean("4",t_4.isChecked());
                editor.putBoolean("8",t_8.isChecked());
                editor.putBoolean("12",t_12.isChecked());
                editor.putBoolean("24",t_24.isChecked());
                editor.putBoolean("30",t_30.isChecked());
                editor.apply();

            }
        });

        both.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(both.isChecked()){
                    homeSrceen.setChecked(false);
                    lock.setChecked(false);
                }
            }
        });

        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lock.isChecked()){
                    homeSrceen.setChecked(false);
                    both.setChecked(false);
                }
            }
        });

        homeSrceen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(homeSrceen.isChecked()){
                    lock.setChecked(false);
                    both.setChecked(false);
                }
            }
        });

        setScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences sharedPreferences = getSharedPreferences("ScreenData", MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("both", both.isChecked());
                editor.putBoolean("lock", lock.isChecked());
                editor.putBoolean("home", homeSrceen.isChecked());
                editor.apply();

                if(homeSrceen.isChecked())
                    count=1;
                else if(lock.isChecked())
                    count=2;
                else
                    count=0;
            }
        });



        setAutoWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("TimeData", MODE_PRIVATE);
                Data data = new Data.Builder().putInt("count",count).build();
                PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest
                        .Builder(MyWorker.class,
//                        time,
                        sharedPreferences.getInt("time", time),
                        TimeUnit.SECONDS
                        ,30,TimeUnit.SECONDS
                )//Optional
//                        .setPeriodStartTime(System.currentTimeMillis()+10000,TimeUnit.SECONDS)
                        .setInputData(data)
                        .build();

                WorkManager.getInstance().enqueue(periodicWorkRequest);
            }
        });
    }


    public void addArrayList(ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            AutoWallpaperList.addInAutoWallpaper(arrayList.get(i));
        }
    }
}
