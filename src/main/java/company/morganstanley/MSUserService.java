package company.morganstanley;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

class User {
    String id; // uuid - unique
    String name;
    // other info related to the user is stored here also


}

class UserRepository {
    public Collection<User> findAll() {
        // pretend that this method loads all entries from the database
        return Collections.emptyList();
    }
}

class UserService {

    private final UserRepository repository;
//    private Map<String,User> userCache = new ConcurrentHashMap<>();
    private volatile Map<String,User> userCache = new HashMap<>();

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 5000)
    public void refresh() {
        // refresh the cache
        Map<String, User> localCache = new HashMap<>();
        repository.findAll().forEach(user->{
            localCache.put(user.id,user);
        });
        //Set<String> deletedUsers = userCache.keySet().stream().filter(key -> !localCache.keySet().contains(key)).collect(Collectors.toSet());
        //userCache.keySet().removeAll(deletedUsers);
        userCache = localCache;
    }

    public User getById(String id) {
        // implement this, and allow it to be called from multiple threads
        return userCache.get(id);
    }
}