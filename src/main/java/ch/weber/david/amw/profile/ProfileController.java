package ch.weber.david.amw.profile;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.weber.david.amw.base.MessageResponse;
import ch.weber.david.amw.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class ProfileController {

    private final ProfileService profileService;

    ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("api/profile")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Profile>> all() {
        List<Profile> result = profileService.getProfiles();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/profile/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Profile> one(@PathVariable Long id) {
        Profile profile = profileService.getProfile(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PostMapping("api/profile")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Profile> newProfile(@Valid @RequestBody Profile profile) {
        Profile savedProfile = profileService.insertProfile(profile);
        return new ResponseEntity<>(savedProfile, HttpStatus.OK);
    }

    @PutMapping("api/profile/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Profile> updateProfile(@Valid @RequestBody Profile profile, @PathVariable Long id) {
        Profile savedProfile = profileService.updateProfile(profile, id);
        return new ResponseEntity<>(savedProfile, HttpStatus.OK);
    }

    @DeleteMapping("api/profile/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteProfile(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(profileService.deleteProfile(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}