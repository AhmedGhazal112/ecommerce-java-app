package Validators;

import Customer.Customer;
import Product.Product;

public class Validator {
    public static void validate(Product product) throws ValidationException {
        if (product == null) {
            throw new ValidationException("Product shouldn't be null");
        }
        if (product.getName().trim().isEmpty()) {
            throw new ValidationException("Product must have a name.");
        }
        if (product.isExpired()) {
            throw new ValidationException("Product has expired");
        }
        if (product.getPrice() < 0) {
            throw new ValidationException("Product's price cannot be negative");
        }
        if (product.getQuantity() < 0) {
            throw new ValidationException("Product quantity cannot be negative");
        }
        if (product.getWeight() < 0) {
            throw new ValidationException("Product weight cannot be negative");
        }
    }

    public static void validate(Product product, int quantity) throws ValidationException {
        validate(product);
        if (!product.isCanPurchase(quantity)) {
            throw new ValidationException(
                    "You can't request " + quantity + " of " + product.getName() + ", you can purchase a maximum of "
                            + product.getQuantity());
        }
        if (quantity < 0) {
            throw new ValidationException("Quantity cannot be negative");
        }
    }

    public static void validate(Customer customer) throws ValidationException {
        if (customer.getName().trim() == "") {
            throw new ValidationException("Customer must have a name");
        }
        if (customer.getBalance() < 0) {
            throw new ValidationException("Balance should be non negative integer");
        }
    }
}