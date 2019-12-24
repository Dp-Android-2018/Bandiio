package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

class SocialModel {

    @SerializedName("stat")
    String stat ;
    @SerializedName("msg")
    String msg ;
    @SerializedName("user")
    UserComplain userComplains;

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

    public UserComplain getUserComplains() {
        return userComplains;
    }

    public void setUserComplains(UserComplain userComplains) {
        this.userComplains = userComplains;
    }
}
