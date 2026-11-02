package food.model;

/**
 * A premium / priority food order.
 * Delivery charge = Rs. 50 (fixed, cheaper because customer pays for premium membership)
 * Discount        = 15% of the bill amount
 */
public class PremiumOrder extends FoodOrder implements Discountable {

    private static final double DELIVERY_CHARGE = 50.0;
    private static final double DISCOUNT_PERCENT = 15.0;

    public PremiumOrder(int orderId, String customerName, double amount) {
        super(orderId, customerName, amount);
    }

    @Override
    public double calculateDeliveryCharge() {
        return DELIVERY_CHARGE;
    }

    @Override
    public double applyDiscount() {
        return getAmount() * DISCOUNT_PERCENT / 100.0;
    }
}
