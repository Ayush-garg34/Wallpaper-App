package garg.ayush.wallpaperapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.AbstractList;
import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {


    Context ctx;
    ArrayList<Category> categoryArrayList;

    public CategoryAdapter(Context ctx, ArrayList<Category> ArrayList) {
        this.ctx = ctx;
        this.categoryArrayList = ArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
        View inflatedView = li.inflate(R.layout.item_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflatedView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Category category = categoryArrayList.get(position);
        holder.textView.setText(category.name);
        Picasso.get().load(category.imageUrl).into(holder.imageView);

//        Log.e("TAG", "onBindViewHolder: "+category.imageUrl );
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(ctx, "Hi", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ctx, CategoryActivity.class);
                if (category.name == "ABSTRACT")
                    intent.putExtra("category", garg.ayush.wallpaperapp.AbstractList.getAbstractArrayList());
                else if(category.name == "TEXTURE")
                    intent.putExtra("category",TextureList.getTextureArraylist());
                else if(category.name == "NATURE")
                    intent.putExtra("category",NatureList.getNatureArrayList());
                else if(category.name == "FLOWERS")
                    intent.putExtra("category", FlowerList.getFlowerArrayList());
                else if(category.name == "QUOTES")
                    intent.putExtra("category", QuotesList.getQuotesArrayList());

                ctx.startActivity(intent);
//                Toast.makeText(ctx, "Hi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.category);
            imageView = itemView.findViewById(R.id.categoryImage);
        }
    }
}
