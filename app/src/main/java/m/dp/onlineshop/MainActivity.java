package m.dp.onlineshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sign_but=(Button) findViewById(R.id.SignUp_splash);
        Button login_but= (Button) findViewById(R.id.login_splash);

        sign_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, sign_Up.class);
                startActivity(intent);
            }
        });

        login_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login_Screen.class);
                startActivity(intent);
            }
        });


        if (PreferenceHelper.getInstance(this).islogin()){
            Intent intent = new Intent(MainActivity.this, activity_drawer.class);
            startActivity(intent);
            }

    }
}
