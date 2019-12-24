package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

class UserComplain {

    @SerializedName("id")
    String id ;

    @SerializedName("complain")
    String complain ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }


}
