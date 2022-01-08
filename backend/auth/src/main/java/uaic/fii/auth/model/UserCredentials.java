package uaic.fii.auth.model;

import lombok.Data;

@Data
public class UserCredentials {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
