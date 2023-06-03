package io.example.musicAppApi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
public class User {
    @Id
    private String username;

    private String password;

    private String userRole;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean accountEnabled;

    private Boolean accountExpired;

    private Boolean accountLocked;

    private Boolean credentialsExpired;

    public UsernamePasswordPrincipal toUsernamePasswordPrincipal() {
        return new UsernamePasswordPrincipal(
                this.username,
                this.password,
                this.userRole,
                this.accountExpired,
                this.accountLocked,
                this.credentialsExpired,
                this.accountEnabled,
                this.firstName,
                this.lastName,
                this.email);
    }
}

