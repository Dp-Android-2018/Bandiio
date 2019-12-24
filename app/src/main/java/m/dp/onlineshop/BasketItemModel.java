package m.dp.onlineshop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasketItemModel {

@SerializedName("text")
    String stayel_name ;
@SerializedName("price")
    String price ;
@SerializedName("size")
    String size ;
@SerializedName("id")
    String id ;
@SerializedName("img")
    String img ;
@SerializedName("max_count")
    String maxcount ;

    @Expose
    String count ;

    @Expose
    boolean is_checked ;

    public boolean get_Is_checked() {
        return is_checked;
    }

    public void setIs_checked(boolean is_checked) {
        this.is_checked = is_checked;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }




    public String getStayel_name() {
        return stayel_name;
    }

    public void setStayel_name(String stayel_name) {
        this.stayel_name = stayel_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

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

    public String getMaxcount() {
        return maxcount;
    }

    public void setMaxcount(String maxcount) {
        this.maxcount = maxcount;
    }
}
