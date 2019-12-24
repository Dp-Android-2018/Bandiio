package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

public class DetailResponse {

    @SerializedName("stat")
    String stat ;

    @SerializedName("msg")
    String msg ;

    @SerializedName("product")
    ProductObjResp productObjResps ;


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


    public ProductObjResp getProductObjResps() {
        return productObjResps;
    }

    public void setProductObjResps(ProductObjResp productObjResps) {
        this.productObjResps = productObjResps;
    }


}
