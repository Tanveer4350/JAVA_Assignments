package food.model;

/**
 * A normal / standard food order.
 * Delivery charge = Rs. 80 (fixed)
 * Discount        = 10% of the bill amount
 */
public class RegularOrder extends FoodOrder implements Discountable {

    private static final double DELIVERY_CHARGE = 80.0;
    private static final double DISCOUNT_PERCENT = 10.0;

    public RegularOrder(int orderId, String customerName, double amount) {
        super(orderId, customerName, amount); // call the parent (FoodOrder) constructor
    }

    // overriding the abstract method from FoodOrder
    @Override
    public double calculateDeliveryCharge() {
        return DELIVERY_CHARGE;
    }

    // implementing the method promised by the Discountable interface
    @Override
    public double applyDiscount() {
        return getAmount() * DISCOUNT_PERCENT / 100.0;
    }
}
