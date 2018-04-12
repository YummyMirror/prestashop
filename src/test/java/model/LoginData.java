package model;

public class LoginData {
    private String login;
    private String password;

    //Getters
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    //Setters
    public LoginData setLogin(String login) {
        this.login = login;
        return this;
    }

    public LoginData setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
