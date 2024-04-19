package ch.weber.david.amw.minecraftaccount.capes;

import ch.weber.david.amw.minecraftaccount.MinecraftAccount;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class Capes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotEmpty
    private String name;

    @ManyToOne
    @JoinColumn(name = "minecraft_account_id")
    private MinecraftAccount minecraftAccount;

    public Capes() {
    
    }
}

