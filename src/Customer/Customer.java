package Customer;

import java.util.*;

import CartItem.CartItem;
import Product.Product;
import Validators.ValidationException;
import Validators.Validator;

public class Customer {
    private static int idCounter = 0;
    private int id;
    private String name;
    private double balance;
    private Map<Integer, CartItem> cart;
    // Keep track this values after each addittion to avoid O(N) calculation every
    // time
    private double total, subtotal, shipping, totalWeight;

    public Customer(String name, double balance) throws ValidationException {
        this.name = name;
        this.balance = balance;
        this.cart = new HashMap<>();
        Validator.validate(this);
        id = idCounter++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void increaseBalance(double amount) throws ValidationException {
        balance += amount;
        Validator.validate(this);
    }

    public void decreaseBalance(double amount) throws ValidationException {
        balance -= amount;
        Validator.validate(this);
    }

    public void addToCart(Product product, int quantity) {
        try {
            CartItem newItem = new CartItem(product, quantity);
            if (cart.containsKey(product.getId())) {
                int newQuantity = quantity + cart.get(product.getId()).getQuantity();
                newItem.setQuantity(newQuantity);
            }

            subtotal += product.getPrice() * quantity;
            shipping += product.calculateShippingCost() * quantity;
            total = subtotal + shipping;
            totalWeight += product.getWeight() * quantity;

            cart.put(product.getId(), newItem);
            System.out.println("Added " + quantity + " " + product.getName() +
                    " to the cart");
        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }
    }

    public void removeFromCart(Product product) {
        try {
            Validator.validate(product);
            if (!cart.containsKey(product.getId())) {
                System.err.println("The product doesn't exist in the cart.");
                return;
            }
            int quantity = cart.get(product.getId()).getQuantity();
            subtotal -= product.getPrice() * quantity;
            shipping -= product.calculateShippingCost() * quantity;
            total = subtotal + shipping;
            totalWeight -= product.getWeight() * quantity;

            cart.remove(product.getId());
            System.out.println("Product " + product.getName() + "successfully removed from the cart");
        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }
    }

    public void checkout() {
        if (cart.isEmpty()) {
            System.err.println("Can't proceed to checkout with empty cart.");
            return;
        }
        if (total > balance) {
            System.err.println("Customer's balance is insufficient");
            System.err.print("Your current balance is " + balance + ", while the total paid amount is " + total);
            return;
        }
        System.out.println("** Shipment notice **");
        for (CartItem item : cart.values()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            System.out.print(quantity + "x " + product.getName() + "  ");
            if (product.getClass().getSimpleName().equals("Product")) {
                System.out.println("--");
            } else {
                System.out.println(product.getWeight() * quantity + "g");
            }
        }
        System.out.println("Total package weight " + totalWeight / 1000 + "kg\n");
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.values()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            System.out.println(quantity + "x " + product.getName() + "  " + item.calculateCartCost());
        }
        System.out.println("-----------------------");
        System.out.println("Subtotal    " + subtotal);
        System.out.println("Shipping    " + shipping);
        System.out.println("Total     " + total);
        balance -= total;
        System.out.println("Remaining balance " + balance + "\n");
        updateProductsStock();
        
        cart.clear();
    }

    private void updateProductsStock() {
        for (CartItem item : cart.values()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            product.decreaseQuantity(quantity);
        }
    }
}

// // Update quantity of product in cart - Optimized version
// public boolean updateCartQuantity(String productId, int newQuantity) {
// if (newQuantity <= 0) {
// return removeFromCart(productId);
// }

// CartItem item = cart.get(productId);
// if (item != null) {
// if (item.getProduct().isInStock(newQuantity)) {
// item.setQuantity(newQuantity);
// System.out.println("Updated " + item.getProduct().getName() +
// " quantity to " + newQuantity);
// return true;
// } else {
// System.out.println("Insufficient stock for update");
// return false;
// }
// }
// System.out.println("Product not found in cart");
// return false;
// }

// public void clearCart() {
// cart.clear();
// System.out.println("Cart cleared");
// }