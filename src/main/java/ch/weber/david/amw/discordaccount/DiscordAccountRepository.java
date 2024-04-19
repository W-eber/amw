package ch.weber.david.amw.discordaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscordAccountRepository extends JpaRepository<DiscordAccount, Long> {
    List<DiscordAccount> findByOrderByUsernameDesc();
}
