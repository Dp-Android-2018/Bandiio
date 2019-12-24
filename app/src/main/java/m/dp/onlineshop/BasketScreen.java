package m.dp.onlineshop;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BasketScreen extends AppCompatActivity {
    RecyclerView recyclerView ;
    List<BasketItemModel> recData ;

    public  ArrayList<String> product_id ;
    public  ArrayList<String> product_count ;

    ArrayList<CheckoutParmeterList> productdata ;
    private String sendtoken ;
    TextView numof_selected_items ;
    TextView subtotal ;

    Button checkout_butt ;

    BasketAdapter adapter ;

    private ProgressDialog progress_Basket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket_screen);



        recData=new ArrayList<>() ;
        recyclerView = findViewById(R.id.basket_recycler);
        numof_selected_items=findViewById(R.id.numof_selected_items) ;
        subtotal=findViewById(R.id.basket_totalprice) ;
        checkout_butt=findViewById(R.id.checkout_butt) ;

        productdata=new ArrayList<>() ;
        product_count=new ArrayList<>() ;
        product_id=new ArrayList<>() ;


        sendtoken=PreferenceHelper.getInstance(this).gettoken() ;


         adapter = new BasketAdapter(recData);
         recyclerView.setLayoutManager(new LinearLayoutManager(BasketScreen.this,
                RecyclerView.VERTICAL, false));
         recyclerView.setAdapter(adapter);

        parsebasket();


        checkout_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsecheckout();
            }
        });

    }


    public void parsebasket(){

        progress_Basket=new ProgressDialog(this);

        progress_Basket.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progress_Basket.show();

        Gson gson= new GsonBuilder()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .create() ;

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(HomeInterface.HomeURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build() ;

        HomeInterface api= retrofit.create(HomeInterface.class) ;
        Call<BasketResp> call= api.getBasket(sendtoken) ;
        call.enqueue(new Callback<BasketResp>() {
            @Override
            public void onResponse(Call<BasketResp> call, Response<BasketResp> response) {
                if(response.isSuccessful()) {
                    if (response.body().getStat().equals("200")) {
                        recData.addAll(response.body().getBasketItemModel());
                        progress_Basket.dismiss();
                    }

                    else {
                        Toast.makeText(BasketScreen.this, "Please Login Again", Toast.LENGTH_SHORT).show();
                        progress_Basket.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BasketResp> call, Throwable t) {
                Toast.makeText(BasketScreen.this, "Check Connection", Toast.LENGTH_SHORT).show();
                progress_Basket.dismiss();
            }
        });

    }



    public void parsecheckout() {

        progress_Basket = new ProgressDialog(this);

        progress_Basket.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progress_Basket.show();

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeInterface.HomeURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        HomeInterface api = retrofit.create(HomeInterface.class);


        for (int i = 0; i < recData.size(); i++) {

            if (recData.get(i).get_Is_checked() == true) {
                product_id.add(recData.get(i).getId());
                product_count.add(recData.get(i).getCount());
            }
        }

        if (product_id.size() == 0) {
            Toast.makeText(this, "Choose Your products,First", Toast.LENGTH_SHORT).show();
            progress_Basket.dismiss();
        }

        else {
            Call<CheckoutRespModel> call = api.checkout(sendtoken, product_id, product_count, subtotal.getText().toString());
            call.enqueue(new Callback<CheckoutRespModel>() {
                @Override
                public void onResponse(Call<CheckoutRespModel> call, Response<CheckoutRespModel> response) {
                    if (response.isSuccessful()) {

                        if (response.body().getStat().equals("200")) {
                            Toast.makeText(BasketScreen.this, "Order Is Sent ,Thanks", Toast.LENGTH_SHORT).show();
                            progress_Basket.dismiss();
                        } else {
                            Toast.makeText(BasketScreen.this, "Please Login Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<CheckoutRespModel> call, Throwable t) {
                    Toast.makeText(BasketScreen.this, "Please Check Connection", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}

