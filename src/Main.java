import java.time.LocalDate;
import java.util.*;

import Customer.Customer;
import Product.Product;
import Product.ShippableProduct;
import Validators.ValidationException;

public class Main {
    private static HashMap<String, Product> products;
    private static HashMap<Integer, Customer> customers;

    public static void main(String[] args) {
        products = new HashMap<>();
        customers = new HashMap<>();

        // Product test cases
        System.out.println("Test Case 1 (Products)");
        testCase1();
        // Customer test cases
        System.out.println("\nTest Case 2 (Customers)");
        testCase2();

        // Checkout test cases
        System.out.println("\nTest Case 3 (Add products and Checkout)");
        testCase3();
    }

    // Product Addittion
    public static void testCase1() {
        // Empty name
        try {
            addProduct(new Product("", 5, 1500));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        // Empty name
        try {
            addProduct(new Product("  ", 5, 1500));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        // Negative price
        try {
            addProduct(new Product("Mobile scratch cards", -5, 1500));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        // Negative quantity
        try {
            addProduct(new Product("Mobile scratch cards", 5, -1));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        // Valid
        try {
            addProduct(new Product("Mobile scratch cards", 5, 200));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        // Product with the same name
        try {
            addProduct(new Product("Mobile scratch cards", 5, 200));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        // Expired product
        try {
            LocalDate expirationDate = LocalDate.of(2025, 5, 4);
            addProduct(new Product("Milk", 7, 200, expirationDate));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        // Valid with expiration date
        try {
            LocalDate expirationDate = LocalDate.of(2025, 8, 1);
            addProduct(new Product("Potato", 7, 200, expirationDate));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        // Negative weight
        try {
            addProduct(new ShippableProduct("TV", 500, 200, -1500));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        // Valid shippable product
        try {
            addProduct(new ShippableProduct("TV", 500, 3, 1500));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void testCase2() {
        // Empty name
        try {
            addCustomer(new Customer("", 1000));
        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }
        // Empty name
        try {
            addCustomer(new Customer("  ", 1000));
        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }
        // Negative balance
        try {
            addCustomer(new Customer("Ahmed", -1000));
        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }
        // Valid
        try {
            addCustomer(new Customer("Ahmed", 2000));
        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }
        // Valid
        try {
            addCustomer(new Customer("Ali", 50));
        } catch (ValidationException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void testCase3() {
        // Valid Checkout
        customers.get(0).addToCart(products.get("Mobile scratch cards"), 100);
        customers.get(0).addToCart(products.get("TV"), 2);
        customers.get(0).checkout();

        customers.get(1).addToCart(products.get("Mobile scratch cards"), 100);
        // No available stock
        customers.get(1).addToCart(products.get("Mobile scratch cards"), 100);
        // Customer's balance is insufficient.
        customers.get(1).checkout();
    }

    public static void addProduct(Product p) {
        try {
            if (products.containsKey(p.getName())) {
                throw new Exception("There is a product with the same name");
            }
            products.put(p.getName(), p);
            System.out.println("Product added successfully");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void addCustomer(Customer c) {
        customers.put(c.getId(), c);
        System.out.println("Customer added successfully");
    }
}