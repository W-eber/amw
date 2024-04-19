package ch.weber.david.amw.steamaccount;

import java.util.List;

import ch.weber.david.amw.profile.Profile;
import ch.weber.david.amw.steamaccount.items.Items;
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
public class SteamAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String friendCode;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "steamAccount")
    private List<Items> items;

    public SteamAccount() {

    }
}

