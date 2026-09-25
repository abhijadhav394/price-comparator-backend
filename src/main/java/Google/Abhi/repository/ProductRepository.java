package Google.Abhi.repository;

import Google.Abhi.Entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Long>{

    List<Product> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Product p WHERE p.amazonPrice < :price")
    List<Product> findProductsBelowAmazonPrice(@Param("price") double price);

    List<Product> findAllByOrderByAmazonPriceAsc();
    List<Product> findAllByOrderByAmazonPriceDesc();

    List<Product> findByAmazonPriceLessThanEqual(double price);
    List<Product> findByAmazonPriceBetween(double minPrice, double maxPrice);

    List<Product> findByCategoryIgnoreCase(String category);


}
