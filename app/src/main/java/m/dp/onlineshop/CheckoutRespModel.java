package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

public class CheckoutRespModel {
    @SerializedName("stat")
    String stat ;
    @SerializedName("msg")
    String msg ;

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
}
