package m.dp.onlineshop;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class activity_drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView ;
    List<HomeModel> recData ;


    public SliderLayout mDemoSlider;
    ArrayList<SliderModel> slider ;

    int backButtonCount=0 ;

    public  HomeScreemAdapter adapter;
    private ProgressDialog progress_home;



    public HashMap<String,String> file_maps_slider ;

    public HashMap<String,String> file_maps_recycler ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        recyclerView = findViewById(R.id.stayel_recycler);
        //HomeScreemAdapter adapter = new HomeScreemAdapter(recData);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        //recyclerView.setAdapter(adapter);
        mDemoSlider = findViewById(R.id.slider_home);

        slider=new ArrayList<>() ;
        recData=new ArrayList<>() ;



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        parseslider();
        parserecycler();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if(backButtonCount == 2)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            backButtonCount=0 ;
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

          if (id == R.id.nav_gallery) {
            Intent basket_intent=new Intent(this , BasketScreen.class) ;
            startActivity(basket_intent);

        } else if (id == R.id.contact_us) {
            Intent intent= new Intent(activity_drawer.this , SocialMediaScreen.class) ;
            startActivity(intent);

        } else if (id == R.id.about_us) {
            Intent intent= new Intent(activity_drawer.this , AboutUsScreen.class) ;
            startActivity(intent);

        }else if (id== R.id.logout){
              showDialog();
          }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

        Call<HomeResp> call = api.getHome();

        call.enqueue(new Callback<HomeResp>() {
            @Override
            public void onResponse(Call<HomeResp> call, Response<HomeResp> response) {

                if (response.isSuccessful())
                {
                    HomeResp homeresp= response.body() ;

                    if (homeresp.getStat().equals("200") ){
                         slider.addAll(homeresp.getSliderModel()) ;

                        file_maps_slider  = new HashMap<>();
                        for (int i=0;i<slider.size();i++){
                            file_maps_slider.put(slider.get(i).getText(),slider.get(i).getImg());
                        }
                        prepreslider();
                    }
                }
            }

            @Override
            public void onFailure(Call<HomeResp> call, Throwable t) {
                Toast.makeText(activity_drawer.this, "failure slider resp", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void prepreslider(){
        for(String name : file_maps_slider.keySet()){
            TextSliderView textSliderView = new TextSliderView(activity_drawer.this);
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





    public void parserecycler(){

        progress_home=new ProgressDialog(this);

        progress_home.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progress_home.show();

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .create();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeInterface.HomeURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        HomeInterface api = retrofit.create(HomeInterface.class);

        Call<HomeResp> call = api.getHome();

        call.enqueue(new Callback<HomeResp>() {
            @Override
            public void onResponse(Call<HomeResp> call, Response<HomeResp> response) {

                if (response.isSuccessful())
                {
                    HomeResp homeresp= response.body() ;

                    if (homeresp.getStat().equals("200") ){


                        recData.addAll(homeresp.getHomeModel()) ;

                        file_maps_recycler  = new HashMap<>();
                        for (int i=0;i<recData.size();i++){
                            file_maps_recycler.put(recData.get(i).getText(),recData.get(i).getImg());

                            adapter=new HomeScreemAdapter(recData) ;
                            recyclerView.setLayoutManager(new GridLayoutManager(activity_drawer.this ,2));
                            recyclerView.setAdapter(adapter);

                            progress_home.dismiss();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<HomeResp> call, Throwable t) {
                Toast.makeText(activity_drawer.this, "failure slider resp", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setMessage("Are you sure You want to logout from account now ");


        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {

                        PreferenceHelper.getInstance(activity_drawer.this).clear();

                        Intent home_login=new Intent(activity_drawer.this,Login_Screen.class) ;
                        startActivity(home_login);
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


}