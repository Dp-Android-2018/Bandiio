package m.dp.onlineshop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder>  {
    private List<ProductModel> mData;
    Context context ;
    public ProductsAdapter(List<ProductModel> mData) {
        this.mData = mData;
    }
    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, final int position) {


        Picasso.with(context).load(mData.get(position).getImg()).into(holder.clothes_img);

        holder.likes.setText(mData.get(position).getNum_favourit());
        holder.price_after.setText(mData.get(position).getPrice());
        holder.price_before.setText(mData.get(position).getOld_price());

        holder.price_before.setPaintFlags(holder.price_before.getPaintFlags() |
                Paint.STRIKE_THRU_TEXT_FLAG);



        holder.clothes_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent product_details=new Intent(context , ProductDetailsScreen.class) ;
                product_details.putExtra("product_id",mData.get(position).getId());
                context.startActivity(product_details);
            }
        });

        if (mData.get(position).getFavorite().equals("1")){
            holder.react.setImageResource(R.drawable.ic_facelove);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView likes;
        TextView price_after;
        TextView price_before;
        ImageView clothes_img ;
        ImageView react ;

        public ViewHolder(View view) {
            super(view);
            likes = view.findViewById(R.id.likes_product);
            price_after = view.findViewById(R.id.price_after_product);
            price_before = view.findViewById(R.id.price_before_product);
            clothes_img= view.findViewById(R.id.img_product);
            react=view.findViewById(R.id.reaction_img_product) ;

        }
    }
}
