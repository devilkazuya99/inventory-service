package blog.technodeck.inventory.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

@Entity
@UserDefinition
public class AppUser extends PanacheEntity {

	@Username
	public String username;
    @Password
    public String password;
    @ManyToMany(cascade = CascadeType.ALL)
    @Roles
    public List<UserRole> roles = new ArrayList<>();;
    
    public static void add(String username, String password, String role) { 
    	AppUser user = new AppUser();
        user.username = username;
        user.password = BcryptUtil.bcryptHash(password);
        UserRole userRole = new UserRole();
        userRole.role = role;
        user.roles.add(userRole);
        user.persist();
    }
    
}
