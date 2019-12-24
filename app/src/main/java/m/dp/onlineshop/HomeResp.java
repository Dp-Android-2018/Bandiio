package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeResp {

    @SerializedName("stat")
    String stat ;

    @SerializedName("msg")
    String msg ;

    @SerializedName("slider")
    private List <SliderModel> sliderModel ;

    @SerializedName("home")
    private List <HomeModel> homeModel ;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List <SliderModel> getSliderModel() {
        return sliderModel;
    }

    public void setSliderModel(List <SliderModel> sliderModel) {
        this.sliderModel = sliderModel;
    }

    public List <HomeModel> getHomeModel() {
        return homeModel;
    }

    public void setHomeModel(List <HomeModel> homeModel) {
        this.homeModel = homeModel;
    }






}
