package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

class ProductsDetails {

    @SerializedName("id")
    String id ;

    @SerializedName("category_id")
    String category_id ;

    @SerializedName("img")
    String img ;

    @SerializedName("offer")
    String offer ;

    @SerializedName("price")
    String price ;

    @SerializedName("old_price")
    String old_price ;

    @SerializedName("Favorite")
    String Favorite ;

    @SerializedName("num_favourit")
    String num_favourit ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
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

    public String getFavorite() {
        return Favorite;
    }

    public void setFavorite(String favorite) {
        Favorite = favorite;
    }

    public String getNum_favourit() {
        return num_favourit;
    }

    public void setNum_favourit(String num_favourit) {
        this.num_favourit = num_favourit;
    }
}
