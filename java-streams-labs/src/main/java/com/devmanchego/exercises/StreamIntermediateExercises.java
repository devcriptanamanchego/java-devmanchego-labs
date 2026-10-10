package com.devmanchego.exercises;

import com.devmanchego.data.MockDataRepository;
import com.devmanchego.model.Category;
import com.devmanchego.model.Customer;
import com.devmanchego.model.Order;
import com.devmanchego.model.OrderStatus;
import com.devmanchego.model.Product;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamIntermediateExercises {

    // =========================================================================
    // LEVEL 1: Partition Products by Availability
    // Difficulty: ★★★☆☆
    // Goal: Partition active vs inactive products into a Map<Boolean, List<Product>>.
    // Hint: Collectors.partitioningBy(Product::active)
    // =========================================================================
    public Map<Boolean, List<Product>> level1_partitionProductsByActiveStatus() {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // =========================================================================
    // LEVEL 2: Unique Categories Purchased by Customer
    // Difficulty: ★★★☆☆
    // Goal: Find all unique categories a given Customer has purchased across all COMPLETED orders.
    // Hint: filter() -> flatMap() -> map() -> collect(Collectors.toSet())
    // =========================================================================
    public Set<Category> level2_getUniqueCategoriesPurchasedByCustomer(Customer customer) {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // =========================================================================
    // LEVEL 3: Total Revenue per Category
    // Difficulty: ★★★☆☆
    // Goal: Calculate total revenue generated per product Category across all COMPLETED orders.
    // Hint: filter() -> flatMap() -> Collectors.groupingBy(Product::category, Collectors.reducing(...))
    // =========================================================================
    public Map<Category, BigDecimal> level3_getTotalRevenuePerCategory() {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // =========================================================================
    // LEVEL 4: Average Products per Order
    // Difficulty: ★★★★☆
    // Goal: Calculate the average number of products contained in COMPLETED orders.
    // Hint: filter() -> mapToInt(order -> order.products().size()) -> average()
    // =========================================================================
    public Double level4_getAverageProductsPerCompletedOrder() {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // =========================================================================
    // LEVEL 5: Customers with Spending Above Threshold
    // Difficulty: ★★★★☆
    // Goal: Find all customers whose total spending on COMPLETED orders exceeds a given threshold.
    // Hint: Group total revenue per Customer, then filter entries >= threshold
    // =========================================================================
    public List<Customer> level5_getCustomersSpendingMoreThan(BigDecimal threshold) {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // =========================================================================
    // LEVEL 6: Group Order IDs by Status
    // Difficulty: ★★★★☆
    // Goal: Return a Map where key is OrderStatus and value is a List of Order IDs (String).
    // Hint: Collectors.groupingBy(Order::status, Collectors.mapping(Order::id, Collectors.toList()))
    // =========================================================================
    public Map<OrderStatus, List<String>> level6_getOrderIdsGroupedByStatus() {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // =========================================================================
    // LEVEL 7: Most Expensive Product per Category
    // Difficulty: ★★★★★
    // Goal: Find the most expensive product within each product Category.
    // Hint: Collectors.groupingBy(Product::category, Collectors.maxBy(Comparator.comparing(Product::price)))
    // =========================================================================
    public Map<Category, Optional<Product>> level7_getMostExpensiveProductPerCategory() {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // =========================================================================
    // LEVEL 8: Product Price Summary (Min and Max in One Pass)
    // Difficulty: ★★★★★
    // Goal: Find both the min and max priced active product simultaneously using Collectors.teeing.
    // Hint: Collectors.teeing(Collectors.minBy(...), Collectors.maxBy(...), (min, max) -> ...)
    // =========================================================================
    public record PriceRange(Optional<Product> cheapest, Optional<Product> mostExpensive) {}

    public PriceRange level8_getActiveProductsPriceRange() {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // =========================================================================
    // MAIN METHOD: Console execution test runner
    // =========================================================================
    public static void main(String[] args) {
        StreamIntermediateExercises exercises = new StreamIntermediateExercises();

        System.out.println("=== LEVEL 1: Partition Products by Status ===");
        try { exercises.level1_partitionProductsByActiveStatus().forEach((active, list) -> System.out.println("Active (" + active + "): " + list.size())); } 
        catch (Exception e) { System.out.println("L1 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 2: Alice's Purchased Categories ===");
        try { System.out.println("Alice Categories: " + exercises.level2_getUniqueCategoriesPurchasedByCustomer(MockDataRepository.ALICE)); } 
        catch (Exception e) { System.out.println("L2 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 3: Total Revenue per Category ===");
        try { exercises.level3_getTotalRevenuePerCategory().forEach((cat, rev) -> System.out.println(cat + ": $" + rev)); } 
        catch (Exception e) { System.out.println("L3 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 4: Average Products per Order ===");
        try { System.out.println("Avg Products: " + exercises.level4_getAverageProductsPerCompletedOrder()); } 
        catch (Exception e) { System.out.println("L4 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 5: Customers Spending > $1000 ===");
        try { exercises.level5_getCustomersSpendingMoreThan(new BigDecimal("1000.00")).forEach(c -> System.out.println(c.name())); } 
        catch (Exception e) { System.out.println("L5 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 6: Order IDs Grouped by Status ===");
        try { exercises.level6_getOrderIdsGroupedByStatus().forEach((status, ids) -> System.out.println(status + ": " + ids)); } 
        catch (Exception e) { System.out.println("L6 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 7: Most Expensive Product per Category ===");
        try { exercises.level7_getMostExpensiveProductPerCategory().forEach((cat, prod) -> System.out.println(cat + ": " + prod.map(Product::name).orElse("None"))); } 
        catch (Exception e) { System.out.println("L7 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 8: Active Products Price Range ===");
        try { 
            PriceRange range = exercises.level8_getActiveProductsPriceRange();
            System.out.println("Cheapest: " + range.cheapest().map(Product::name).orElse("None"));
            System.out.println("Most Expensive: " + range.mostExpensive().map(Product::name).orElse("None"));
        } catch (Exception e) { System.out.println("L8 Pending: " + e.getMessage()); }
    }
}