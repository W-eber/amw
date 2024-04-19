package ch.weber.david.amw.discordaccount;

import ch.weber.david.amw.base.MessageResponse;
import ch.weber.david.amw.storage.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscordAccountService {

    private final DiscordAccountRepository repository;

    public DiscordAccountService(DiscordAccountRepository repository) {
        this.repository = repository;
    }

    public List<DiscordAccount> getDiscordAccounts() {
        return repository.findByOrderByUsernameDesc();
    }

    public DiscordAccount getDiscordAccount(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, DiscordAccount.class));
    }

    public DiscordAccount insertDiscordAccount(DiscordAccount discordaccount) {
        System.out.println(discordaccount);
        return repository.save(discordaccount);
    }

    public DiscordAccount updateDiscordAccount(DiscordAccount discordaccount, Long id) {
        return repository.findById(id)
                .map(discordaccountOrig -> {
                    discordaccountOrig.setUsername(discordaccount.getUsername());
                    discordaccountOrig.setJoinDate(discordaccount.getJoinDate());
                    discordaccountOrig.setProfile(discordaccount.getProfile());
                    return repository.save(discordaccountOrig);
                })
                .orElseGet(() -> repository.save(discordaccount));
    }

    public MessageResponse deleteDiscordAccount(Long id) {
        repository.deleteById(id);
        return new MessageResponse("DiscordAccount " + id + " deleted");
    }
}
