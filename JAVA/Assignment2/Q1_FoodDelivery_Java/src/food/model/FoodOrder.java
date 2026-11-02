package food.model;

/**
 * Abstract class representing a generic food order.
 * Every concrete order type (Regular, Premium) must extend this class.
 */
public abstract class FoodOrder {

    // ---------- private data members ----------
    private int orderId;
    private String customerName;
    private double amount;

    // static member shared by ALL orders (belongs to the class, not an object)
    private static String restaurantName = "Tasty Bites Restaurant";

    // static counter to keep track of how many orders have been created so far
    private static int orderCounter = 0;

    // ---------- constructor ----------
    public FoodOrder(int orderId, String customerName, double amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        orderCounter++;              // every time a new order object is built, increase the counter
    }

    // ---------- getters and setters ----------
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static String getRestaurantName() {
        return restaurantName;
    }

    public static void setRestaurantName(String restaurantName) {
        FoodOrder.restaurantName = restaurantName;
    }

    // static method -> can be called using the class name, e.g. FoodOrder.getTotalOrders()
    public static int getTotalOrders() {
        return orderCounter;
    }

    // ---------- abstract method ----------
    // every subclass MUST provide its own version of this method
    public abstract double calculateDeliveryCharge();
}
