package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

public class FavouriteModel {

    @SerializedName("msg")
    String msg ;

    @SerializedName("stat")
    String stat ;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
