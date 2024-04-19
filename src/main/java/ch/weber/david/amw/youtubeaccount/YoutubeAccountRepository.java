package ch.weber.david.amw.youtubeaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YoutubeAccountRepository extends JpaRepository<YoutubeAccount, Long> {
    List<YoutubeAccount> findByOrderByUsernameAsc();
}
