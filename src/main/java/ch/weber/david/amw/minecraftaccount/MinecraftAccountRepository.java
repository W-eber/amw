package ch.weber.david.amw.minecraftaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MinecraftAccountRepository extends JpaRepository<MinecraftAccount, Long> {
    List<MinecraftAccount> findByOrderByUsernameDesc();
}
