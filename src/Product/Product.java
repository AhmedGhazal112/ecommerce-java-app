package Product;

import java.time.LocalDate;
import Validators.ValidationException;
import Validators.Validator;

public class Product {
    private String name;
    private int id, quantity;
    private double price;
    private boolean hasExpiry;
    private LocalDate expirationDate;
    private static int idCounter = 0;

    public Product(String name, double price, int quantity) throws ValidationException {
        this.id = idCounter++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.hasExpiry = false;
        Validator.validate(this);
    }

    public Product(String name, double price, int quantity, LocalDate expirationDate) throws ValidationException {
        this(name, price, quantity);
        this.hasExpiry = true;
        this.expirationDate = expirationDate;
        Validator.validate(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void increasePrice(int amount) {
        price += amount;
    }

    public void decreasePrice(int amount) {
        if (price - amount < 0) {
            System.out.println("Price shouldn't be negative");
            return;
        }
        price -= amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(int amount) {
        quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        if (quantity - amount < 0) {
            System.out.println("Quantity cannot be negative");
            return;
        }
        quantity -= amount;
    }

    public boolean getHasExpiry() {
        return hasExpiry;
    }

    public void setHasExpiry(boolean hasExpiry) {
        this.hasExpiry = hasExpiry;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getWeight() {
        return 0;
    }

    public boolean isCanPurchase(int quantity) {
        return this.quantity >= quantity;
    }

    public double calculateShippingCost() {
        return 0;
    }

    public boolean isExpired() {
        return hasExpiry && expirationDate.isBefore(LocalDate.now());
    }
}