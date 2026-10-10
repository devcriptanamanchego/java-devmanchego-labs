package com.devmanchego.exercises;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devmanchego.data.MockDataRepository;
import com.devmanchego.model.Customer;
import com.devmanchego.model.Order;
import com.devmanchego.model.OrderStatus;
import com.devmanchego.model.Product;

public class StreamPracticeExercises {

	public static void main(String[] args) {
        StreamPracticeExercises exercises = new StreamPracticeExercises();

        System.out.println("=== EXERCISE 1: Uppercase Customer Emails ===");
        exercises.exercise1_getUppercaseCustomerEmails().forEach(email -> System.out.println("Email: " + email));

        System.out.println("\n=== EXERCISE 2: Most Expensive Product ===");
        exercises.exercise2_getMostExpensiveProduct()
                .ifPresentOrElse(
                        product -> System.out.println("Top Product: " + product.name() + " ($" + product.price() + ")"),
                        () -> System.out.println("No products found")
                );

        System.out.println("\n=== EXERCISE 3: Total Spent by Alice ===");
        Customer alice = MockDataRepository.ALICE;
        BigDecimal totalSpentAlice = exercises.exercise3_getTotalSpentByCustomer(alice);
        System.out.println("Total spent by " + alice.name() + ": $" + totalSpentAlice);

        System.out.println("\n=== EXERCISE 4: Product Count by Category ===");
        exercises.exercise4_countProductsByCategory()
                .forEach((category, count) -> System.out.println(category + ": " + count + " product(s)"));

        System.out.println("\n=== EXERCISE 5: Completed Orders Grouped by Customer ===");
        exercises.exercise5_groupCompletedOrdersByCustomer().forEach((customer, orders) -> {
            System.out.println("Customer: " + customer.name());
            orders.forEach(order -> System.out.println("  - Order ID: " + order.id() + " | Total: $" + order.totalAmount()));
        });

        System.out.println("\n=== EXERCISE 6: Has Any High-Value Product (>$2000)? ===");
        boolean hasHighValue = exercises.exercise6_hasHighValueProductInAnyOrder();
        System.out.println("Has product > $2,000: " + hasHighValue);

        System.out.println("\n=== EXERCISE 7: Average Value of Completed Orders ===");
        Double avgValue = exercises.exercise7_getAverageCompletedOrderValue();
        System.out.printf("Average Completed Order Value: $%.2f%n", avgValue);

        System.out.println("\n=== EXERCISE 8: Top Spending Customer Overall ===");
        exercises.exercise8_getTopSpendingCustomer()
                .ifPresentOrElse(
                        topCustomer -> System.out.println("Top Customer: " + topCustomer.name() + " (" + topCustomer.email() + ")"),
                        () -> System.out.println("No top customer found")
                );
    }
	
	
    // =========================================================================
    // EXERCISE 1: Find Customer Emails
    // Goal: Return a list of all customer email addresses in uppercase.
    // =========================================================================
    public List<String> exercise1_getUppercaseCustomerEmails() {
    	 return MockDataRepository.getCustomers().stream()
    			 .map(Customer::email)
    			 .map(String::toUpperCase)
    			 .toList();
    }

    // =========================================================================
    // EXERCISE 2: Top Expensive Product
    // Goal: Find the most expensive product available using Streams (max/comparator).
    // =========================================================================
    public Optional<Product> exercise2_getMostExpensiveProduct() {
    /*	return MockDataRepository.getProducts().stream()
    		.sorted(Comparator.comparing(Product::price).reversed())
    		.findFirst();
    		*/
    	return MockDataRepository.getProducts().stream()
    			.max(Comparator.comparing(Product::price));
    	
    }

    // =========================================================================
    // EXERCISE 3: Total Spent by Customer
    // Goal: Calculate the total amount spent by a specific customer across all their COMPLETED orders.
    // =========================================================================
    public BigDecimal exercise3_getTotalSpentByCustomer(Customer customer) {
    	return MockDataRepository.getOrders().stream()
    			 .filter(order -> customer.equals(order.customer()))
    			 .filter(order -> order.status() == OrderStatus.COMPLETED)
    			 .map(Order::totalAmount)
    	         .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // =========================================================================
    // EXERCISE 4: Count Products Per Category
    // Goal: Return a Map with the count of products in each category.
    // =========================================================================
    public Map<Enum<?>, Long> exercise4_countProductsByCategory() {
        return MockDataRepository.getProducts().stream()
        		.collect(Collectors.groupingBy(
                        	Product::category,
                        	Collectors.counting()));
    }

    // =========================================================================
    // EXERCISE 5: Customer Orders Summary
    // Goal: Group all COMPLETED orders by Customer.
    // =========================================================================
    public Map<Customer, List<Order>> exercise5_groupCompletedOrdersByCustomer() {
        return MockDataRepository.getOrders().stream()
        		.filter(order -> order.status() == OrderStatus.COMPLETED)
        		.collect(Collectors.groupingBy(Order::customer));
    }

    // =========================================================================
    // EXERCISE 6: Check Order Threshold
    // Goal: Check if ANY order contains a product with a price greater than $2,000.
    // =========================================================================
    public boolean exercise6_hasHighValueProductInAnyOrder() {
    	return MockDataRepository.getOrders().stream()
                .anyMatch(order -> order.products().stream()
                        .anyMatch(product -> product.price().compareTo(BigDecimal.valueOf(2000)) > 0));
    }

    // =========================================================================
    // EXERCISE 7: Average Order Value
    // Goal: Calculate the average total amount of ALL completed orders.
    // =========================================================================
    public Double exercise7_getAverageCompletedOrderValue() {
    	return MockDataRepository.getOrders().stream()
    			.filter(order -> order.status() == OrderStatus.COMPLETED)
    			.map(Order::totalAmount)
    			.mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0.0);
    }

    // =========================================================================
    // EXERCISE 8: Top Spent Customer (Java 21 Sequenced Collections)
    // Goal: Return the Customer who has spent the most money overall.
    // =========================================================================
    public Optional<Customer> exercise8_getTopSpendingCustomer() {
    	return MockDataRepository.getOrders().stream()
                .collect(Collectors.groupingBy(
                        Order::customer,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Order::totalAmount,
                                BigDecimal::add
                        )
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}