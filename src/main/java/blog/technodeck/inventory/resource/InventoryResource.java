package blog.technodeck.inventory.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import blog.technodeck.inventory.entity.Category;
import blog.technodeck.inventory.entity.Product;
import blog.technodeck.inventory.entity.ProductState;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryResource {

    @Transactional
    @POST
    @Path("category")
    @RolesAllowed("admin")
    public String createCategory(List<Category> categories) {
        Category.persist(categories);
        return "OK";
    }
    
    @GET
    @Path("category")
    @RolesAllowed("user")
    public List<Category> listCategory() {
        return Category.listAll(Sort.ascending("name"));
    }
    
    @Transactional
    @POST
    @Path("product")
    @RolesAllowed("admin")
    public String addProduct(List<Product> products) {
        products.forEach(p -> {p.state = ProductState.CREATED;});
        Product.persist(products);
        return "OK";
    }
    
    @GET
    @Path("product")
    @RolesAllowed("user")
    public List<Product> listProducts() {
        PanacheQuery<Product> products = Product.findAll(Sort.ascending("id"));
        products.page(Page.ofSize(10)); // first 10 records
        return products.list();
    }
    
    @GET
    @Path("category/{categoryId}/product")
    @RolesAllowed("user")
    public List<Product> listCategoryProducts(@PathParam("categoryId") Long categoryId) {
        return Product.list("categoryId", categoryId);
    }
    
}
