package m.dp.onlineshop;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterInterface {


    String REGIURL = "https://bandiooo.000webhostapp.com/public/api/";

    @FormUrlEncoded
    @POST("register")
    Call<RegResponse> getUserRegi(

//            @Field("stat") String stat,
//            @Field("msg") String msg,

            @Field("username") String username ,
            @Field("email") String email,
            @Field("birthdate") String birthday ,
            @Field("password") String password ,
            @Field("password_confirmation") String confipassword


            );
}
