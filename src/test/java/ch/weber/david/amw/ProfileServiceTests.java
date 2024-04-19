package ch.weber.david.amw;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.weber.david.amw.profile.Profile;
import ch.weber.david.amw.profile.ProfileRepository;
import ch.weber.david.amw.profile.ProfileService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProfileServiceTests {

    private ProfileService profileService;
    private final ProfileRepository profileRepositoryMock = mock(ProfileRepository.class);

    private final Profile profileMock = mock(Profile.class);

    @BeforeEach
    void setUp() {
        profileService = new ProfileService(profileRepositoryMock);
    }

    @Test
    void createProfile() {
        when(profileRepositoryMock.save(profileMock)).thenReturn(profileMock);
        profileService.insertProfile(profileMock);
        verify(profileRepositoryMock, times(1)).save(any());
    }

    @Test
    void readProfile() {
        when(profileRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(profileMock));
        Profile profile = profileService.getProfile(any());
        verify(profileRepositoryMock, times(1)).findById(any());
    }

    @Test
    void updateProfile() {
    Profile updatedProfile = new Profile();
    updatedProfile.setUsername("UpdatedUsername");
    updatedProfile.setBio("UpdatedBio");
    updatedProfile.setJoinDate(LocalDate.now());

    Long profileId = 1L;
    when(profileRepositoryMock.findById(profileId)).thenReturn(Optional.of(profileMock));
    profileService.updateProfile(updatedProfile, profileId);
    verify(profileRepositoryMock, times(1)).save(updatedProfile);  
    }


    @Test
    void deleteProfile() {
        profileService.deleteProfile(any());
        verify(profileRepositoryMock, times(1)).deleteById(any());
    }

}