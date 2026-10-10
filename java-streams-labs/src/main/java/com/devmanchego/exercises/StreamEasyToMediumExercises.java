package com.devmanchego.exercises;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.devmanchego.data.MockDataRepository;
import com.devmanchego.model.Category;
import com.devmanchego.model.Order;
import com.devmanchego.model.OrderStatus;
import com.devmanchego.model.Product;

public class StreamEasyToMediumExercises {

    // =========================================================================
    // LEVEL 1: Active Product Names
    // Difficulty: ★☆☆☆☆
    // Goal: Return a list with the names (String) of all active products.
    // Hint: filter() -> map() -> toList()
    // =========================================================================
    public List<String> level1_getActiveProductNames() {
    	return MockDataRepository.getProducts().stream()
    		.filter(product -> product.active() == true)
    		.map(Product::name)
    		.toList();
    }

    // =========================================================================
    // LEVEL 2: Count Customers From Country
    // Difficulty: ★☆☆☆☆
    // Goal: Count how many customers belong to a specific country (e.g. "Germany").
    // Hint: filter() -> count()
    // =========================================================================
    public long level2_countCustomersFromCountry(String country) {
    	return MockDataRepository.getCustomers().stream()
    			.filter(customer -> country != null && country.equals(customer.country())).count();
    }

    // =========================================================================
    // LEVEL 3: Sort Products by Price
    // Difficulty: ★★☆☆☆
    // Goal: Get all products sorted by price in ascending order.
    // Hint: sorted(Comparator.comparing(...))
    // =========================================================================
    public List<Product> level3_getProductsSortedByPriceAscending() {
    	return MockDataRepository.getProducts().stream()
    		.sorted(Comparator.comparing(Product::price)).toList();
    }

    // =========================================================================
    // LEVEL 4: Find Product by ID
    // Difficulty: ★★☆☆☆
    // Goal: Search for a product by its ID (e.g. "P102") and return it as Optional<Product>.
    // Hint: filter() -> findFirst()
    // =========================================================================
    public Optional<Product> level4_findProductById(String productId) {
    	return MockDataRepository.getProducts().stream()
        		.filter(product -> productId != null && productId.equals(product.id()))
        		.findFirst();
        
    }

    // =========================================================================
    // LEVEL 5: Map Completed Order IDs
    // Difficulty: ★★★☆☆
    // Goal: Get a list containing only the IDs (String) of orders with COMPLETED status.
    // Hint: filter() -> map() -> toList()
    // =========================================================================
    public List<String> level5_getCompletedOrderIds() {
    	return MockDataRepository.getOrders().stream()
    			.filter(order -> OrderStatus.COMPLETED == order.status())
    			.map(Order::id)
    			.toList();
    }

    // =========================================================================
    // LEVEL 6: Books Sorted by Name
    // Difficulty: ★★★☆☆
    // Goal: Filter products in the BOOKS category and return them sorted alphabetically by name.
    // Hint: filter() -> sorted() -> toList()
    // =========================================================================
    public List<Product> level6_getBooksSortedByName() {
    	return MockDataRepository.getProducts().stream()
    			.filter(product -> product.category() == Category.BOOKS)
    			.sorted(Comparator.comparing(Product::name)).toList();
    }

    // =========================================================================
    // LEVEL 7: Group Product Names by Category
    // Difficulty: ★★★★☆
    // Goal: Return a Map where the key is the Category and the value is a List of product NAMES (List<String>).
    // Hint: Collectors.groupingBy(..., Collectors.mapping(...))
    // =========================================================================
    public Map<Category, List<String>> level7_getProductNamesGroupedByCategory() {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // =========================================================================
    // LEVEL 8: Cheapest Product in Completed Orders
    // Difficulty: ★★★★★
    // Goal: From all COMPLETED orders, flatten the product lists and find the cheapest product.
    // Hint: filter() -> flatMap() -> min(Comparator)
    // =========================================================================
    public Optional<Product> level8_getCheapestProductInCompletedOrders() {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // =========================================================================
    // MAIN METHOD: Console execution test runner
    // =========================================================================
    public static void main(String[] args) {
        StreamEasyToMediumExercises exercises = new StreamEasyToMediumExercises();

        System.out.println("=== LEVEL 1: Active Product Names ===");
        try { exercises.level1_getActiveProductNames().forEach(str -> 
    		System.out.println("- " + str)
        		);
    	}catch (Exception e) { System.out.println("L1 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 2: German Customers Count ===");
        try { System.out.println("German Customers: " + exercises.level2_countCustomersFromCountry("Germany")); } 
        catch (Exception e) { System.out.println("L2 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 3: Products Sorted by Price ===");
        try { exercises.level3_getProductsSortedByPriceAscending().forEach(p -> System.out.println(p.name() + " -> $" + p.price())); } 
        catch (Exception e) { System.out.println("L3 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 4: Find Product P102 ===");
        try { exercises.level4_findProductById("P102").ifPresent(p -> System.out.println("Found: " + p.name())); } 
        catch (Exception e) { System.out.println("L4 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 5: Completed Order IDs ===");
        try { exercises.level5_getCompletedOrderIds().forEach(id -> System.out.println("Order ID: " + id)); } 
        catch (Exception e) { System.out.println("L5 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 6: Books Sorted by Name ===");
        try { exercises.level6_getBooksSortedByName().forEach(b -> System.out.println("Book: " + b.name())); } 
        catch (Exception e) { System.out.println("L6 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 7: Product Names Grouped by Category ===");
        try { exercises.level7_getProductNamesGroupedByCategory().forEach((cat, names) -> System.out.println(cat + ": " + names)); } 
        catch (Exception e) { System.out.println("L7 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 8: Cheapest Product in Completed Orders ===");
        try { exercises.level8_getCheapestProductInCompletedOrders().ifPresent(p -> System.out.println("Cheapest: " + p.name() + " ($" + p.price() + ")")); } 
        catch (Exception e) { System.out.println("L8 Pending: " + e.getMessage()); }
    }
}