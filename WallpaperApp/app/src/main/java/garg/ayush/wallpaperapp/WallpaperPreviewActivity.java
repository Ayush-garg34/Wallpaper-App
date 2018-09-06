package garg.ayush.wallpaperapp;

import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

public class WallpaperPreviewActivity extends AppCompatActivity {

    String walllpaper;
    Button cancel, set;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_preview);

        imageView = findViewById(R.id.wallpaperPreview);

        cancel = findViewById(R.id.cancel);
        set = findViewById(R.id.set);
        Intent intent = getIntent();

        walllpaper = intent.getStringExtra("wallpaper");
        Picasso.get().load(walllpaper).into(imageView);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final View DialogView = LayoutInflater.from(this).inflate(R.layout.dialog, null, true);
        final AlertDialog CustomDialog = new AlertDialog.Builder(this)
                .setTitle("Set Screen")
                .setView(DialogView)
                .create();

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HistoryList.addInHistory(walllpaper);
                CustomDialog.show();
            }
        });

    }

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

    public void lock(View view) {
        Toast.makeText(this, "Preparing", Toast.LENGTH_SHORT).show();
        Picasso.get()
                .load(walllpaper)
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
                        Toast.makeText(getBaseContext(), "Lock Screen Wallpaper Changed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        Log.d("TAG", "FAILED");
                        Toast.makeText(getBaseContext(), "Loading image failed", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onPrepareLoad(final Drawable placeHolderDrawable) {
                        Log.d("TAG", "Prepare Load");
//                        Toast.makeText(getBaseContext(), "Downloading image", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void home(View view) {
        Toast.makeText(this, "Preparing", Toast.LENGTH_SHORT).show();
        Picasso.get()
                .load(walllpaper)
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
                        Toast.makeText(getBaseContext(), "Home Screen Wallpaper Changed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        Log.d("TAG", "FAILED");
                        Toast.makeText(getBaseContext(), "Loading image failed", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onPrepareLoad(final Drawable placeHolderDrawable) {
                        Log.d("TAG", "Prepare Load");
//                        Toast.makeText(getBaseContext(), "Downloading image", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void both(View view) {
        Toast.makeText(this, "Preparing", Toast.LENGTH_SHORT).show();
        Picasso.get()
                .load(walllpaper)
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
                        Toast.makeText(getBaseContext(), "Both Wallpapers Changed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        Log.d("TAG", "FAILED");
                        Toast.makeText(getBaseContext(), "Loading image failed", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onPrepareLoad(final Drawable placeHolderDrawable) {
                        Log.d("TAG", "Prepare Load");
//                        Toast.makeText(getBaseContext(), "Downloading image", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}

