package com.devmanchego.exercises;

import com.devmanchego.data.MockDataRepository;
import com.devmanchego.model.Order;
import com.devmanchego.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StreamProgressiveExercisesTest {

    private StreamProgressiveExercises exercises;

    @BeforeEach
    void setUp() {
        exercises = new StreamProgressiveExercises();
    }

    @Test
    @DisplayName("Level 1: Should return customer names in uppercase")
    void testLevel1() {
        List<String> names = exercises.level1_getUppercaseCustomerNames();
        assertNotNull(names, "Names list should not be null");
        assertEquals(3, names.size());
        assertTrue(names.containsAll(List.of("ALICE SMITH", "BOB JONES", "CHARLIE BROWN")));
    }

    @Test
    @DisplayName("Level 2: Should count cancelled orders")
    void testLevel2() {
        long cancelledCount = exercises.level2_countCancelledOrders();
        assertEquals(1L, cancelledCount, "Should find 1 cancelled order (ORD-004)");
    }

    @Test
    @DisplayName("Level 3: Should find active products cheaper than threshold")
    void testLevel3() {
        List<Product> cheapProducts = exercises.level3_getProductsUnderPrice(new BigDecimal("500.00"));
        assertNotNull(cheapProducts);
        assertEquals(3, cheapProducts.size(), "Should find Sony Headphones, Java Book, and Espresso Machine");
        assertTrue(cheapProducts.stream().allMatch(p -> p.price().compareTo(new BigDecimal("500.00")) < 0));
    }

    @Test
    @DisplayName("Level 4: Should return comma-separated customer emails")
    void testLevel4() {
        String emails = exercises.level4_getJoinedCustomerEmails();
        assertNotNull(emails);
        assertTrue(emails.contains("alice@example.com"));
        assertTrue(emails.contains("bob@example.com"));
        assertTrue(emails.contains("charlie@example.com"));
        assertTrue(emails.contains(", "));
    }

    @Test
    @DisplayName("Level 5: Should find latest completed order")
    void testLevel5() {
        Optional<Order> latestOrder = exercises.level5_getLatestCompletedOrder();
        assertTrue(latestOrder.isPresent());
        assertEquals("ORD-005", latestOrder.get().id());
        assertEquals(LocalDate.of(2026, 5, 12), latestOrder.get().orderDate());
    }

    @Test
    @DisplayName("Level 6: Should calculate total product count for a customer")
    void testLevel6() {
        long count = exercises.level6_getTotalProductCountByCustomer(MockDataRepository.BOB);
        assertEquals(4L, count, "Bob has ORD-002 (2 products) and ORD-005 (2 products)");
    }

    @Test
    @DisplayName("Level 7: Should calculate revenue per customer ID")
    void testLevel7() {
        Map<String, BigDecimal> revenueMap = exercises.level7_getRevenuePerCustomerId();
        assertNotNull(revenueMap);
        assertEquals(new BigDecimal("2899.49"), revenueMap.get("C01"), "Alice (C01) revenue");
        assertEquals(new BigDecimal("689.43"), revenueMap.get("C02"), "Bob (C02) revenue");
    }

    @Test
    @DisplayName("Level 8: Should find most frequent product in completed orders")
    void testLevel8() {
        Optional<Product> mostPopular = exercises.level8_getMostFrequentProductInCompletedOrders();
        assertTrue(mostPopular.isPresent());
        // Sony Headphones (P103) and Espresso Machine (P301) appear 2 times each in completed orders
        assertTrue(
            mostPopular.get().id().equals("P103") || mostPopular.get().id().equals("P301"),
            "Most popular product should be Headphones or Espresso Machine"
        );
    }
}