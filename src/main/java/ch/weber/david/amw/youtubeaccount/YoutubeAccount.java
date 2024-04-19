package ch.weber.david.amw.youtubeaccount;

import ch.weber.david.amw.profile.Profile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class YoutubeAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 32)
    @NotEmpty
    private String username;

    @Column(nullable = false)
    @NotNull
    private int followers;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public YoutubeAccount() {
        
    }
}

