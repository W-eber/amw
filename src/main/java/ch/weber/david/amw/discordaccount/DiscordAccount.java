package ch.weber.david.amw.discordaccount;

import java.time.LocalDate;

import ch.weber.david.amw.profile.Profile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class DiscordAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 16)
    @NotEmpty
    private String username;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private LocalDate joinDate;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public DiscordAccount() {

    }
}

