package ch.weber.david.amw.profile;

import java.util.List;

import org.springframework.data.annotation.Id;

import ch.weber.david.amw.discordaccount.DiscordAccount;
import ch.weber.david.amw.minecraftaccount.MinecraftAccount;
import ch.weber.david.amw.steamaccount.SteamAccount;
import ch.weber.david.amw.youtubeaccount.YoutubeAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String bio;
    private String joinDate;

    @OneToMany(mappedBy = "profile")
    private List<MinecraftAccount> minecraftAccounts;

    @OneToMany(mappedBy = "profile")
    private List<DiscordAccount> discordAccounts;

    @OneToMany(mappedBy = "profile")
    private List<YoutubeAccount> youtubeAccounts;

    @OneToMany(mappedBy = "profile")
    private List<SteamAccount> steamAccounts;

    // Constructor, getters, and setters
}
