package m.dp.onlineshop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    private List<ProductsIteam> mData;
    Context context ;
    public FavouriteAdapter(List<ProductsIteam> mData) {
        this.mData = mData;
    }
    @NonNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favourite_iteam, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.ViewHolder holder, int position) {

        holder.clothes_img.setImageResource(mData.get(position).image);
        holder.counter.setText(String.valueOf(mData.get(position).counter));
        holder.favourite_iteam_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context , ProductDetailsScreen.class) ;
                context.startActivity(intent);
            }
        });
    }

    @Override


    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView counter;
        TextView price_after;
        TextView price_before;
        ImageView clothes_img ;
        ImageView react_img ;
        LinearLayout favourite_iteam_layout ;
        public ViewHolder(View view) {
            super(view);
            counter = view.findViewById(R.id.counter2);
            price_after = view.findViewById(R.id.price_after2);
            price_before = view.findViewById(R.id.price_before2);
            clothes_img= view.findViewById(R.id.clothes_img2);
            react_img = view.findViewById(R.id.reaction_img2);
            favourite_iteam_layout=view.findViewById(R.id.favourite_iteam_layout);


        }
    }
}
