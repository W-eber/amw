package ch.weber.david.amw.minecraftaccount;

import ch.weber.david.amw.profile.Profile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class MinecraftAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 16)
    @NotEmpty
    private String username;

    @Column(nullable = false)
    @NotEmpty
    private int nameChanges;

    private enum access {
        MFA, SFA, NFA;
    };

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public MinecraftAccount() {

    }
}
