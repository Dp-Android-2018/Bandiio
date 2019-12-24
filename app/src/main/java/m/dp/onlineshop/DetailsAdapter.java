package m.dp.onlineshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
    private List<SizeModel> mData;
    private Context context ;
    public DetailsAdapter(List<SizeModel> mData) {
        this.mData = mData;
    }

    public  static String selected_size ;
    @NonNull
    @Override
    public DetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_detail_iteam, parent, false);

        context=parent.getContext() ;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetailsAdapter.ViewHolder holder, final int position) {

        holder.size.setText(mData.get(position).getSize());

        holder.size_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selected_size=mData.get(position).getSize() ;
                holder.size_card.setCardBackgroundColor(R.drawable.ic_facelove);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView size ;
        CardView size_card ;
        public ViewHolder(View view) {
            super(view);
            size = view.findViewById(R.id.size);
            size_card=view.findViewById(R.id.size_card) ;
        }
    }
}
