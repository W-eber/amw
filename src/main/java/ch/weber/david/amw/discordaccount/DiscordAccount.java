package ch.weber.david.amw.discordaccount;

import java.time.LocalDate;

import ch.weber.david.amw.profile.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class DiscordAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private LocalDate joinDate;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public DiscordAccount() {

    }
}

