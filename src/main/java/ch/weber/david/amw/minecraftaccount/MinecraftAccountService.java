package ch.weber.david.amw.minecraftaccount;

import ch.weber.david.amw.base.MessageResponse;
import ch.weber.david.amw.storage.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinecraftAccountService {

    private final MinecraftAccountRepository repository;

    public MinecraftAccountService(MinecraftAccountRepository repository) {
        this.repository = repository;
    }

    public List<MinecraftAccount> getMinecraftAccounts() {
        return repository.findByOrderByUsernameDesc();
    }

    public MinecraftAccount getMinecraftAccount(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, MinecraftAccount.class));
    }

    public MinecraftAccount insertMinecraftAccount(MinecraftAccount ma) {
        return repository.save(ma);
    }

    public MinecraftAccount updateMinecraftAccount(MinecraftAccount ma, Long id) {
        return repository.findById(id)
                .map(maOrig -> {
                    maOrig.setUsername(ma.getUsername());
                    maOrig.setNameChanges(ma.getNameChanges());
                    maOrig.setProfile(ma.getProfile());
                    return repository.save(maOrig);
                })
                .orElseGet(() ->
                        repository.save(ma));
    }

    public MessageResponse deleteMinecraftAccount(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Minecraft Account " + id + " deleted");
    }
}

