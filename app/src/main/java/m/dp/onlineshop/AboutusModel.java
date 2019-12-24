package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

public class AboutusModel {

    @SerializedName("stat")
    String stat ;

    @SerializedName("msg")
    String msg ;

    @SerializedName("aboutus")
    String aboutus ;

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

    public String getAboutus() {
        return aboutus;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }


}
