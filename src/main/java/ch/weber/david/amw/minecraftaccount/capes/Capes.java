package ch.weber.david.amw.minecraftaccount.capes;

import ch.weber.david.amw.minecraftaccount.MinecraftAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Capes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "minecraft_account_id")
    private MinecraftAccount minecraftAccount;

    // Constructor, getters, and setters
}

