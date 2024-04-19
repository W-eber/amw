package ch.weber.david.amw.profile;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 16)
    @NotEmpty
    private String username;

    @Column(length = 50)
    private String bio;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private LocalDate joinDate;

    public Profile() {

    }

    public Profile(String username, String bio, LocalDate joinDate) {
        this.username = username;
        this.bio = bio;
        this.joinDate = joinDate;
    }

}
