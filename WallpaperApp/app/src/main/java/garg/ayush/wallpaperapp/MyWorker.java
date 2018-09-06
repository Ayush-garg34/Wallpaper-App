package garg.ayush.wallpaperapp;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import androidx.work.Worker;

class MyWorker extends Worker{
    @NonNull
    @Override
    public Result doWork() {
        int count = getInputData().getInt("count",0);

        Intent intent=new Intent(getApplicationContext(),MyWorkerActivity.class);
        intent.putExtra("count",count);
        getApplicationContext().startActivity(intent);

        try {
            return Result.SUCCESS;
        }catch (Exception e){
            return Result.RETRY;
        }
    }
}
