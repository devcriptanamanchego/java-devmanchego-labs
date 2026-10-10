package com.devmanchego.exercises;

import com.devmanchego.data.MockDataRepository;
import com.devmanchego.model.Category;
import com.devmanchego.model.Customer;
import com.devmanchego.model.OrderStatus;
import com.devmanchego.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StreamIntermediateExercisesTest {

    private StreamIntermediateExercises exercises;

    @BeforeEach
    void setUp() {
        exercises = new StreamIntermediateExercises();
    }

    @Test
    @DisplayName("Level 1: Should partition products into active (5) and inactive (1)")
    @Disabled("Pending implementation. Feature under development.")
    @Tag("wip")
    @Tag("streams")
    void testLevel1() {
        Map<Boolean, List<Product>> partitioned = exercises.level1_partitionProductsByActiveStatus();
        assertNotNull(partitioned);
        assertEquals(5, partitioned.get(true).size(), "Should have 5 active products");
        assertEquals(1, partitioned.get(false).size(), "Should have 1 inactive product");
    }

    @Test
    @DisplayName("Level 2: Should get unique categories purchased by Alice")
    @Disabled("Pending implementation. Feature under development.")
    @Tag("wip")
    @Tag("streams")
    void testLevel2() {
        Set<Category> categories = exercises.level2_getUniqueCategoriesPurchasedByCustomer(MockDataRepository.ALICE);
        assertNotNull(categories);
        assertEquals(1, categories.size(), "Alice purchased items from 1 category in COMPLETED orders");
        assertTrue(categories.contains(Category.ELECTRONICS));
    }

    @Test
    @DisplayName("Level 3: Should calculate total revenue per product category")
    @Disabled("Pending implementation. Feature under development.")
    @Tag("wip")
    @Tag("streams")
    void testLevel3() {
        Map<Category, BigDecimal> revenue = exercises.level3_getTotalRevenuePerCategory();
        assertNotNull(revenue);
        assertEquals(new BigDecimal("2899.49"), revenue.get(Category.ELECTRONICS));
        assertEquals(new BigDecimal("89.94"), revenue.get(Category.BOOKS));
        assertEquals(new BigDecimal("599.49"), revenue.get(Category.HOME_GOODS));
    }

    @Test
    @DisplayName("Level 4: Should calculate average products per completed order")
    @Disabled("Pending implementation. Feature under development.")
    @Tag("wip")
    @Tag("streams")
    void testLevel4() {
        Double avgProducts = exercises.level4_getAverageProductsPerCompletedOrder();
        assertNotNull(avgProducts);
        assertEquals(2.0, avgProducts, 0.01, "Each completed order (ORD-001, ORD-002, ORD-005) contains 2 products");
    }

    @Test
    @DisplayName("Level 5: Should find customers spending more than $1000")
    @Disabled("Pending implementation. Feature under development.")
    @Tag("wip")
    @Tag("streams")
    void testLevel5() {
        List<Customer> topSpenders = exercises.level5_getCustomersSpendingMoreThan(new BigDecimal("1000.00"));
        assertNotNull(topSpenders);
        assertEquals(1, topSpenders.size());
        assertEquals("Alice Smith", topSpenders.get(0).name());
    }

    @Test
    @DisplayName("Level 6: Should group order IDs by status")
    @Disabled("Pending implementation. Feature under development.")
    @Tag("wip")
    @Tag("streams")
    void testLevel6() {
        Map<OrderStatus, List<String>> grouped = exercises.level6_getOrderIdsGroupedByStatus();
        assertNotNull(grouped);
        assertEquals(3, grouped.get(OrderStatus.COMPLETED).size());
        assertEquals(1, grouped.get(OrderStatus.PENDING).size());
        assertEquals(1, grouped.get(OrderStatus.CANCELLED).size());
    }

    @Test
    @DisplayName("Level 7: Should find most expensive product per category")
    @Disabled("Pending implementation. Feature under development.")
    @Tag("wip")
    @Tag("streams")
    void testLevel7() {
        Map<Category, Optional<Product>> maxPerCategory = exercises.level7_getMostExpensiveProductPerCategory();
        assertNotNull(maxPerCategory);
        assertTrue(maxPerCategory.get(Category.ELECTRONICS).isPresent());
        assertEquals("MacBook Pro 16\"", maxPerCategory.get(Category.ELECTRONICS).get().name());
        assertEquals("Java 21 in Action", maxPerCategory.get(Category.BOOKS).get().name());
    }

    @Test
    @DisplayName("Level 8: Should find price range using Collectors.teeing")
    @Disabled("Pending implementation. Feature under development.")
    @Tag("wip")
    @Tag("streams")
    void testLevel8() {
        StreamIntermediateExercises.PriceRange priceRange = exercises.level8_getActiveProductsPriceRange();
        assertNotNull(priceRange);
        assertTrue(priceRange.cheapest().isPresent());
        assertTrue(priceRange.mostExpensive().isPresent());
        assertEquals("Java 21 in Action", priceRange.cheapest().get().name()); // $49.99 active
        assertEquals("MacBook Pro 16\"", priceRange.mostExpensive().get().name()); // $2499.99
    }
}