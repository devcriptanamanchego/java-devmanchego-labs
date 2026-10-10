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

public class StreamProgressiveExercises {

    // =========================================================================
    // LEVEL 1: Extract Customer Names
    // Difficulty: ★☆☆☆☆
    // Goal: Get a list of all customer names in uppercase.
    // Hint: map() -> toList()
    // =========================================================================
    public List<String> level1_getUppercaseCustomerNames() {
        return MockDataRepository.getCustomers().stream()
        		.map(Customer::name)
        		.map(String::toUpperCase)
        		.toList();
    }

    // =========================================================================
    // LEVEL 2: Count Cancelled Orders
    // Difficulty: ★☆☆☆☆
    // Goal: Count the total number of orders with status CANCELLED.
    // Hint: filter() -> count()
    // =========================================================================
    public long level2_countCancelledOrders() {
    	return MockDataRepository.getOrders().stream()
    		.filter(order -> order.status() == OrderStatus.CANCELLED)
    		.count();
    }

    // =========================================================================
    // LEVEL 3: Find Products Cheaper Than Threshold
    // Difficulty: ★★☆☆☆
    // Goal: Get all active products priced under $500.00.
    // Hint: filter() -> toList()
    // =========================================================================
    public List<Product> level3_getProductsUnderPrice(BigDecimal threshold) {
    	return MockDataRepository.getProducts().stream()
    		.filter(Product::active)
    		.filter(product -> product.price().compareTo(threshold) < 0)
			.toList();
    }

    // =========================================================================
    // LEVEL 4: Get Customer Emails Joined as String
    // Difficulty: ★★☆☆☆
    // Goal: Join all customer email addresses into a single comma-separated String.
    // Hint: map() -> Collectors.joining(", ")
    // =========================================================================
    public String level4_getJoinedCustomerEmails() {
    	return MockDataRepository.getCustomers().stream()
    			.map(Customer::email)
    			.collect(Collectors.joining(", "));		
    }

    // =========================================================================
    // LEVEL 5: Latest Completed Order
    // Difficulty: ★★★☆☆
    // Goal: Find the most recent COMPLETED order based on its orderDate.
    // Hint: filter() -> max(Comparator.comparing(Order::orderDate))
    // =========================================================================
    public Optional<Order> level5_getLatestCompletedOrder() {
    	return MockDataRepository.getOrders().stream()
    			.filter(order -> order.status() == OrderStatus.COMPLETED)
    			.max(Comparator.comparing(Order::orderDate));
    			
    }

    // =========================================================================
    // LEVEL 6: Total Items Purchased Per Customer
    // Difficulty: ★★★☆☆
    // Goal: Calculate total number of products purchased by a specific customer across all their orders.
    // Hint: filter() -> flatMap() -> count() OR mapToInt() -> sum()
    // =========================================================================
    public long level6_getTotalProductCountByCustomer(Customer customer) {
        return MockDataRepository.getOrders().stream()
        		.filter(order -> customer != null && customer.equals(order.customer()))
        		.flatMap(order -> order.products().stream())
        		.count();
    }

    // =========================================================================
    // LEVEL 7: Map Customer ID to Total Revenue
    // Difficulty: ★★★★☆
    // Goal: Return a Map of Customer ID (String) to their total spent amount on COMPLETED orders.
    // Hint: Collectors.groupingBy(..., Collectors.reducing(...))
    // =========================================================================
    public Map<String, BigDecimal> level7_getRevenuePerCustomerId() {
    	return MockDataRepository.getOrders().stream()
    			.filter(order -> order.status()== OrderStatus.COMPLETED)
    			.collect(Collectors.groupingBy(
                        order -> order.customer().id(),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Order::totalAmount,
                                BigDecimal::add
                        )
                ));
    }

    // =========================================================================
    // LEVEL 8: Most Popular Product Across Completed Orders
    // Difficulty: ★★★★★
    // Goal: Find the Product that appears most frequently across all COMPLETED orders.
    // Hint: filter() -> flatMap() -> Collectors.groupingBy(..., Collectors.counting()) -> max(Map.Entry)
    // =========================================================================
    public Optional<Product> level8_getMostFrequentProductInCompletedOrders() {
    	 return MockDataRepository.getOrders().stream()
            .filter(order -> order.status() == OrderStatus.COMPLETED)
            .flatMap(order -> order.products().stream())
            .collect(Collectors.groupingBy(
                    product -> product,
                    Collectors.counting()
            ))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey);
    }

    // =========================================================================
    // MAIN METHOD: Console execution test runner
    // =========================================================================
    public static void main(String[] args) {
        StreamProgressiveExercises exercises = new StreamProgressiveExercises();

        System.out.println("=== LEVEL 1: Uppercase Customer Names ===");
        try { exercises.level1_getUppercaseCustomerNames().forEach(str -> 
    		System.out.println("- " + str)
        		); 
        } catch (Exception e) { System.out.println("L1 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 2: Cancelled Orders Count ===");
        try { System.out.println("Cancelled Orders: " + exercises.level2_countCancelledOrders()); } 
        catch (Exception e) { System.out.println("L2 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 3: Products Under $500 ===");
        try { exercises.level3_getProductsUnderPrice(new BigDecimal("500.00")).forEach(p -> System.out.println(p.name() + " -> $" + p.price())); } 
        catch (Exception e) { System.out.println("L3 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 4: Joined Customer Emails ===");
        try { System.out.println("Emails: " + exercises.level4_getJoinedCustomerEmails()); } 
        catch (Exception e) { System.out.println("L4 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 5: Latest Completed Order ===");
        try { exercises.level5_getLatestCompletedOrder().ifPresent(o -> System.out.println("Latest Order: " + o.id() + " (" + o.orderDate() + ")")); } 
        catch (Exception e) { System.out.println("L5 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 6: Total Items Purchased by Bob ===");
        try { System.out.println("Bob's Items: " + exercises.level6_getTotalProductCountByCustomer(MockDataRepository.BOB)); } 
        catch (Exception e) { System.out.println("L6 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 7: Revenue Per Customer ID ===");
        try { exercises.level7_getRevenuePerCustomerId().forEach((id, total) -> System.out.println(id + ": $" + total)); } 
        catch (Exception e) { System.out.println("L7 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 8: Most Frequent Product ===");
        try { exercises.level8_getMostFrequentProductInCompletedOrders().ifPresent(p -> System.out.println("Most Popular: " + p.name())); } 
        catch (Exception e) { System.out.println("L8 Pending: " + e.getMessage()); }
    }
}