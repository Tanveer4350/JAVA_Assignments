public class Q1_GenericSum {

    public static <T extends Number> double sum(T[] array) {
        double total = 0;

        for (T value : array) {
            total += value.doubleValue();
        }

        return total;
    }

    public static void main(String[] args) {

        Integer[] integers = {10, 20, 30, 40};
        Float[] floats = {10.5f, 20.5f, 30.5f};
        Double[] doubles = {1.2, 2.4, 3.6};

        System.out.println("Sum of Integers: " + sum(integers));
        System.out.println("Sum of Floats: " + sum(floats));
        System.out.println("Sum of Doubles: " + sum(doubles));
    }
}