package ch.weber.david.amw.steamaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SteamAccountRepository extends JpaRepository<SteamAccount, Long> {
    List<SteamAccount> findByOrderByUsernameAsc();
}
