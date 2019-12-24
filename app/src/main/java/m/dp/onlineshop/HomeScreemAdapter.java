package m.dp.onlineshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeScreemAdapter extends RecyclerView.Adapter<HomeScreemAdapter.ViewHolder> {

    private List<HomeModel> mData;
    public Context context;
    ProgressDialog progressDialog ;

    public HomeScreemAdapter(List<HomeModel> mData) {
        this.mData = mData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_screen, parent, false);


        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        String imageurl= mData.get(position).getImg() ;


        Picasso.with(context).load(imageurl)
                .fit().centerInside().into(holder.myImageview);


        holder.myImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog=new ProgressDialog(context);

                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

              progressDialog.show();

                Intent detail_intent= new Intent(context , ProductScreen.class ) ;
                detail_intent.putExtra("category_id",mData.get(position).getId());
                context.startActivity(detail_intent);

                progressDialog.dismiss();

            }
        });

        holder.myTextView.setText(mData.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        ImageView myImageview ;
       // LinearLayout cardhome ;
        public ViewHolder(View view) {
            super(view);
            myTextView = view.findViewById(R.id.tv_stayelname_home);
            myImageview = view.findViewById(R.id.img_home);
//            cardhome=view.findViewById(R.id.card_home) ;
        }
    }

}
