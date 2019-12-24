package m.dp.onlineshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SocialMediaScreen extends AppCompatActivity {

    EditText complain_txt ;
    private String sendtoken ;
    Button send_butt ;

    Button twitter_but ;
    Button insta_but ;

   public boolean msg_is_sent=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media_screen);

        complain_txt=findViewById(R.id.yourmessage) ;
        sendtoken=PreferenceHelper.getInstance(this).gettoken() ;

        final ProgressBar simpleProgressBarsocial = (ProgressBar) findViewById(R.id.simpleProgressBar_social);

        send_butt=findViewById(R.id.sendbutton) ;

        twitter_but=findViewById(R.id.twitter) ;
        insta_but=findViewById(R.id.instagram) ;

        send_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                send_butt.setVisibility(View.INVISIBLE);
                simpleProgressBarsocial.setVisibility(View.VISIBLE);

                parsecomplain();

                Toast.makeText(SocialMediaScreen.this, msg_is_sent+"", Toast.LENGTH_SHORT).show();

                if (msg_is_sent == true) {

                    Intent home_intent = new Intent(SocialMediaScreen.this, activity_drawer.class);
                    startActivity(home_intent);
                }

            }
        });

        twitter_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/login?lang=ar"));
                startActivity(browserIntent);
            }
        });

        insta_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/"));
                startActivity(browserIntent);
            }
        });


    }


   public void parsecomplain(){

       Gson gson = new GsonBuilder()
               .serializeNulls()
               .setDateFormat(DateFormat.LONG)
               .create();



       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(HomeInterface.HomeURL)
               .addConverterFactory(GsonConverterFactory.create(gson))
               .build();

       HomeInterface api = retrofit.create(HomeInterface.class);

       Call<SocialModel> call = api.complain( sendtoken , complain_txt.getText().toString());
       call.enqueue(new Callback<SocialModel>() {
           @Override
           public void onResponse(Call<SocialModel> call, Response<SocialModel> response) {

               if (response.isSuccessful()){
                   //Toast.makeText(SocialMediaScreen.this ,response.body().getMsg(), Toast.LENGTH_SHORT).show();
                   if (response.body().getStat().equals("200")){
                       Toast.makeText(SocialMediaScreen.this, "Thanks", Toast.LENGTH_SHORT).show();


                   }
                   else
                       Toast.makeText(SocialMediaScreen.this ,"Please Enter Your Complain", Toast.LENGTH_SHORT).show();
                   msg_is_sent =false ;
               }
           }

           @Override
           public void onFailure(Call<SocialModel> call, Throwable t) {
               Toast.makeText(SocialMediaScreen.this ,"Please Check Connection", Toast.LENGTH_SHORT).show();
           }
       });

   }
   }

