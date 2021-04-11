package blog.technodeck.inventory.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Product extends PanacheEntity {

    public Long categoryId;
    public String name;
    public String description;
    public Float price;
    @Enumerated(EnumType.STRING)
    public ProductState state;
    
}
