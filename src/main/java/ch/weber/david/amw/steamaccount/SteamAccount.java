package ch.weber.david.amw.steamaccount;

import java.util.List;

import ch.weber.david.amw.profile.Profile;
import ch.weber.david.amw.steamaccount.items.Items;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class SteamAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 24)
    @NotEmpty
    private String username;

    @Column(nullable = false, length = 8)
    @NotEmpty
    private String friendCode;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public SteamAccount() {

    }
}

