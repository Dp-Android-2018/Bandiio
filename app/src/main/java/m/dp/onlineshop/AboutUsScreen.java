package m.dp.onlineshop;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AboutUsScreen extends AppCompatActivity {

    TextView aboutus_txt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_screen);

        aboutus_txt=findViewById(R.id.aboutus_txt) ;
        parseaboutus();
    }

   public void parseaboutus(){
       Gson gson = new GsonBuilder()
               .serializeNulls()
               .setDateFormat(DateFormat.LONG)
               .create();



       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(HomeInterface.HomeURL)
               .addConverterFactory(GsonConverterFactory.create(gson))
               .build();

       HomeInterface api = retrofit.create(HomeInterface.class);

       Call<AboutusModel> call = api.getaboutus();
       call.enqueue(new Callback<AboutusModel>() {
           @Override
           public void onResponse(Call<AboutusModel> call, Response<AboutusModel> response) {
               if (response.isSuccessful()){
                   if (response.body().getStat().equals("200")){
                       aboutus_txt.setText(response.body().getAboutus());
                   }
               }
           }

           @Override
           public void onFailure(Call<AboutusModel> call, Throwable t) {

           }
       });

   }
}
