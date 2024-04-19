package ch.weber.david.amw.minecraftaccount;

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
public class MinecraftAccountController {

    private final MinecraftAccountService minecraftaccountService;

    MinecraftAccountController(MinecraftAccountService minecraftaccountService) {
        this.minecraftaccountService = minecraftaccountService;
    }

    @GetMapping("api/minecraftaccount")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<MinecraftAccount>> all() {
        List<MinecraftAccount> result = minecraftaccountService.getMinecraftAccounts();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/minecraftaccount/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<MinecraftAccount> one(@PathVariable Long id) {
        MinecraftAccount minecraftaccount = minecraftaccountService.getMinecraftAccount(id);
        return new ResponseEntity<>(minecraftaccount, HttpStatus.OK);
    }

    @PostMapping("api/minecraftaccount")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MinecraftAccount> newMinecraftAccount(@Valid @RequestBody MinecraftAccount minecraftaccount) {
        MinecraftAccount savedMinecraftAccount = minecraftaccountService.insertMinecraftAccount(minecraftaccount);
        return new ResponseEntity<>(savedMinecraftAccount, HttpStatus.OK);
    }

    @PutMapping("api/minecraftaccount/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MinecraftAccount> updateMinecraftAccount(@Valid @RequestBody MinecraftAccount minecraftaccount, @PathVariable Long id) {
        MinecraftAccount savedMinecraftAccount = minecraftaccountService.updateMinecraftAccount(minecraftaccount, id);
        return new ResponseEntity<>(savedMinecraftAccount, HttpStatus.OK);
    }

    @DeleteMapping("api/minecraftaccount/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteMinecraftAccount(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(minecraftaccountService.deleteMinecraftAccount(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}