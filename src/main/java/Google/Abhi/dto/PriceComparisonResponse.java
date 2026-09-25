package Google.Abhi.dto;

public class PriceComparisonResponse {

    private Long productId;
    private String productName;
    private double amazonPrice;
    private double flipkartPrice;
    private String cheaperPlatform;
    private double saving;

    public PriceComparisonResponse() {
    }

    public PriceComparisonResponse(
            Long productId,
            String productName,
            double amazonPrice,
            double flipkartPrice,
            String cheaperPlatform,
            double saving) {

        this.productId = productId;
        this.productName = productName;
        this.amazonPrice = amazonPrice;
        this.flipkartPrice = flipkartPrice;
        this.cheaperPlatform = cheaperPlatform;
        this.saving = saving;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getAmazonPrice() {
        return amazonPrice;
    }

    public double getFlipkartPrice() {
        return flipkartPrice;
    }

    public String getCheaperPlatform() {
        return cheaperPlatform;
    }

    public double getSaving() {
        return saving;
    }
}