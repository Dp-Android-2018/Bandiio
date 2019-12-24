package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

public class RegResponse {

    @SerializedName("stat")
    String stat ;

    @SerializedName("msg")
    String msg ;

    @SerializedName("user")
    User userobj ;



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

    public User getUserobj() {
        return userobj;
    }

    public void setUserobj(User userobj) {
        this.userobj = userobj;
    }
}
