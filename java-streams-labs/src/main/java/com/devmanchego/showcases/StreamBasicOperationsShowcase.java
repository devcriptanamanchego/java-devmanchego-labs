package com.devmanchego.showcases;

import com.devmanchego.data.MockDataRepository;
import com.devmanchego.model.Category;
import com.devmanchego.model.Order;
import com.devmanchego.model.OrderStatus;
import com.devmanchego.model.Product;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamBasicOperationsShowcase {

    public static void main(String[] args) {
        System.out.println("=== 1. Active Products Filtered and Sorted ===");
        getUniqueProductsFromCompletedOrders().forEach(product -> 
        	System.out.println("- " + product.name())
        );

        System.out.println("\n=== 2. Total Sales Revenue for Completed Orders ===");
        System.out.println("Total: $" + calculateTotalCompletedRevenue());

        System.out.println("\n=== 3. Grouping Products by Category ===");
        groupProductsByCategory().forEach((category, products) -> {
            System.out.println(category + ": " + products.stream().map(Product::name).toList());
        });

        System.out.println("\n=== 4. FlatMapping Order Products ===");
        getUniqueProductsFromCompletedOrders().forEach(product -> 
            System.out.println("- " + product.name())
        );
    }

    /**
     * Filters active products, sorts them by price descending, and uses Java 16 .toList()
     */
    public static List<Product> getFilterAndSortActiveProducts() {
        return MockDataRepository.getProducts().stream()
                .filter(Product::active)
                .sorted(Comparator.comparing(Product::price).reversed())
                .toList(); // Java 16+ direct unmodifiable list
    }

    /**
     * Calculates sum of all completed orders using map, reduce, and BigDecimal
     */
    public static BigDecimal calculateTotalCompletedRevenue() {
        return MockDataRepository.getOrders().stream()
                .filter(order -> order.status() == OrderStatus.COMPLETED)
                .map(Order::totalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Groups products by their Category enum using Collectors.groupingBy
     */
    public static Map<Category, List<Product>> groupProductsByCategory() {
        return MockDataRepository.getProducts().stream()
                .collect(Collectors.groupingBy(Product::category));
    }

    /**
     * Extracts all products from completed orders using flatMap to flatten nested lists
     */
    public static List<Product> getUniqueProductsFromCompletedOrders() {
        return MockDataRepository.getOrders().stream()
                .filter(order -> order.status() == OrderStatus.COMPLETED)
                .flatMap(order -> order.products().stream())
                .distinct()
                .toList();
    }
}