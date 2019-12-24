package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class ComplainResp {

    @SerializedName("stat")
    String stat ;

    @SerializedName("msg")
    String msg ;

    @SerializedName("user")
    private List<UserComplain> userComplains;

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

    public List<UserComplain> getUserComplains() {
        return userComplains;
    }

    public void setUserComplains(List<UserComplain> userComplains) {
        this.userComplains = userComplains;
    }
}
