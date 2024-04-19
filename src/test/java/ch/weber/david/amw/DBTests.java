package ch.weber.david.amw;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import ch.weber.david.amw.profile.Profile;
import ch.weber.david.amw.profile.ProfileRepository;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class DBTests {

    @Autowired
    private ProfileRepository profileRepository;

    @Test
	void insertProfile() {
    LocalDate joinDate1 = LocalDate.parse("2024-04-18");
    Profile objProfile1 = this.profileRepository.save(new Profile("username", "biography", joinDate1));
    Assertions.assertNotNull(objProfile1.getId());

	LocalDate joinDate2 = LocalDate.parse("2024-04-18");
    Profile objProfile2 = this.profileRepository.save(new Profile("1234", "", joinDate2));
    Assertions.assertNotNull(objProfile2.getId());
    }
}
