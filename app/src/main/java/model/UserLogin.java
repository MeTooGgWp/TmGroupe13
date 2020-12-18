package model;

public class UserLogin {
    private String pseudo;
    private String token;

    public String getPseudo() {
        return pseudo;
    }

    public String getToken() {
        return token;
    }

    public void setPseudo(String user) {
        this.pseudo = user;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
