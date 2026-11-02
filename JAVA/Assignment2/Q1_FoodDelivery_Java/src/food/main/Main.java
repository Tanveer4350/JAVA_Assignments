package food.main;

import food.model.FoodOrder;
import food.model.PremiumOrder;
import food.model.RegularOrder;
import food.service.OrderService;
import food.utility.OrderUtility;

public class Main {
    public static void main(String[] args) {

        OrderService service = new OrderService();

        // i. create at least six orders
        // ii. store them using an array of FoodOrder (this shows POLYMORPHISM:
        //     the array type is the parent class, but each slot holds a child object)
        FoodOrder[] orders = new FoodOrder[6];

        orders[0] = new RegularOrder(1, "Aman Sharma", 450.0);
        orders[1] = new PremiumOrder(2, "Riya Kapoor", 900.0);
        orders[2] = new RegularOrder(3, "Karan Mehta", 300.0);
        orders[3] = new PremiumOrder(4, "Simran Kaur", 1200.0);
        orders[4] = new RegularOrder(5, "Neha Verma", 550.0);
        orders[5] = new PremiumOrder(6, "Vikram Singh", 750.0);

        System.out.println("===== ONLINE FOOD DELIVERY - ORDER BILLS =====");

        for (FoodOrder order : orders) {

            // validate before printing anything, using the utility class
            if (!OrderUtility.validateAmount(order.getAmount())
                    || !OrderUtility.validateCustomerName(order.getCustomerName())) {
                System.out.println("Invalid order skipped: " + order.getOrderId());
                continue;
            }

            // iii, iv, v, vi -> bill, discount, delivery charge, final amount
            service.displayBill(order);
        }

        // static method call -> notice we call it on the CLASS, not an object
        System.out.println("\nTotal Orders Created: " + FoodOrder.getTotalOrders());
    }
}
