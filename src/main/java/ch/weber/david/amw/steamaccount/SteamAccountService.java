package ch.weber.david.amw.steamaccount;

import ch.weber.david.amw.base.MessageResponse;
import ch.weber.david.amw.storage.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SteamAccountService {

    private final SteamAccountRepository repository;

    public SteamAccountService(SteamAccountRepository repository) {
        this.repository = repository;
    }

    public List<SteamAccount> getSteamAccounts() {
        return repository.findByOrderByUsernameAsc();
    }

    public SteamAccount getSteamAccount(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, SteamAccount.class));
    }

    public SteamAccount insertSteamAccount(SteamAccount steamaccount) {
        System.out.println(steamaccount);
        return repository.save(steamaccount);
    }

    public SteamAccount updateSteamAccount(SteamAccount steamaccount, Long id) {
        return repository.findById(id)
                .map(steamaccountOrig -> {
                    steamaccountOrig.setUsername(steamaccount.getUsername());
                    steamaccountOrig.setFriendCode(steamaccount.getFriendCode());
                    steamaccountOrig.setProfile(steamaccount.getProfile());
                    return repository.save(steamaccountOrig);
                })
                .orElseGet(() -> repository.save(steamaccount));
    }

    public MessageResponse deleteSteamAccount(Long id) {
        repository.deleteById(id);
        return new MessageResponse("SteamAccount " + id + " deleted");
    }
}
