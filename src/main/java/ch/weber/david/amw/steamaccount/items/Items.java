package ch.weber.david.amw.steamaccount.items;

import ch.weber.david.amw.steamaccount.SteamAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;

    @ManyToOne
    @JoinColumn(name = "steam_account_id")
    private SteamAccount steamAccount;

    // Constructor, getters, and setters
}
