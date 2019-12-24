package m.dp.onlineshop;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface HomeInterface {

    String HomeURL = "https://bandiooo.000webhostapp.com/public/api/";


    @GET("home")
    Call<HomeResp> getHome();

    @FormUrlEncoded
    @POST("product")
    Call<ProductsResp> getproducts(@Field("category_id") String category_id , @Header("token") String token);


    @FormUrlEncoded
    @POST("details")
    Call<DetailResponse> Getdetails( @Header("token") String token ,@Field("product_id") String product_id) ;



    @POST("basket")
    Call<BasketResp> getBasket( @Header("token") String token );

    @GET("aboutus")
    Call<AboutusModel> getaboutus() ;

    @FormUrlEncoded
    @POST("complain")
    Call<SocialModel> complain( @Header("token") String token ,@Field("complain") String complain) ;

    @FormUrlEncoded
    @POST("bag")
    Call<BagModelResp> Bag( @Header("token") String token ,
                            @Field("product_id") String product_id ,
                            @Field("size") String size ,
                            @Field("product_count") String product_count) ;

    @FormUrlEncoded
    @POST("checkout")
    Call<CheckoutRespModel> checkout( @Header("token") String token ,
                            @Field("product_id[]") ArrayList<String> product_ID ,
                            @Field("product_count[]") ArrayList<String> product_Count,
                            @Field("total") String total );

    @FormUrlEncoded
    @POST("favorite")
    Call<FavouriteModel> favourite( @Header("token") String token ,@Field("product_id") String product_id) ;


}
