package model.public_side;

public class UserData {
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
    public UserData setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserData setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "loginA='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
