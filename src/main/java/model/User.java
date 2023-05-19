package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private String role;

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
