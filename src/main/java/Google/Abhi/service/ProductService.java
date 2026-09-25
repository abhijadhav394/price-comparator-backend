package Google.Abhi.service;

import Google.Abhi.Entity.Product;
import Google.Abhi.Entity.Seller;
import Google.Abhi.dto.ProductRequest;
import Google.Abhi.dto.ProductResponse;
import Google.Abhi.exception.ProductNotFoundException;
import Google.Abhi.repository.ProductRepository;
import Google.Abhi.repository.SellerRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import Google.Abhi.dto.PriceComparisonResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    public ProductService(ProductRepository productRepository,
                          SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }


    // CREATE
    public Product saveProduct(ProductRequest productRequest) {

        Product product = new Product();

        product.setName(productRequest.getName());
        product.setAmazonPrice(productRequest.getAmazonPrice());
        product.setFlipkartPrice(productRequest.getFlipkartPrice());
        product.setProductUrl(productRequest.getProductUrl());
        product.setCategory(productRequest.getCategory());

        Seller seller = sellerRepository.findById(productRequest.getSellerId())
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        product.setSeller(seller);

        return productRepository.save(product);
    }

    // GET ALL
    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();

        List<ProductResponse> responses = new ArrayList<>();

        for (Product product : products) {

            ProductResponse response = new ProductResponse();

            response.setId(product.getId());
            response.setName(product.getName());
            response.setAmazonPrice(product.getAmazonPrice());
            response.setFlipkartPrice(product.getFlipkartPrice());
            response.setProductUrl(product.getProductUrl());
            response.setCategory(product.getCategory());

            responses.add(response);
        }

        return responses;
    }

    // GET BY ID
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setAmazonPrice(product.getAmazonPrice());
        response.setFlipkartPrice(product.getFlipkartPrice());
        response.setProductUrl(product.getProductUrl());
        response.setCategory(product.getCategory());

        return response;
    }

    // UPDATE
    public Product updateProduct(Long id, Product product) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        existingProduct.setName(product.getName());
        existingProduct.setAmazonPrice(product.getAmazonPrice());
        existingProduct.setFlipkartPrice(product.getFlipkartPrice());
        existingProduct.setProductUrl(product.getProductUrl());
        existingProduct.setCategory(product.getCategory());

        return productRepository.save(existingProduct);
    }

    // DELETE
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


    //paging
    public Page<Product> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findAll(pageable);
    }
    //price filtering
    public List<Product> getProductsBelowPrice(double price) {
        return productRepository.findProductsBelowAmazonPrice(price);
    }

    public PriceComparisonResponse comparePrice(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        double amazonPrice = product.getAmazonPrice();
        double flipkartPrice = product.getFlipkartPrice();

        if (amazonPrice < flipkartPrice) {

            double saving = flipkartPrice - amazonPrice;

            return new PriceComparisonResponse(
                    product.getId(),
                    product.getName(),
                    amazonPrice,
                    flipkartPrice,
                    "Amazon",
                    saving
            );

        } else if (flipkartPrice < amazonPrice) {

            double saving = amazonPrice - flipkartPrice;

            return new PriceComparisonResponse(
                    product.getId(),
                    product.getName(),
                    amazonPrice,
                    flipkartPrice,
                    "Flipkart",
                    saving
            );

        } else {

            return new PriceComparisonResponse(
                    product.getId(),
                    product.getName(),
                    amazonPrice,
                    flipkartPrice,
                    "Same",
                    0
            );
        }
    }

    public List<ProductResponse> searchProducts(String name) {

        List<Product> products =
                productRepository.findByNameContainingIgnoreCase(name);

        List<ProductResponse> responses = new ArrayList<>();

        for (Product product : products) {

            ProductResponse response = new ProductResponse();

            response.setId(product.getId());
            response.setName(product.getName());
            response.setAmazonPrice(product.getAmazonPrice());
            response.setFlipkartPrice(product.getFlipkartPrice());
            response.setProductUrl(product.getProductUrl());
            response.setCategory(product.getCategory());

            responses.add(response);
        }

        return responses;
    }

   // sort prices
    public List<ProductResponse> sortProducts(String order) {

        List<Product> products;

        if (order.equalsIgnoreCase("asc")) {
            products = productRepository.findAllByOrderByAmazonPriceAsc();
        } else if (order.equalsIgnoreCase("desc")) {
            products = productRepository.findAllByOrderByAmazonPriceDesc();
        } else {
            throw new RuntimeException("Order must be asc or desc");
        }

        List<ProductResponse> responses = new ArrayList<>();

        for (Product product : products) {

            ProductResponse response = new ProductResponse();

            response.setId(product.getId());
            response.setName(product.getName());
            response.setAmazonPrice(product.getAmazonPrice());
            response.setFlipkartPrice(product.getFlipkartPrice());
            response.setProductUrl(product.getProductUrl());

            responses.add(response);
        }

        return responses;
    }

    //filterProducts()
    public List<ProductResponse> filterProducts(double maxPrice) {

        List<Product> products =
                productRepository.findByAmazonPriceLessThanEqual(maxPrice);

        List<ProductResponse> responses = new ArrayList<>();

        for (Product product : products) {

            ProductResponse response = new ProductResponse();

            response.setId(product.getId());
            response.setName(product.getName());
            response.setAmazonPrice(product.getAmazonPrice());
            response.setFlipkartPrice(product.getFlipkartPrice());
            response.setProductUrl(product.getProductUrl());
            response.setCategory(product.getCategory());

            responses.add(response);
        }

        return responses;
    }

    // filterProductsByRange()
    public List<ProductResponse> filterProductsByRange(
            double minPrice,
            double maxPrice) {

        List<Product> products =
                productRepository.findByAmazonPriceBetween(
                        minPrice,
                        maxPrice
                );

        List<ProductResponse> responses = new ArrayList<>();

        for (Product product : products) {

            ProductResponse response = new ProductResponse();

            response.setId(product.getId());
            response.setName(product.getName());
            response.setAmazonPrice(product.getAmazonPrice());
            response.setFlipkartPrice(product.getFlipkartPrice());
            response.setProductUrl(product.getProductUrl());
            response.setCategory(product.getCategory());

            responses.add(response);
        }

        return responses;
    }

    //category
    public List<ProductResponse> getProductsByCategory(String category) {

        List<Product> products =
                productRepository.findByCategoryIgnoreCase(category);

        List<ProductResponse> responses = new ArrayList<>();

        for (Product product : products) {

            ProductResponse response = new ProductResponse();

            response.setId(product.getId());
            response.setName(product.getName());
            response.setAmazonPrice(product.getAmazonPrice());
            response.setFlipkartPrice(product.getFlipkartPrice());
            response.setProductUrl(product.getProductUrl());
            response.setCategory(product.getCategory());

            responses.add(response);
        }

        return responses;
    }
}
