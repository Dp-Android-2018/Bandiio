package m.dp.onlineshop;


import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    String username ;

    @SerializedName("email")
    String email ;

    @SerializedName("birthdate")
    String birthdate ;

    @SerializedName("password")
    String password ;

    @SerializedName("token")
    String token ;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
