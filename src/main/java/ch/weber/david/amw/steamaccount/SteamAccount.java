package ch.weber.david.amw.steamaccount;

import ch.weber.david.amw.profile.Profile;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class SteamAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String friendCode;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    // Constructor, getters, and setters
}

