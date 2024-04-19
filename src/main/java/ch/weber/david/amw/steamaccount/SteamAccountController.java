package ch.weber.david.amw.steamaccount;

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
public class SteamAccountController {

    private final SteamAccountService steamaccountService;

    SteamAccountController(SteamAccountService steamaccountService) {
        this.steamaccountService = steamaccountService;
    }

    @GetMapping("api/steamaccount")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<SteamAccount>> all() {
        List<SteamAccount> result = steamaccountService.getSteamAccounts();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/steamaccount/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<SteamAccount> one(@PathVariable Long id) {
        SteamAccount steamaccount = steamaccountService.getSteamAccount(id);
        return new ResponseEntity<>(steamaccount, HttpStatus.OK);
    }

    @PostMapping("api/steamaccount")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<SteamAccount> newSteamAccount(@Valid @RequestBody SteamAccount steamaccount) {
        SteamAccount savedSteamAccount = steamaccountService.insertSteamAccount(steamaccount);
        return new ResponseEntity<>(savedSteamAccount, HttpStatus.OK);
    }

    @PutMapping("api/steamaccount/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<SteamAccount> updateSteamAccount(@Valid @RequestBody SteamAccount steamaccount, @PathVariable Long id) {
        SteamAccount savedSteamAccount = steamaccountService.updateSteamAccount(steamaccount, id);
        return new ResponseEntity<>(savedSteamAccount, HttpStatus.OK);
    }

    @DeleteMapping("api/steamaccount/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteSteamAccount(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(steamaccountService.deleteSteamAccount(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}