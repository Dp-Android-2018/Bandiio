package m.dp.onlineshop;

import com.google.gson.annotations.SerializedName;

public class LoginUser {

    @SerializedName("id")
    String id ;

    @SerializedName("username")
    String username ;

    @SerializedName("email")
    String email ;

    @SerializedName("birthdate")
    String birthdate ;

    @SerializedName("token")
    String token ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
