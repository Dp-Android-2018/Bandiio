package m.dp.onlineshop;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailsScreen extends AppCompatActivity {

    RecyclerView recyclerView ;
    ArrayList<SizeModel> recData ;
    RatingBar ratingBar;



   public static boolean love_ok=false ;

    private SliderLayout mDemoSlider;
    ArrayList<ImagesDetailResp>  imgslider ;

    public HashMap<String,String> file_maps_slider ;

    public HashMap<String,String> file_maps_recycler ;

    private ProgressDialog progress_details;

    private  String sendtoken ;

    public static String product_id ;

    TextView product_price ;
    TextView product_stayel_name ;
    TextView product_offer ;
    TextView product_old_price ;
    TextView num_of_rate ;

    Button add_but ;
    TextView counter ;
    Button minus ;
    Button plus ;
    ImageView love_details ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_screen);

        mDemoSlider = findViewById(R.id.slider);
        recyclerView = findViewById(R.id.size_Recycler);
        counter=findViewById(R.id.number_of_pices) ;
        minus=findViewById(R.id.minus);
        plus=findViewById(R.id.plus);
        love_details=findViewById(R.id.love_details) ;
        ratingBar =  findViewById(R.id.rating_bar);


//        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();


        imgslider=new ArrayList<>() ;
        recData=new ArrayList<>() ;

        product_price=findViewById(R.id.price) ;
        product_stayel_name=findViewById(R.id.product_stayel_name) ;
        product_offer=findViewById(R.id.product_offer) ;
        product_old_price=findViewById(R.id.product_old_price) ;
        add_but=findViewById(R.id.add_button) ;
        num_of_rate=findViewById(R.id.num_of_favourite) ;


        sendtoken= PreferenceHelper.getInstance(this).gettoken() ;
        product_id=getIntent().getExtras().getString("product_id") ;


        love_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (love_ok == false) {
                    love_details.setImageResource(R.drawable.ic_facelove);
                    love_ok =true ;
                    parselike() ;

                }else {
                    love_details.setImageResource(R.drawable.ic_like);
                    love_ok=false ;
                    parselike();
                }
            }
        });

        add_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();
            }
        });



       parseslider();




        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(counter.getText().toString())>1)
                counter.setText(String.valueOf(Integer.parseInt(counter.getText().toString()) - 1)) ;
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.setText(String.valueOf(Integer.parseInt(counter.getText().toString()) +1)) ;
            }
        });



    }

    public void parseslider(){
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .create();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeInterface.HomeURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        HomeInterface api = retrofit.create(HomeInterface.class);

        Call<DetailResponse> call = api.Getdetails( sendtoken, product_id);

        call.enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {

                if (response.isSuccessful())
                {
                    DetailResponse detailResponse= response.body() ;

                    if (detailResponse.getStat().equals("200") ){

                        imgslider.addAll(detailResponse.getProductObjResps().getImagesDetailResps());
                        file_maps_slider=new HashMap<>() ;
                        for (int i=0;i<imgslider.size();i++){
                            file_maps_slider.put("",imgslider.get(i).getImg());
                        }

                        recData.addAll(detailResponse.getProductObjResps().getSizemodel());
                        file_maps_recycler=new HashMap<>() ;
                        for (int i=0;i<recData.size();i++){
                            file_maps_recycler.put("",recData.get(i).getSize());
                        }

                        DetailsAdapter adapter = new DetailsAdapter(recData);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ProductDetailsScreen.this,
                                LinearLayoutManager.HORIZONTAL, false));
                        recyclerView.setAdapter(adapter);

                        prepreslider();


                       if (response.body().getProductObjResps().getRate()==null){
                           ratingBar.setRating((float) 0.0);

                       }else
                        ratingBar.setRating(Float.parseFloat(response.body().getProductObjResps().getRate()));





                        product_stayel_name.setText(detailResponse.getProductObjResps().getText());
                        product_price.setText(detailResponse.getProductObjResps().getPrice());
                        num_of_rate.setText(detailResponse.getProductObjResps().getNum_rate());


                        if (!detailResponse.getProductObjResps().getOffer().equals("0") )
                        {
                            product_old_price.setText(detailResponse.getProductObjResps().getOld_price());
                            product_old_price.setPaintFlags(product_old_price.getPaintFlags() |
                                    Paint.STRIKE_THRU_TEXT_FLAG);
                            product_offer.setText(detailResponse.getProductObjResps().getOffer());
                        }

                    }


                }

            }
            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                Toast.makeText(ProductDetailsScreen.this, "failure slider resp", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void prepreslider(){
        for(String name : file_maps_slider.keySet()){
            TextSliderView textSliderView = new TextSliderView(ProductDetailsScreen.this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps_slider.get(name))
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {

                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
    }


    public void parsBag() {


        progress_details=new ProgressDialog(this);

        progress_details.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progress_details.show();


        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .create();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeInterface.HomeURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        HomeInterface api = retrofit.create(HomeInterface.class);


        Call<BagModelResp> call = api.Bag( sendtoken, product_id , DetailsAdapter.selected_size
                ,counter.getText().toString() );


        call.enqueue(new Callback<BagModelResp>() {
            @Override
            public void onResponse(Call<BagModelResp> call, Response<BagModelResp> response) {

                if (response.isSuccessful()){



                    if (response.body().getStat().equals("200")){

                        Intent home_intent=new Intent(ProductDetailsScreen.this , activity_drawer.class) ;
                        startActivity(home_intent);
                        Toast.makeText(ProductDetailsScreen.this, "Product added successfully!", Toast.LENGTH_SHORT).show();
                        progress_details.dismiss();
                    }
                    else {
                        Toast.makeText(ProductDetailsScreen.this, "Please Select Size", Toast.LENGTH_SHORT).show();
                        progress_details.dismiss();
                    }


                }

            }

            @Override
            public void onFailure(Call<BagModelResp> call, Throwable t) {

                Toast.makeText(ProductDetailsScreen.this, "Please Check Connection", Toast.LENGTH_SHORT).show();
                progress_details.dismiss();
            }
        });
    }

    public void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setMessage("Are you sure You wanted to Buy Product with size " + DetailsAdapter.selected_size +
                    "  and " + counter.getText().toString() + " pices");


        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        parsBag();
                    }
                });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public void parselike(){

        progress_details=new ProgressDialog(this);

        progress_details.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progress_details.show();

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .create();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeInterface.HomeURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        HomeInterface api = retrofit.create(HomeInterface.class);

        Call<FavouriteModel>call=api.favourite(sendtoken,product_id) ;
        call.enqueue(new Callback<FavouriteModel>() {
            @Override
            public void onResponse(Call<FavouriteModel> call, Response<FavouriteModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStat().equals("200")) {
                        Toast.makeText(ProductDetailsScreen.this, "thanks for like", Toast.LENGTH_SHORT).show();
                        progress_details.dismiss();
                    }

                    else {
                        Toast.makeText(ProductDetailsScreen.this, "Please Check Connection", Toast.LENGTH_SHORT).show();
                        progress_details.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<FavouriteModel> call, Throwable t) {

                Toast.makeText(ProductDetailsScreen.this, "Please Check Connection", Toast.LENGTH_SHORT).show();
                progress_details.dismiss();
            }
        });


    }

}
