package ch.weber.david.amw.youtubeaccount;

import ch.weber.david.amw.base.MessageResponse;
import ch.weber.david.amw.storage.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YoutubeAccountService {

    private final YoutubeAccountRepository repository;

    public YoutubeAccountService(YoutubeAccountRepository repository) {
        this.repository = repository;
    }

    public List<YoutubeAccount> getYoutubeAccounts() {
        return repository.findByOrderByUsernameAsc();
    }

    public YoutubeAccount getYoutubeAccount(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, YoutubeAccount.class));
    }

    public YoutubeAccount insertYoutubeAccount(YoutubeAccount youtubeaccount) {
        System.out.println(youtubeaccount);
        return repository.save(youtubeaccount);
    }

    public YoutubeAccount updateYoutubeAccount(YoutubeAccount youtubeaccount, Long id) {
        return repository.findById(id)
                .map(youtubeaccountOrig -> {
                    youtubeaccountOrig.setUsername(youtubeaccount.getUsername());
                    youtubeaccountOrig.setFollowers(youtubeaccount.getFollowers());
                    youtubeaccountOrig.setProfile(youtubeaccount.getProfile());
                    return repository.save(youtubeaccountOrig);
                })
                .orElseGet(() -> repository.save(youtubeaccount));
    }

    public MessageResponse deleteYoutubeAccount(Long id) {
        repository.deleteById(id);
        return new MessageResponse("YoutubeAccount " + id + " deleted");
    }
}
