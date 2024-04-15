package ch.weber.david.amw.discordaccount;

import ch.weber.david.amw.profile.Profile;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class DiscordAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String joinDate;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    // Constructor, getters, and setters
}

