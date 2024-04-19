package ch.weber.david.amw.steamaccount.items;

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
public class ItemsController {

    private final ItemsService itemsService;

    ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping("api/items")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Items>> all() {
        List<Items> result = itemsService.getItemss();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/items/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Items> one(@PathVariable Long id) {
        Items items = itemsService.getItems(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("api/items")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Items> newItems(@Valid @RequestBody Items items) {
        Items savedItems = itemsService.insertItems(items);
        return new ResponseEntity<>(savedItems, HttpStatus.OK);
    }

    @PutMapping("api/items/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Items> updateItems(@Valid @RequestBody Items items, @PathVariable Long id) {
        Items savedItems = itemsService.updateItems(items, id);
        return new ResponseEntity<>(savedItems, HttpStatus.OK);
    }

    @DeleteMapping("api/items/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteItems(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(itemsService.deleteItems(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}