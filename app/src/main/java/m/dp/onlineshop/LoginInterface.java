package m.dp.onlineshop;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface {

    String LOGURL = "https://bandiooo.000webhostapp.com/public/api/";

    @FormUrlEncoded
    @POST("login")
    Call<LoginResp> getUserLogin(

//            @Field("stat") String stat,
//            @Field("msg") String msg,

                @Field("username") String username ,

                @Field("password") String password

    );
}
