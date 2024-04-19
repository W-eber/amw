package ch.weber.david.amw.steamaccount.items;

import ch.weber.david.amw.steamaccount.SteamAccount;
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
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @NotNull
    private double price;

    @ManyToOne
    @JoinColumn(name = "steam_account_id")
    private SteamAccount steamAccount;

    public Items() {
        
    }
}
