package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

public class SizeModel {

    @SerializedName("size")
    String size ;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
