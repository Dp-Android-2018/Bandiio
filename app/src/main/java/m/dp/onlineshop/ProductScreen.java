package m.dp.onlineshop;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class ProductScreen extends AppCompatActivity {

    RecyclerView recyclerView ;

    List<ProductModel> recData ;

    private ProgressDialog progress_product;

    ProductsAdapter adapter ;

    private String sendtoken ;
    String category_id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);


        recyclerView = findViewById(R.id.products_recycler); //wait
        recData = new ArrayList<>();

        category_id= getIntent().getExtras().getString("category_id") ;


        sendtoken = PreferenceHelper.getInstance(ProductScreen.this).gettoken();

        parseproducts();


    }






    public void parseproducts(){


        progress_product=new ProgressDialog(this);

        progress_product.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progress_product.show();



        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .create();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeInterface.HomeURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        HomeInterface api = retrofit.create(HomeInterface.class);

        Call<ProductsResp> call = api.getproducts(category_id,sendtoken);
        call.enqueue(new Callback<ProductsResp>() {
            @Override
            public void onResponse(Call<ProductsResp> call, Response<ProductsResp> response) {
                ProductsResp productsresp= response.body() ;

                if (response.isSuccessful()) {
                    if (productsresp.getStat().equals("200") ){

                        Toast.makeText(ProductScreen.this, productsresp.getMsg(), Toast.LENGTH_SHORT).show();

                        recData.addAll( productsresp.getProductModel()) ;

                        adapter = new ProductsAdapter(recData);
                        recyclerView.setLayoutManager(new GridLayoutManager(ProductScreen.this,2));
                        recyclerView.setAdapter(adapter);
                        progress_product.dismiss();

                    }else {

                        Toast.makeText(ProductScreen.this, "Please Check Connection", Toast.LENGTH_SHORT).show();
                        progress_product.dismiss();

                    }
                }
            }

            @Override
            public void onFailure(Call<ProductsResp> call, Throwable t) {
                Toast.makeText(ProductScreen.this, "Please Check Connection", Toast.LENGTH_SHORT).show();
                progress_product.dismiss();
            }
        });



    }
}
