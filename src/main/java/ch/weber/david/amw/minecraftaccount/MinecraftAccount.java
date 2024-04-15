package ch.weber.david.amw.minecraftaccount;

import ch.weber.david.amw.profile.Profile;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

    // Constructor, getters, and setters
}

