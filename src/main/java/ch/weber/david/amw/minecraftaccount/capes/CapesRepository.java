package ch.weber.david.amw.minecraftaccount.capes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapesRepository extends JpaRepository<Capes, Long> {
    List<Capes> findByOrderByNameDesc();
}
