package garg.ayush.wallpaperapp;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MyWorkerActivity extends AppCompatActivity {

    int count=0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Intent intent=getIntent();
        count=intent.getIntExtra("count",0);


        ArrayList<String> autoWallpaper = AutoWallpaperList.getAutoWallpaper();
        int index = new Random()
                .nextInt(autoWallpaper.size());
//                .nextInt(autoWallpaper.size());

        String randomWallpaper=autoWallpaper.get(index);
        Log.e("TAG", "doWork:-------------------------------- "+autoWallpaper.size());
//        Toast.makeText(getApplicationContext(),"Hi", Toast.LENGTH_SHORT).show();



        final MainActivity mainActivity =new MainActivity();
        if(count==2) {
            Picasso.get()
                    .load(randomWallpaper)
                    .into(new Target() {

                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            WallpaperManager wallpaperManager = WallpaperManager.getInstance(getBaseContext());
                            try {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK);
                                } else
                                    wallpaperManager.setBitmap(bitmap);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            Log.d("TAG", "onBitmapLoaded: ");
//                            Toast.makeText(getBaseContext(), "Lock Screen Wallpaper Changed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        }


                        @Override
                        public void onPrepareLoad(final Drawable placeHolderDrawable) {
                        }
                    });
        }else if(count==1){
            Thread thread =new Thread();

            Picasso.get()
                    .load(randomWallpaper)
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            WallpaperManager wallpaperManager = WallpaperManager.getInstance(getBaseContext());
                            try {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM);
                                } else
                                    wallpaperManager.setBitmap(bitmap);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            Log.d("TAG", "onBitmapLoaded: ");
//                            Toast.makeText(getBaseContext(), "Home Screen Wallpaper Changed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                        }


                        @Override
                        public void onPrepareLoad(final Drawable placeHolderDrawable) {
                        }
                    });
        }else {
            Picasso.get()
                    .load(randomWallpaper)
                    .into(new Target() {

                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            WallpaperManager wallpaperManager = WallpaperManager.getInstance(getBaseContext());
                            try {
                                wallpaperManager.setBitmap(bitmap);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            Log.d("TAG", "onBitmapLoaded: ");
//                            Toast.makeText(getBaseContext(), "Both Wallpapers Changed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        }


                        @Override
                        public void onPrepareLoad(final Drawable placeHolderDrawable) {
                        }
                    });
        }

        finish();


    }
}
