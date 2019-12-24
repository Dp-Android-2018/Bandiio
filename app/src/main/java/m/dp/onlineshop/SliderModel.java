package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

public class SliderModel {
    @SerializedName("id")
    String id;

    @SerializedName("img")
    String img;

    @SerializedName("text")
    String text;

    @SerializedName("type")
    String type;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("updated_at")
    String updated_at;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }




}
