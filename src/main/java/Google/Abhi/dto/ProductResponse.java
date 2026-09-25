package Google.Abhi.dto;

public class ProductResponse {

    private Long id;
    private String name;
    private double amazonPrice;
    private double flipkartPrice;
    private String productUrl;
    private String category;

    public ProductResponse() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}