package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductObjResp {
    @SerializedName("id")
    String id ;

    @SerializedName("img")
    List<ImagesDetailResp> imagesDetailResps ;

    @SerializedName("text")
    String text ;

    @SerializedName("price")
    String price ;

    @SerializedName("old_price")
    String old_price ;

    @SerializedName("offer")
    String offer ;

    @SerializedName("rate")
    String rate ;

    @SerializedName("num_rate")
    String num_rate ;


    @SerializedName("size")
    private List<SizeModel> sizemodel ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ImagesDetailResp> getImagesDetailResps() {
        return imagesDetailResps;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOld_price() {
        return old_price;
    }

    public void setOld_price(String old_price) {
        this.old_price = old_price;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getNum_rate() {
        return num_rate;
    }

    public void setNum_rate(String num_rate) {
        this.num_rate = num_rate;
    }

    public List<SizeModel> getSizemodel() {
        return sizemodel;
    }

    public void setSizemodel(List<SizeModel> sizemodel) {
        this.sizemodel = sizemodel;
    }

    public void setImagesDetailResps(List<ImagesDetailResp> imagesDetailResps) {
        this.imagesDetailResps = imagesDetailResps;
    }
}
