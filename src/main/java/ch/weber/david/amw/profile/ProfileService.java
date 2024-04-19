package ch.weber.david.amw.profile;

import ch.weber.david.amw.base.MessageResponse;
import ch.weber.david.amw.storage.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    public List<Profile> getProfiles() {
        return repository.findByOrderByUsernameAsc();
    }

    public Profile getProfile(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Profile.class));
    }

    public Profile insertProfile(Profile profile) {
        System.out.println(profile);
        return repository.save(profile);
    }

    public Profile updateProfile(Profile profile, Long id) {
        return repository.findById(id)
                .map(profileOrig -> {
                    profileOrig.setUsername(profile.getUsername());
                    profileOrig.setBio(profile.getBio());
                    profileOrig.setJoinDate(profile.getJoinDate());
                    profileOrig.setMinecraftAccounts(profile.getMinecraftAccounts());
                    profileOrig.setDiscordAccounts(profile.getDiscordAccounts());
                    profileOrig.setYoutubeAccounts(profile.getYoutubeAccounts());
                    profileOrig.setSteamAccounts(profile.getSteamAccounts());
                    return repository.save(profileOrig);
                })
                .orElseGet(() -> repository.save(profile));
    }

    public MessageResponse deleteProfile(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Profile " + id + " deleted");
    }
}
