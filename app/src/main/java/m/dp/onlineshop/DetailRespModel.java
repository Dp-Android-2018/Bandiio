package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailRespModel {

    @SerializedName("stat")
    String stat;

    @SerializedName("msg")
    String msg;

    @SerializedName("product")
    private List<ProductModel> product ;

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

    public List<ProductModel> getProduct() {
        return product;
    }

    public void setProduct(List<ProductModel> product) {
        this.product = product;
    }

 ;
}
