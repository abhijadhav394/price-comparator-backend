package Google.Abhi.controller;

import java.util.List;
import Google.Abhi.Entity.Product;
import Google.Abhi.dto.ProductRequest;
import Google.Abhi.dto.ProductResponse;
import Google.Abhi.service.ProductService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import Google.Abhi.dto.PriceComparisonResponse;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE
    @PostMapping
    public Product saveProduct(@Valid @RequestBody ProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }

    // GET ALL
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }

    @GetMapping("/page")
    public Page<Product> getProducts(
            @RequestParam int page,
            @RequestParam int size) {

        return productService.getProducts(page, size);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return "Product deleted successfully";
    }


    @GetMapping("/below-price")
    public List<Product> getProductsBelowPrice(@RequestParam double price) {
        return productService.getProductsBelowPrice(price);
    }

    @GetMapping("/{id}/compare")
    public PriceComparisonResponse comparePrice(@PathVariable Long id) {
        return productService.comparePrice(id);
    }

    @GetMapping("/search")
    public List<ProductResponse> searchProducts(
            @RequestParam String name) {

        return productService.searchProducts(name);
    }

    @GetMapping("/sort")
    public List<ProductResponse> sortProducts(
            @RequestParam String order) {

        return productService.sortProducts(order);
    }

    @GetMapping("/filter")
    public List<ProductResponse> filterProducts(
            @RequestParam double maxPrice) {

        return productService.filterProducts(maxPrice);
    }

    @GetMapping("/filter/range")
    public List<ProductResponse> filterProductsByRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {

        return productService.filterProductsByRange(
                minPrice,
                maxPrice
        );
    }

    @GetMapping("/category/{category}")
    public List<ProductResponse> getProductsByCategory(
            @PathVariable String category) {

        return productService.getProductsByCategory(category);
    }
}