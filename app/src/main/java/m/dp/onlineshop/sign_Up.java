package m.dp.onlineshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

import static android.util.Patterns.EMAIL_ADDRESS;

public class sign_Up extends AppCompatActivity {

    private EditText Name ,email, password , birthday ,confirmed_pass;


    //ProgressBar simpleProgressBar_sign ;
    ImageButton signUp_butt ;

    private ProgressDialog progress_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        Name=findViewById(R.id.Name_ss) ;
        email=findViewById(R.id.E_mail_ss) ;
        password=findViewById(R.id.password_ss) ;
        birthday=findViewById(R.id.Birthday_ss) ;
        confirmed_pass=findViewById(R.id.confirmed_password_ss) ;

      //  simpleProgressBar_sign = (ProgressBar) findViewById(R.id.simpleProgressBar_sign);


        signUp_butt= findViewById(R.id.sign_ss);
        signUp_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final String namestr = Name.getText().toString().trim();
                final String emailstr = email.getText().toString().trim();
                final String birthdaystr = birthday.getText().toString().trim();
                final String passwordstr = password.getText().toString().trim();
                final String confirmedpasswordstr = confirmed_pass.getText().toString().trim();


                if (sign_validate(namestr, emailstr , birthdaystr , passwordstr , confirmedpasswordstr)==true)
                {
                    registerMe(namestr, emailstr , birthdaystr , passwordstr , confirmedpasswordstr);

                }

            }


        });
    }

    public boolean sign_validate( String namestr , String emailstr ,
                                  String birthdaystr ,String passwordstr ,
                                  String confirmedpasswordstr ) {
        boolean valid = true;


        if (emailstr.isEmpty() || !EMAIL_ADDRESS.matcher(emailstr).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (passwordstr.isEmpty() || passwordstr.length() < 6 ) {
            password.setError("pass is very short");
            valid = false;
        } else {
            password.setError(null);
        }

        if(confirmedpasswordstr.isEmpty() || !confirmedpasswordstr.equals(passwordstr) ){
            confirmed_pass.setError("password not match");
            valid = false;
        } else {
            confirmed_pass.setError(null);
        }

        if(namestr.isEmpty())
            Name.setError("Enter Name");
        else
            Name.setError(null);

        if(birthdaystr.isEmpty())
            birthday.setError("YYYY/MM/D");
        else
            birthday.setError(null);

        return valid;
    }

    private void registerMe(final String namestr , String emailstr ,
                            String birthdaystr , String passwordstr,
                            String confirmedpasswordstr) {
        progress_signup=new ProgressDialog(this);

        progress_signup.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // progress_login.setIndeterminate(true);
        // progress_login.setProgress(0);
        progress_signup.show();

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RegisterInterface.REGIURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RegisterInterface api = retrofit.create(RegisterInterface.class);

        Call<RegResponse> call = api.getUserRegi(namestr,emailstr,birthdaystr,passwordstr,confirmedpasswordstr);

        call.enqueue(new Callback<RegResponse>() {
            @Override

            public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {

                if (response.isSuccessful()) {

                        RegResponse regresponse = response.body();
                    Toast.makeText(sign_Up.this, "hi", Toast.LENGTH_SHORT).show();
                    Toast.makeText(sign_Up.this, regresponse.getMsg(), Toast.LENGTH_SHORT).show();

                        if (regresponse.getStat().equals("200")){
                            Toast.makeText(sign_Up.this,"Registerr Successfully!", Toast.LENGTH_SHORT).show();
                            PreferenceHelper.getInstance(sign_Up.this).puttoken(regresponse.getUserobj().getToken());
                            Intent intent = new Intent(sign_Up.this,activity_drawer.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                             startActivity(intent);
                            sign_Up.this.finish();

                            progress_signup.dismiss();
                        }
                        else
                            progress_signup.dismiss();
                    }

                }
            @Override
            public void onFailure(Call<RegResponse> call, Throwable t) {

                Toast.makeText(sign_Up.this, "Please Check Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
