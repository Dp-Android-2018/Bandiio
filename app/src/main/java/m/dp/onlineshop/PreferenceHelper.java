package m.dp.onlineshop;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class PreferenceHelper {

    public static PreferenceHelper preferenceHelper ;

    private SharedPreferences sharedPreferences;
    private Context context;

    public static PreferenceHelper  getInstance(Context context) {
        if (preferenceHelper == null) {
            preferenceHelper = new PreferenceHelper(context);
        }
        return preferenceHelper;
    }


    public PreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
        this.context = context;
    }







    public void savemodel(User user) {


        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("user", json);
        prefsEditor.commit();
    }

//
//
    public User getmodel() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("user", "");
        User obj = gson.fromJson(json, User.class);
        return obj;
    }



    public void puttoken(String token) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("Token",token);
        edit.commit();
    }


    public String gettoken() {
        return sharedPreferences.getString("Token", "");
    }


    public void clear(){ // Delete all shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.commit();
    }

    public boolean islogin(){
        if (gettoken()==null || gettoken().equals(""))
            return false ;
        else
            return true ;
    }
}
