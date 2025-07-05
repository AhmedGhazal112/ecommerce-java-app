# E-Commerce Console App

A Java console application that simulates an E-Commerce store.

## Features

- **Product Management**
- **Customer Management**
- **Shopping Cart Functionality**
- **Checkout Process**
- **Shipping Calculation**
- **Validation**

## Project Structure

```
E-Commerce/
├── src/
│   ├── Main.java                    # Main application with test cases
│   ├── Product/
│   │   ├── Product.java             # Base product class
│   │   └── ShippableProduct.java    # Product with shipping capabilities
│   ├── Customer/
│   │   └── Customer.java            # Customer with cart and checkout
│   ├── CartItem/
│   │   └── CartItem.java            # Individual cart item
│   ├── Interfaces/
│   │   └── IShippable.java          # Shipping interface
│   └── Validators/
│       ├── Validator.java           # Validation logic
│       └── ValidationException.java # Custom exception
└── README.txt
```

## Classes Overview

### Product
- Base class for all products
- Supports expiration dates
- Automatic ID generation
- Price and quantity management

### ShippableProduct
- Extends Product with shipping capabilities
- Calculates shipping cost based on weight
- Implements IShippable interface

### Customer
- Manages customer information and balance
- Shopping cart functionality
- Checkout process with receipt generation
- Automatic stock updates after purchase

### CartItem
- Represents individual items in shopping cart
- Calculates item costs and shipping fees

## How to Compile

1. Open a terminal and navigate to the project directory:
   ```bash
   cd E-Commerce/src
   ```

2. Compile all Java files:
   ```bash
   javac -cp . Main.java
   ```

## How to Run

1. Run the compiled application:
   ```bash
   java Main
   ```

## Test Cases

The application includes three main test scenarios:

1. **Product Addition Tests**: Validates product creation with various edge cases
2. **Customer Creation Tests**: Validates customer creation and balance management
3. **Shopping and Checkout Tests**: Demonstrates complete shopping workflow

## Validation Rules

- **Products**: Non-empty name, non-negative price/quantity/weight, valid expiration dates
- **Customers**: Non-empty name, non-negative balance
- **Cart Operations**: Valid product quantities, sufficient stock availability
- **Checkout**: Non-empty cart, sufficient customer balance