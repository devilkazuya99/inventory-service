package blog.technodeck.inventory;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import blog.technodeck.inventory.entity.AppUser;
import io.quarkus.runtime.StartupEvent;

@Singleton
public class Startup {
	
	@Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users
        AppUser.deleteAll();
        AppUser.add("admin", "admin", "admin");
        AppUser.add("user", "user", "user");
    }
	
}
