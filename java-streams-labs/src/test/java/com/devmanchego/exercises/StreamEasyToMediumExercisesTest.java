package com.devmanchego.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.devmanchego.model.Category;
import com.devmanchego.model.Product;

class StreamEasyToMediumExercisesTest {

    private StreamEasyToMediumExercises exercises;

    @BeforeEach
    void setUp() {
        exercises = new StreamEasyToMediumExercises();
    }

    @Test
    @DisplayName("Level 1: Should return active product names")
    void testLevel1() {
        List<String> activeNames = exercises.level1_getActiveProductNames();
        assertNotNull(activeNames, "Active names list should not be null");
        assertEquals(5, activeNames.size(), "Should find exactly 5 active products");
        assertFalse(activeNames.contains("Clean Architecture"), "Should not include inactive products");
    }

    @Test
    @DisplayName("Level 2: Should count customers from Germany")
    void testLevel2() {
        long count = exercises.level2_countCustomersFromCountry("Germany");
        assertEquals(1L, count, "Should find exactly 1 customer from Germany");
    }

    @Test
    @DisplayName("Level 3: Should sort products by price ascending")
    void testLevel3() {
        List<Product> sorted = exercises.level3_getProductsSortedByPriceAscending();
        assertNotNull(sorted, "Sorted list should not be null");
        assertEquals(6, sorted.size(), "Should include all 6 products");
        assertEquals(new BigDecimal("39.95"), sorted.get(0).price(), "Cheapest product should be first");
        assertEquals(new BigDecimal("2499.99"), sorted.get(5).price(), "Most expensive product should be last");
    }

    @Test
    @DisplayName("Level 4: Should find product by ID")
    void testLevel4() {
        Optional<Product> product = exercises.level4_findProductById("P102");
        assertTrue(product.isPresent(), "Product P102 should be present");
        assertEquals("iPhone 15 Pro", product.get().name());
    }

    @Test
    @DisplayName("Level 5: Should get completed order IDs")
    void testLevel5() {
        List<String> completedIds = exercises.level5_getCompletedOrderIds();
        assertNotNull(completedIds, "Order IDs list should not be null");
        assertEquals(3, completedIds.size(), "Should find 3 completed orders");
        assertTrue(completedIds.containsAll(List.of("ORD-001", "ORD-002", "ORD-005")));
    }

    @Test
    @DisplayName("Level 6: Should get books sorted alphabetically")
    void testLevel6() {
        List<Product> books = exercises.level6_getBooksSortedByName();
        assertNotNull(books, "Books list should not be null");
        assertEquals(2, books.size(), "Should find 2 books");
        assertEquals("Clean Architecture", books.get(0).name(), "Clean Architecture should come first alphabetically");
        assertEquals("Java 21 in Action", books.get(1).name(), "Java 21 in Action should come second alphabetically");
    }

    
    
    @Test
    @DisplayName("Level 7: Should group product names by category")    
    @Disabled("Pending implementation. Feature under development.")
    @Tag("wip")
    @Tag("streams")
    void testLevel7() {
        Map<Category, List<String>> grouped = exercises.level7_getProductNamesGroupedByCategory();
        assertNotNull(grouped, "Grouped map should not be null");
        assertTrue(grouped.get(Category.BOOKS).contains("Java 21 in Action"));
        assertEquals(3, grouped.get(Category.ELECTRONICS).size(), "Should have 3 electronics products");
    }

    @Test
    @DisplayName("Level 8: Should find cheapest product in completed orders")
    @Disabled("Pending implementation. Feature under development.")
    @Tag("wip")
    @Tag("streams")
    void testLevel8() {
        Optional<Product> cheapest = exercises.level8_getCheapestProductInCompletedOrders();
        assertTrue(cheapest.isPresent(), "Cheapest product should be present");
        assertEquals("Clean Architecture", cheapest.get().name(), "Cheapest product should be Clean Architecture ($39.95)");
    }
}