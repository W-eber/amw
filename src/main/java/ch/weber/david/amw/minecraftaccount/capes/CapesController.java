package ch.weber.david.amw.minecraftaccount.capes;

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
public class CapesController {

    private final CapesService capesService;

    CapesController(CapesService capesService) {
        this.capesService = capesService;
    }

    @GetMapping("api/capes")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Capes>> all() {
        List<Capes> result = capesService.getCapes();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/capes/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Capes> one(@PathVariable Long id) {
        Capes capes = capesService.getCapes(id);
        return new ResponseEntity<>(capes, HttpStatus.OK);
    }

    @PostMapping("api/capes")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Capes> newCapes(@Valid @RequestBody Capes capes) {
        Capes savedCapes = capesService.insertCapes(capes);
        return new ResponseEntity<>(savedCapes, HttpStatus.OK);
    }

    @PutMapping("api/capes/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Capes> updateCapes(@Valid @RequestBody Capes capes, @PathVariable Long id) {
        Capes savedCapes = capesService.updateCapes(capes, id);
        return new ResponseEntity<>(savedCapes, HttpStatus.OK);
    }

    @DeleteMapping("api/capes/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteCapes(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(capesService.deleteCapes(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}