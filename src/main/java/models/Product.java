package models;

import java.math.BigDecimal;

public class Product {

    private String productName;
    private BigDecimal productPrice;
    private int quantity;
    private BigDecimal productTotalPrice;

    public Product() {
    }

    public Product(String productName, BigDecimal productPrice, int quantity, BigDecimal productTotalPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.productTotalPrice = productTotalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public void updateQuantity(int quantity) {
        this.quantity += quantity;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void updateProductTotalPrice() {
        this.productTotalPrice = this.productPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object obj) {
        return ((Product) obj).getProductName().equals(this.productName)
                && ((Product) obj).getProductPrice().compareTo(this.productPrice) == 0
                && ((Product) obj).getQuantity() == this.quantity
                && ((Product) obj).getProductTotalPrice().compareTo(this.productTotalPrice) == 0;
    }
}
