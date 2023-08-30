package asap.hellomockdata.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "username", length = 25, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_type", length = 16, nullable = false)
    private UserType userType;

    protected User() {
    }

    public User(
        final Long id,
        final String username,
        final String password,
        final UserType userType
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public Instant getCreatedAt() {
        return super.createdAt;
    }

    public Instant getUpdatedAt() {
        return super.updatedAt;
    }
}
