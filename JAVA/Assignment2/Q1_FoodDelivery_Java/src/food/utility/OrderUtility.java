package food.utility;

import food.model.FoodOrder;

/**
 * Utility (helper) class with static methods used to validate
 * order data and build a readable summary string.
 * It does not need to be instantiated, so all methods are static.
 */
public class OrderUtility {

    // amount must be greater than zero to be considered valid
    public static boolean validateAmount(double amount) {
        return amount > 0;
    }

    // customer name must not be null or empty/blank
    public static boolean validateCustomerName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    // builds a nicely formatted one-line summary for any FoodOrder
    public static String generateOrderSummary(FoodOrder order) {
        return "Order #" + order.getOrderId()
                + " | Customer: " + order.getCustomerName()
                + " | Amount: Rs." + order.getAmount()
                + " | Restaurant: " + FoodOrder.getRestaurantName();
    }
}
