package food.service;

import food.model.Discountable;
import food.model.FoodOrder;
import food.utility.OrderUtility;

/**
 * Service class: contains the "business logic" of the application.
 * It takes an order and prints out the full bill for it.
 */
public class OrderService {

    public void displayBill(FoodOrder order) {

        System.out.println("--------------------------------------------------");
        System.out.println(OrderUtility.generateOrderSummary(order));

        double deliveryCharge = order.calculateDeliveryCharge();
        double discount = 0.0;

        // an order "IS-A" Discountable only if its class implements the interface,
        // so we check before calling applyDiscount()
        if (order instanceof Discountable) {
            Discountable discountableOrder = (Discountable) order;
            discount = discountableOrder.applyDiscount();
        }

        double finalAmount = order.getAmount() - discount + deliveryCharge;

        System.out.printf("Bill Amount        : Rs. %.2f%n", order.getAmount());
        System.out.printf("Discount Applied    : Rs. %.2f%n", discount);
        System.out.printf("Delivery Charge     : Rs. %.2f%n", deliveryCharge);
        System.out.printf("Final Payable Amount: Rs. %.2f%n", finalAmount);
        System.out.println("--------------------------------------------------");
    }
}
