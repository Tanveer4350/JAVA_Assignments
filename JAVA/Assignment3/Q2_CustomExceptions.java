/**
 * Q2: To develop custom exceptions and use debugging techniques to
 * identify and correct errors in a Java application.
 *
 * This program demonstrates:
 * 1. Creating custom checked exceptions
 * 2. Creating custom unchecked exceptions
 * 3. Using custom exceptions in a real-world scenario (Bank Account)
 * 4. Debugging techniques: stack traces, exception chaining, assertions
 */

public class Q2_CustomExceptions {

    // -----------------------------------------------------------------------
    // CUSTOM CHECKED EXCEPTION: InsufficientFundsException
    // -----------------------------------------------------------------------
    static class InsufficientFundsException extends Exception {
        private double amount;

        public InsufficientFundsException(double amount) {
            super(String.format("Insufficient funds! Tried to withdraw: %.2f", amount));
            this.amount = amount;
        }

        public double getAmount() {
            return amount;
        }
    }

    // -----------------------------------------------------------------------
    // CUSTOM CHECKED EXCEPTION: InvalidAgeException
    // -----------------------------------------------------------------------
    static class InvalidAgeException extends Exception {
        private int age;

        public InvalidAgeException(int age) {
            super("Invalid age: " + age + ". Age must be between 0 and 150.");
            this.age = age;
        }

        public int getAge() {
            return age;
        }
    }

    // -----------------------------------------------------------------------
    // CUSTOM UNCHECKED EXCEPTION: InvalidAccountException
    // -----------------------------------------------------------------------
    static class InvalidAccountException extends RuntimeException {
        private String accountId;

        public InvalidAccountException(String accountId) {
            super("Invalid account ID: '" + accountId + "'");
            this.accountId = accountId;
        }

        public String getAccountId() {
            return accountId;
        }
    }

    // -----------------------------------------------------------------------
    // CUSTOM UNCHECKED EXCEPTION: NegativeAmountException
    // -----------------------------------------------------------------------
    static class NegativeAmountException extends RuntimeException {
        public NegativeAmountException(double amount) {
            super("Amount cannot be negative: " + amount);
        }
    }

    // -----------------------------------------------------------------------
    // CUSTOM EXCEPTION WITH CHAINING: TransactionFailedException
    // -----------------------------------------------------------------------
    static class TransactionFailedException extends Exception {
        public TransactionFailedException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // -----------------------------------------------------------------------
    // BANK ACCOUNT CLASS
    // -----------------------------------------------------------------------
    static class BankAccount {
        private String accountId;
        private String owner;
        private double balance;

        public BankAccount(String accountId, String owner, double initialBalance) {
            if (accountId == null || accountId.trim().isEmpty()) {
                throw new InvalidAccountException(accountId == null ? "null" : accountId);
            }
            if (initialBalance < 0) {
                throw new NegativeAmountException(initialBalance);
            }
            this.accountId = accountId;
            this.owner = owner;
            this.balance = initialBalance;
        }

        public void deposit(double amount) throws TransactionFailedException {
            try {
                if (amount <= 0) {
                    throw new NegativeAmountException(amount);
                }
                balance += amount;
                System.out.printf("  Deposited: %.2f | New Balance: %.2f%n", amount, balance);
            } catch (NegativeAmountException e) {
                throw new TransactionFailedException("Deposit failed for account: " + accountId, e);
            }
        }

        public void withdraw(double amount) throws InsufficientFundsException, TransactionFailedException {
            try {
                if (amount <= 0) {
                    throw new NegativeAmountException(amount);
                }
                if (amount > balance) {
                    throw new InsufficientFundsException(amount);
                }
                balance -= amount;
                System.out.printf("  Withdrawn: %.2f | New Balance: %.2f%n", amount, balance);
            } catch (NegativeAmountException e) {
                throw new TransactionFailedException("Withdrawal failed for account: " + accountId, e);
            }
        }

        public double getBalance() { return balance; }
        public String getAccountId() { return accountId; }

        @Override
        public String toString() {
            return String.format("Account[%s | Owner: %s | Balance: %.2f]", accountId, owner, balance);
        }
    }

    // -----------------------------------------------------------------------
    // PERSON CLASS
    // -----------------------------------------------------------------------
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) throws InvalidAgeException {
            if (age < 0 || age > 150) {
                throw new InvalidAgeException(age);
            }
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person[name=" + name + ", age=" + age + "]";
        }
    }

    // -----------------------------------------------------------------------
    // DEBUGGING: Stack trace demo
    // -----------------------------------------------------------------------
    static void debugWithStackTrace() {
        try {
            BankAccount acc = new BankAccount("ACC001", "Alice", 500.0);
            acc.withdraw(1000.0);
        } catch (InsufficientFundsException e) {
            System.out.println("  [DEBUG] Exception caught: " + e.getMessage());
            System.out.println("  [DEBUG] Amount attempted: " + e.getAmount());
            System.out.println("  [DEBUG] Stack Trace:");
            e.printStackTrace(System.out);
        } catch (TransactionFailedException e) {
            System.out.println("  Transaction failed: " + e.getMessage());
        }
    }

    // -----------------------------------------------------------------------
    // DEBUGGING: Assertions demo (enable with: java -ea Q2_CustomExceptions)
    // -----------------------------------------------------------------------
    static void debugWithAssertions(int value) {
        assert value > 0 : "Value must be positive, got: " + value;
        System.out.println("  Value is valid: " + value);
    }

    // -----------------------------------------------------------------------
    // MAIN METHOD
    // -----------------------------------------------------------------------
    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("  Q2: Custom Exceptions and Debugging Demo");
        System.out.println("==============================================\n");

        // --- 1. InsufficientFundsException ---
        System.out.println("--- 1. Custom Checked: InsufficientFundsException ---");
        try {
            BankAccount acc = new BankAccount("ACC001", "Alice", 500.0);
            System.out.println("  Created: " + acc);
            acc.deposit(200.0);
            acc.withdraw(100.0);
            System.out.println("  Attempting to withdraw 1000.0...");
            acc.withdraw(1000.0);
        } catch (InsufficientFundsException e) {
            System.out.println("  Caught InsufficientFundsException: " + e.getMessage());
            System.out.printf("  Attempted withdrawal: %.2f%n", e.getAmount());
        } catch (TransactionFailedException e) {
            System.out.println("  Transaction Failed: " + e.getMessage());
        }

        // --- 2. InvalidAgeException ---
        System.out.println("\n--- 2. Custom Checked: InvalidAgeException ---");
        try {
            Person p1 = new Person("Bob", 25);
            System.out.println("  Created: " + p1);
            Person p2 = new Person("Charlie", -5);
            System.out.println("  Created: " + p2);
        } catch (InvalidAgeException e) {
            System.out.println("  Caught InvalidAgeException: " + e.getMessage());
            System.out.println("  Invalid age: " + e.getAge());
        }

        // --- 3. InvalidAccountException ---
        System.out.println("\n--- 3. Custom Unchecked: InvalidAccountException ---");
        try {
            BankAccount acc = new BankAccount("", "Dave", 1000.0);
        } catch (InvalidAccountException e) {
            System.out.println("  Caught InvalidAccountException: " + e.getMessage());
            System.out.println("  Account ID was: '" + e.getAccountId() + "'");
        }

        // --- 4. NegativeAmountException ---
        System.out.println("\n--- 4. Custom Unchecked: NegativeAmountException ---");
        try {
            BankAccount acc = new BankAccount("ACC002", "Eve", -500.0);
        } catch (NegativeAmountException e) {
            System.out.println("  Caught NegativeAmountException: " + e.getMessage());
        }

        // --- 5. Exception Chaining ---
        System.out.println("\n--- 5. Exception Chaining: TransactionFailedException ---");
        try {
            BankAccount acc = new BankAccount("ACC003", "Frank", 200.0);
            System.out.println("  Created: " + acc);
            System.out.println("  Depositing negative amount (-50.0)...");
            acc.deposit(-50.0);
        } catch (TransactionFailedException e) {
            System.out.println("  Caught TransactionFailedException: " + e.getMessage());
            System.out.println("  Root Cause: " + e.getCause().getMessage());
        }

        // --- 6. Debugging with Stack Trace ---
        System.out.println("\n--- 6. Debugging: Stack Trace ---");
        debugWithStackTrace();

        // --- 7. Multiple accounts test ---
        System.out.println("\n--- 7. Multiple Account Creation Test ---");
        String[] ids = {"ACC004", "", "ACC005"};
        for (String id : ids) {
            try {
                BankAccount acc = new BankAccount(id, "Test User", 100.0);
                System.out.println("  Created: " + acc);
            } catch (InvalidAccountException e) {
                System.out.println("  Failed for ID='" + id + "': " + e.getMessage());
            }
        }

        // --- 8. Assertions ---
        System.out.println("\n--- 8. Debugging with Assertions (run with java -ea) ---");
        try {
            debugWithAssertions(10);
            debugWithAssertions(-1);
        } catch (AssertionError e) {
            System.out.println("  AssertionError: " + e.getMessage());
        }

        // --- Summary ---
        System.out.println("\n==============================================");
        System.out.println("  Custom Exception Hierarchy Created:");
        System.out.println("==============================================");
        System.out.println("Exception (Checked)");
        System.out.println("  |-- InsufficientFundsException");
        System.out.println("  |-- InvalidAgeException");
        System.out.println("  |-- TransactionFailedException");
        System.out.println("");
        System.out.println("RuntimeException (Unchecked)");
        System.out.println("  |-- InvalidAccountException");
        System.out.println("  |-- NegativeAmountException");
        System.out.println("==============================================");
    }
}