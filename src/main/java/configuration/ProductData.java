package configuration;


import java.math.BigDecimal;


public class ProductData {
    private String product1Name;
    private String product2Name;
    private String currency;
    private BigDecimal shippingFee;

    public String getCategory() {
        return category;
    }

    private String category;

    public String getProduct1Name() {
        return product1Name;
    }

    public String getProduct2Name() {
        return product2Name;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }
}


