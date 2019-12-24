package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BasketResp {

    @SerializedName("stat")
    String stat ;
    @SerializedName("msg")
    String msg ;

    @SerializedName("basket")
    private List<BasketItemModel> basketItemModel ;

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

    public List<BasketItemModel> getBasketItemModel() {
        return basketItemModel;
    }

    public void setBasketItemModel(List<BasketItemModel> basketItemModel) {
        this.basketItemModel = basketItemModel;
    }
}
