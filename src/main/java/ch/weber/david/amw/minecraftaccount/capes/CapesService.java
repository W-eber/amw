package ch.weber.david.amw.minecraftaccount.capes;

import ch.weber.david.amw.base.MessageResponse;
import ch.weber.david.amw.storage.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapesService {

    private final CapesRepository repository;

    public CapesService(CapesRepository repository) {
        this.repository = repository;
    }

    public List<Capes> getCapes() {
        return repository.findByOrderByNameDesc();
    }

    public Capes getCapes(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Capes.class));
    }

    public Capes insertCapes(Capes capes) {
        System.out.println(capes);
        return repository.save(capes);
    }

    public Capes updateCapes(Capes capes, Long id) {
        return repository.findById(id)
                .map(capesOrig -> {
                    capesOrig.setName(capes.getName());
                    capesOrig.setMinecraftAccount(capes.getMinecraftAccount());
                    return repository.save(capesOrig);
                })
                .orElseGet(() -> repository.save(capes));
    }

    public MessageResponse deleteCapes(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Capes " + id + " deleted");
    }
}
