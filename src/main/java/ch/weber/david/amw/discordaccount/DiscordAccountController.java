package ch.weber.david.amw.discordaccount;

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
public class DiscordAccountController {

    private final DiscordAccountService discordaccountService;

    DiscordAccountController(DiscordAccountService discordaccountService) {
        this.discordaccountService = discordaccountService;
    }

    @GetMapping("api/discordaccount")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<DiscordAccount>> all() {
        List<DiscordAccount> result = discordaccountService.getDiscordAccounts();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/discordaccount/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<DiscordAccount> one(@PathVariable Long id) {
        DiscordAccount discordaccount = discordaccountService.getDiscordAccount(id);
        return new ResponseEntity<>(discordaccount, HttpStatus.OK);
    }

    @PostMapping("api/discordaccount")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<DiscordAccount> newDiscordAccount(@Valid @RequestBody DiscordAccount discordaccount) {
        DiscordAccount savedDiscordAccount = discordaccountService.insertDiscordAccount(discordaccount);
        return new ResponseEntity<>(savedDiscordAccount, HttpStatus.OK);
    }

    @PutMapping("api/discordaccount/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<DiscordAccount> updateDiscordAccount(@Valid @RequestBody DiscordAccount discordaccount, @PathVariable Long id) {
        DiscordAccount savedDiscordAccount = discordaccountService.updateDiscordAccount(discordaccount, id);
        return new ResponseEntity<>(savedDiscordAccount, HttpStatus.OK);
    }

    @DeleteMapping("api/discordaccount/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteDiscordAccount(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(discordaccountService.deleteDiscordAccount(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}