package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

class ImageModel {
    @SerializedName("img")
    String img ;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
