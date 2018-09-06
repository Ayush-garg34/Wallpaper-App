package garg.ayush.wallpaperapp;

import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
//        implements ValueEventListener
{

    ArrayList<Category> categories = new ArrayList<>();


//    DatabaseReference rootRef;
//    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> historyArrayList = new ArrayList<>();
        historyArrayList = getArrayList("history");
        if (historyArrayList != null)
            for (int i = 0; i < historyArrayList.size(); i++) {
//                Log.e("TAG", "onCreate: " + historyArrayList.get(i));
                HistoryList.addInHistory(historyArrayList.get(i));
            }
        ArrayList<String> auto = new ArrayList<>();
        auto=getArrayList("auto");
        if(auto!=null) {
            for (int i = 0; i < auto.size(); i++) {
                AutoWallpaperList.addInAutoWallpaper(auto.get(i));
            }
            Log.e(" ----    TAG", "onCreate: " + auto.size());
        }

//        textView=findViewById(R.id.text);
//        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

//        rootRef = firebaseDatabase.getReference();

//        rootRef.addListenerForSingleValueEvent(this);

//        rootRef.addValueEventListener(this);
        categories.add(new Category("ABSTRACT", "https://wallpapers.pictures/media/geometric-shapes-wallpaper-background-744x1392.jpg"));
        categories.add(new Category("FLOWERS", "https://free4kwallpaper.com/wp-content/uploads/2016/01/Pink-Flowers-4K-Spring-Wallpaper.jpg"));
        categories.add(new Category("NATURE", "https://i.imgur.com/P0s8LK4.jpg"));
        categories.add(new Category("QUOTES", "https://i.pinimg.com/564x/c1/9b/16/c19b167857e45022463169c209b33b15.jpg"));
        categories.add(new Category("TEXTURE", "https://wallpapershome.com/images/pages/pic_v/6199.jpg"));


        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new CategoryAdapter(this, categories));
    }

//    @Override
//    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//        Toast.makeText(this, "HIIIIIII", Toast.LENGTH_SHORT).show();
//
//        Log.e("TAG", "onDataChange: " + dataSnapshot.getChildren());
//
//        Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
//
//
//
//        for (DataSnapshot ds : dataSnapshots) {
//            String url = ds.getValue(String.class);
////          notesArrayList.add(currentNote);
////            textView.setText(url);
//            Log.e("TA--", "onDataChange: " + url);
//
//
////
//        }
//
//        Picasso.get()
//                .load("https://wallpapers.pictures/media/geometric-shapes-wallpaper-background-744x1392.jpg")
////                .load("https://opensource.google.com/assets/static/images/home/blog/blog_image_1.jpg")
////                .load("http://wallpaperswide.com/download/the_amazing_spider_man_2012_film-wallpaper-1600x900.jpg")
//                .into(new com.squareup.picasso.Target() {
//                    @Override
//                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                        WallpaperManager wallpaperManager = WallpaperManager.getInstance(MainActivity.this);
//                        try {
//                            wallpaperManager.setBitmap(bitmap);
//                        } catch (IOException ex) {
//                            ex.printStackTrace();
//                        }
//                        Log.d("TAG", "onBitmapLoaded: ");
//                        Toast.makeText(MainActivity.this, "Wallpaper Changed", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//                    }
//
//                    @Override
//                    public void onPrepareLoad(Drawable placeHolderDrawable) {
//                        Log.d("TAG", "Prepare Load");
//                        Toast.makeText(MainActivity.this, "Downloading image", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//    @Override
//    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ArrayList<String> historyArrayList = HistoryList.getHistoryArrayList();
        ArrayList<String> autoWallpaper = AutoWallpaperList.getAutoWallpaper();
        saveArrayList(autoWallpaper,"auto");
        saveArrayList(historyArrayList, "history");
    }


    public void saveArrayList(ArrayList<String> list, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public ArrayList<String> getArrayList(String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
