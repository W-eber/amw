package ch.weber.david.amw.youtubeaccount;

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
public class YoutubeAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private int followers;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public YoutubeAccount() {
        
    }
}

