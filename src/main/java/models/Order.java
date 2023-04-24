package models;


import configuration.ConfigurationRetriever;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static helpers.Helper.toSingleton;

public class Order {

    private List<Product> products;

    public Order() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product, int quantity) {
        if (products.stream().map(Product::getProductName).toList().contains(product.getProductName())) {
            Product existingProduct = products.stream()
                    .filter(productInList -> productInList.getProductName().equals(product.getProductName()))
                    .collect(toSingleton());
            existingProduct.updateQuantity(quantity);
            existingProduct.updateProductTotalPrice();
        } else {
            products.add(product);
        }
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Product product : products) {
            totalPrice = totalPrice.add(new BigDecimal(String.valueOf(product.getProductPrice())));
        }
        return totalPrice;
    }

    public BigDecimal getPriceWithShippingFee() {
        return getTotalPrice().add(ConfigurationRetriever.getProductData().getShippingFee());
    }
}
