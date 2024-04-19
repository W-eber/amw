package ch.weber.david.amw.youtubeaccount;

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
public class YoutubeAccountController {

    private final YoutubeAccountService youtubeaccountService;

    YoutubeAccountController(YoutubeAccountService youtubeaccountService) {
        this.youtubeaccountService = youtubeaccountService;
    }

    @GetMapping("api/youtubeaccount")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<YoutubeAccount>> all() {
        List<YoutubeAccount> result = youtubeaccountService.getYoutubeAccounts();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/youtubeaccount/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<YoutubeAccount> one(@PathVariable Long id) {
        YoutubeAccount youtubeaccount = youtubeaccountService.getYoutubeAccount(id);
        return new ResponseEntity<>(youtubeaccount, HttpStatus.OK);
    }

    @PostMapping("api/youtubeaccount")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<YoutubeAccount> newYoutubeAccount(@Valid @RequestBody YoutubeAccount youtubeaccount) {
        YoutubeAccount savedYoutubeAccount = youtubeaccountService.insertYoutubeAccount(youtubeaccount);
        return new ResponseEntity<>(savedYoutubeAccount, HttpStatus.OK);
    }

    @PutMapping("api/youtubeaccount/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<YoutubeAccount> updateYoutubeAccount(@Valid @RequestBody YoutubeAccount youtubeaccount, @PathVariable Long id) {
        YoutubeAccount savedYoutubeAccount = youtubeaccountService.updateYoutubeAccount(youtubeaccount, id);
        return new ResponseEntity<>(savedYoutubeAccount, HttpStatus.OK);
    }

    @DeleteMapping("api/youtubeaccount/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteYoutubeAccount(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(youtubeaccountService.deleteYoutubeAccount(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}