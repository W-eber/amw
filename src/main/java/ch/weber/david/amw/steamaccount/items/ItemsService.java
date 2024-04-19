package ch.weber.david.amw.steamaccount.items;

import ch.weber.david.amw.base.MessageResponse;
import ch.weber.david.amw.storage.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {

    private final ItemsRepository repository;

    public ItemsService(ItemsRepository repository) {
        this.repository = repository;
    }

    public List<Items> getItemss() {
        return repository.findByOrderByNameAsc();
    }

    public Items getItems(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Items.class));
    }

    public Items insertItems(Items items) {
        System.out.println(items);
        return repository.save(items);
    }

    public Items updateItems(Items items, Long id) {
        return repository.findById(id)
                .map(itemsOrig -> {
                    itemsOrig.setName(items.getName());
                    itemsOrig.setPrice(items.getPrice());
                    itemsOrig.setSteamAccount(items.getSteamAccount());
                    return repository.save(itemsOrig);
                })
                .orElseGet(() -> repository.save(items));
    }

    public MessageResponse deleteItems(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Items " + id + " deleted");
    }
}
