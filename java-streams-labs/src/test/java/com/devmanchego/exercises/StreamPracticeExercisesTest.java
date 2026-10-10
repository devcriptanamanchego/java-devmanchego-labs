package com.devmanchego.exercises;

import com.devmanchego.data.MockDataRepository;
import com.devmanchego.model.Category;
import com.devmanchego.model.Customer;
import com.devmanchego.model.Order;
import com.devmanchego.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StreamPracticeExercisesTest {

    private StreamPracticeExercises exercises;

    @BeforeEach
    void setUp() {
        exercises = new StreamPracticeExercises();
    }

    @Test
    @DisplayName("Exercise 1: Should return all customer emails in uppercase")
    void testExercise1_getUppercaseCustomerEmails() {
        List<String> emails = exercises.exercise1_getUppercaseCustomerEmails();

        assertNotNull(emails, "Result list should not be null");
        assertEquals(3, emails.size(), "Should contain 3 customer emails");
        assertTrue(emails.contains("ALICE@EXAMPLE.COM"));
        assertTrue(emails.contains("BOB@EXAMPLE.COM"));
        assertTrue(emails.contains("CHARLIE@EXAMPLE.COM"));
    }

    @Test
    @DisplayName("Exercise 2: Should find the most expensive product")
    void testExercise2_getMostExpensiveProduct() {
        Optional<Product> product = exercises.exercise2_getMostExpensiveProduct();

        assertTrue(product.isPresent(), "Product should be present");
        assertEquals(MockDataRepository.LAPTOP.id(), product.get().id());
        assertEquals(new BigDecimal("2499.99"), product.get().price());
    }

    @Test
    @DisplayName("Exercise 3: Should calculate total spent by customer on COMPLETED orders")
    void testExercise3_getTotalSpentByCustomer() {
        // Bob has ORD-002 ($89.94) and ORD-005 ($599.49) completed
        BigDecimal totalBob = exercises.exercise3_getTotalSpentByCustomer(MockDataRepository.BOB);

        assertNotNull(totalBob, "Total spent should not be null");
        assertEquals(new BigDecimal("689.43"), totalBob);
    }

    @Test
    @DisplayName("Exercise 4: Should count products per category")
    void testExercise4_countProductsByCategory() {
        Map<Enum<?>, Long> counts = exercises.exercise4_countProductsByCategory();

        assertNotNull(counts, "Counts map should not be null");
        assertEquals(3L, counts.get(Category.ELECTRONICS));
        assertEquals(2L, counts.get(Category.BOOKS));
        assertEquals(1L, counts.get(Category.HOME_GOODS));
    }

    @Test
    @DisplayName("Exercise 5: Should group COMPLETED orders by customer")
    void testExercise5_groupCompletedOrdersByCustomer() {
        Map<Customer, List<Order>> completedOrders = exercises.exercise5_groupCompletedOrdersByCustomer();

        assertNotNull(completedOrders, "Map should not be null");
        assertTrue(completedOrders.containsKey(MockDataRepository.ALICE));
        assertEquals(1, completedOrders.get(MockDataRepository.ALICE).size(), "Alice has 1 completed order");
        
        assertTrue(completedOrders.containsKey(MockDataRepository.BOB));
        assertEquals(2, completedOrders.get(MockDataRepository.BOB).size(), "Bob has 2 completed orders");
    }

    @Test
    @DisplayName("Exercise 6: Should check if any order contains a product over $2,000")
    void testExercise6_hasHighValueProductInAnyOrder() {
        boolean hasHighValue = exercises.exercise6_hasHighValueProductInAnyOrder();

        assertTrue(hasHighValue, "Should be true because ORD-001 contains MacBook Pro ($2499.99)");
    }

    @Test
    @DisplayName("Exercise 7: Should calculate average total value of completed orders")
    void testExercise7_getAverageCompletedOrderValue() {
        Double avgValue = exercises.exercise7_getAverageCompletedOrderValue();

        assertNotNull(avgValue, "Average value should not be null");
        // ORD-001 ($2899.49) + ORD-002 ($89.94) + ORD-005 ($599.49) = $3588.92 / 3 = 1196.3066...
        assertEquals(1196.30, avgValue, 0.01, "Average should be approximately 1196.30");
    }

    @Test
    @DisplayName("Exercise 8: Should identify top spending customer")
    void testExercise8_getTopSpendingCustomer() {
        Optional<Customer> topCustomer = exercises.exercise8_getTopSpendingCustomer();

        assertTrue(topCustomer.isPresent(), "Top customer should be present");
        assertEquals(MockDataRepository.ALICE.id(), topCustomer.get().id(), "Alice spent the most ($2899.49 in completed orders)");
    }
}