package Product;

import java.time.LocalDate;

import Interfaces.IShippable;
import Validators.ValidationException;
import Validators.Validator;

public class ShippableProduct extends Product implements IShippable {
    static double costPerGram = 0.005;
    private double weight;

    public ShippableProduct(String name, double price, int quantity, double weight) throws ValidationException {
        super(name, price, quantity);
        this.weight = weight;
        Validator.validate(this);
    }

    public ShippableProduct(String name, double price, int quantity, double weight, LocalDate expirationDate)
            throws ValidationException {
        super(name, price, quantity, expirationDate);
        this.weight = weight;
        Validator.validate(this);
    }

    @Override
    public String getName(){
        return super.getName();
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public double calculateShippingCost() {
        return weight * costPerGram;
    }
}
