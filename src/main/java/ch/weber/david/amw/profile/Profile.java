package ch.weber.david.amw.profile;

import java.time.LocalDate;
import java.util.List;

import ch.weber.david.amw.discordaccount.DiscordAccount;
import ch.weber.david.amw.minecraftaccount.MinecraftAccount;
import ch.weber.david.amw.steamaccount.SteamAccount;
import ch.weber.david.amw.youtubeaccount.YoutubeAccount;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "profile")
    private List<MinecraftAccount> minecraftAccounts;

    @OneToMany(mappedBy = "profile")
    private List<DiscordAccount> discordAccounts;

    @OneToMany(mappedBy = "profile")
    private List<YoutubeAccount> youtubeAccounts;

    @OneToMany(mappedBy = "profile")
    private List<SteamAccount> steamAccounts;

    public Profile() {

    }

    // public Profile(String username, String bio, LocalDate joinDate, List<MinecraftAccount> minecraftAccounts,
    //         List<DiscordAccount> discordaccounts, List<YoutubeAccount> youtubeAccounts,
    //         List<SteamAccount> steamAccounts) {
    //     this.username = username;
    //     this.bio = bio;
    //     this.joinDate = joinDate;

    //     this.minecraftAccounts = minecraftAccounts;
    //     this.discordAccounts = discordaccounts;
    //     this.youtubeAccounts = youtubeAccounts;
    //     this.steamAccounts = steamAccounts;
    // }

}
