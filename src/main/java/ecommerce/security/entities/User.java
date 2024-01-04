package ecommerce.security.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false,length = 200)
    private String password;

    //Se usa para identificar el rol que tiene cada usuario
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role_relate", joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
