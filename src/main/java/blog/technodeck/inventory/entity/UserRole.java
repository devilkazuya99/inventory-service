package blog.technodeck.inventory.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.RolesValue;

@Entity
public class UserRole extends PanacheEntity {

	@ManyToMany(mappedBy = "roles")
    public List<AppUser> users;
	
	@RolesValue
	public String role;
}
