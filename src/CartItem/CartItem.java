package CartItem;

import Product.Product;
import Validators.ValidationException;
import Validators.Validator;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) throws ValidationException {
        Validator.validate(product, quantity);
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws ValidationException {
        Validator.validate(product, quantity);
        this.quantity = quantity;
    }

    public double calculateCartCost() {
        return product.getPrice() * quantity;
    }

    public double calculateCartShippingFees() {
        return product.calculateShippingCost() * quantity;
    }

    public double calculateCartTotalCost() {
        return calculateCartCost() + calculateCartShippingFees();
    }
}