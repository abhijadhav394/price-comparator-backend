package Google.Abhi.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double amazonPrice;

    private double flipkartPrice;

    private String productUrl;

    private String category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonIgnore
    private Seller seller;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmazonPrice() {
        return amazonPrice;
    }

    public void setAmazonPrice(double amazonPrice) {
        this.amazonPrice = amazonPrice;
    }

    public double getFlipkartPrice() {
        return flipkartPrice;
    }

    public void setFlipkartPrice(double flipkartPrice) {
        this.flipkartPrice = flipkartPrice;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}