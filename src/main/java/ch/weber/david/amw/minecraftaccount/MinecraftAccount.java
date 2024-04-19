package ch.weber.david.amw.minecraftaccount;

import java.util.List;

import ch.weber.david.amw.minecraftaccount.capes.Capes;
import ch.weber.david.amw.profile.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class MinecraftAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private int nameChanges;
    private enum access {MFA, SFA, NFA};

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "minecraftAccount")
    private List<Capes> capes;

    public MinecraftAccount() {

    }
}

