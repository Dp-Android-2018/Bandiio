package m.dp.onlineshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Login_Screen extends AppCompatActivity {

  //  PreferenceHelper preferenceHelper ;
    EditText user_name ;
    EditText pass ;
    private ProgressDialog progress_login;

    int backButtonCount=0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__screen);

        Button signUp_butt=  findViewById(R.id.sign_ls);
        final ImageView login= findViewById(R.id.log_ls);
         user_name= findViewById(R.id.username_ls) ;
         pass=findViewById(R.id.password_ls) ;





         onBackPressed();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String username = user_name.getText().toString().trim();
                 String password = pass.getText().toString().trim();

                 if (login_validate(username,password)==true) {
                         loginUser(username, password);
                 }


            }
        });


       signUp_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Screen.this, sign_Up.class);
                startActivity(intent);
            }

        });

    }

    public void onBackPressed() {

        if(backButtonCount == 2)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            backButtonCount=0 ;
        }
        else if (backButtonCount==1)
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
        }

        backButtonCount++;
    }

    public boolean login_validate( String namestr ,String passwordstr ) {
        boolean valid = true;



        if (passwordstr.isEmpty() || passwordstr.length() < 6 ) {
            pass.setError("pass is very short");
            valid = false;
        } else {
            pass.setError(null);
        }


        if(namestr.isEmpty())
            user_name.setError("Enter Name");
        else
            user_name.setError(null);


        return valid;
    }


    private void loginUser(final String username , final String pass) {

        progress_login=new ProgressDialog(this);

       progress_login.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progress_login.show();



        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginInterface.LOGURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        LoginInterface api = retrofit.create(LoginInterface.class);

        Call<LoginResp> call = api.getUserLogin(username,pass);

        call.enqueue(new Callback<LoginResp>() {

            @Override
            public void onResponse(Call<LoginResp> call, Response<LoginResp> response) {


                if(response.isSuccessful()){

                    LoginResp resObj= response.body() ;

                    if(resObj.getStat().equals( "200")){

                        Toast.makeText(Login_Screen.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        PreferenceHelper.getInstance(Login_Screen.this).puttoken(resObj.getUser().getToken());

                        Toast.makeText(Login_Screen.this, PreferenceHelper.getInstance(Login_Screen.this).gettoken(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Login_Screen.this, activity_drawer.class);
                        startActivity(intent);

                        progress_login.dismiss();

                    }
                    else {
                        Toast.makeText(Login_Screen.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                        progress_login.dismiss();
                    }

                }
                else {
                    Toast.makeText(Login_Screen.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                    progress_login.dismiss();
                }

                }

            @Override
            public void onFailure(Call<LoginResp> call, Throwable t) {
                Toast.makeText(Login_Screen.this , "enter failure" ,Toast.LENGTH_SHORT).show();
                progress_login.dismiss();
            }
        });

    }

}
