package ch.weber.david.amw.steamaccount.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {
    List<Items> findByOrderByNameAsc();
}
