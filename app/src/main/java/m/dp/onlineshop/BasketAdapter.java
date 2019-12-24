package m.dp.onlineshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {
    private List<BasketItemModel> mData;
    private Context context ;

    public   int numofchecked =0 ;

    public Double totalprice = 0.0 ;


    public BasketAdapter(List<BasketItemModel> mData) {
        this.mData = mData;
    }
    @NonNull
    @Override
    public BasketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.basket_iteam, parent, false);

        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BasketAdapter.ViewHolder holder, final int position) {

        holder.stayel_name.setText(mData.get(position).getStayel_name());
        holder.size.setText(mData.get(position).getSize());
        holder.price.setText(mData.get(position).getPrice());
        Picasso.with(context).load(mData.get(position).getImg())
                .into(holder.img);


        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Integer.parseInt(holder.counter.getText().toString())<=Integer.parseInt(mData.get(position).getMaxcount())){
                    holder.counter.setText(String.valueOf(Integer.parseInt(holder.counter.getText().toString()) + 1));
                }
                else {
                    Toast.makeText(context, "thi max count for this product", Toast.LENGTH_SHORT).show();
                }


                if (Integer.parseInt(holder.counter.getText().toString())==1){
                    numofchecked++ ;
                    mData.get(position).setIs_checked(true);
                }

                     ((BasketScreen)context).numof_selected_items.setText(String.valueOf(numofchecked));

                   holder.price.setText(String.valueOf(Double.parseDouble(holder.counter.getText().toString()) *
                            Double.parseDouble(mData.get(position).getPrice())));


                    totalprice += Double.parseDouble(mData.get(position).getPrice());

                    mData.get(position).setCount(holder.counter.getText().toString());

                    ((BasketScreen) context).subtotal.setText(String.valueOf(totalprice));

            }
        });





        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( Integer.parseInt(holder.counter.getText().toString())>0){
                    holder.counter.setText(String.valueOf(Integer.parseInt(holder.counter.getText().toString()) - 1));

                    holder.price.setText(String.valueOf(Double.parseDouble(holder.counter.getText().toString()) *
                            Double.parseDouble(mData.get(position).getPrice())));


                    totalprice -= Double.parseDouble(mData.get(position).getPrice());

                    ((BasketScreen) context).subtotal.setText(String.valueOf(totalprice));

                    mData.get(position).setCount(holder.counter.getText().toString());

                }

                if (Integer.parseInt(holder.counter.getText().toString())==0){
                    if (numofchecked>0)
                     numofchecked-- ;

                    mData.get(position).setIs_checked(false);

                }


                ((BasketScreen)context).numof_selected_items.setText(String.valueOf(numofchecked));

            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stayel_name ;
        TextView size ;
        TextView price ;
        ImageView img ;

        ImageView plus ;
        ImageView minus ;
        TextView counter ;
        ImageView basket_love ;

        public ViewHolder(View view) {
            super(view);
            stayel_name = view.findViewById(R.id.stayel_name_txt);
            size = view.findViewById(R.id.style_size);
            price = view.findViewById(R.id.style_price);
            img = view.findViewById(R.id.basket_img);

            plus=view.findViewById(R.id.basket_plus) ;
            minus=view.findViewById(R.id.basket_minus) ;
            counter=view.findViewById(R.id.basket_counter);
            basket_love=view.findViewById(R.id.basket_love) ;
        }
    }
}
