package com.devmanchego.data;

import com.devmanchego.model.Category;
import com.devmanchego.model.Customer;
import com.devmanchego.model.Order;
import com.devmanchego.model.OrderStatus;
import com.devmanchego.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public final class MockDataRepository {

    private MockDataRepository() {
        // Prevent instantiation of utility class
    }

    // Sample Products
    public static final Product LAPTOP = new Product("P101", "MacBook Pro 16\"", Category.ELECTRONICS, new BigDecimal("2499.99"), true);
    public static final Product PHONE = new Product("P102", "iPhone 15 Pro", Category.ELECTRONICS, new BigDecimal("1199.00"), true);
    public static final Product HEADPHONES = new Product("P103", "Sony WH-1000XM5", Category.ELECTRONICS, new BigDecimal("399.50"), true);
    public static final Product BOOK_JAVA = new Product("P201", "Java 21 in Action", Category.BOOKS, new BigDecimal("49.99"), true);
    public static final Product BOOK_ARCH = new Product("P202", "Clean Architecture", Category.BOOKS, new BigDecimal("39.95"), false);
    public static final Product COFFEE_MAKER = new Product("P301", "Espresso Machine", Category.HOME_GOODS, new BigDecimal("199.99"), true);

    // Sample Customers
    public static final Customer ALICE = new Customer("C01", "Alice Smith", "alice@example.com", "USA");
    public static final Customer BOB = new Customer("C02", "Bob Jones", "bob@example.com", "Germany");
    public static final Customer CHARLIE = new Customer("C03", "Charlie Brown", "charlie@example.com", "Spain");

    public static List<Product> getProducts() {
        return List.of(LAPTOP, PHONE, HEADPHONES, BOOK_JAVA, BOOK_ARCH, COFFEE_MAKER);
    }

    public static List<Customer> getCustomers() {
        return List.of(ALICE, BOB, CHARLIE);
    }

    public static List<Order> getOrders() {
        return List.of(
            new Order("ORD-001", ALICE, LocalDate.of(2026, 1, 15), List.of(LAPTOP, HEADPHONES), OrderStatus.COMPLETED),
            new Order("ORD-002", BOB, LocalDate.of(2026, 2, 10), List.of(BOOK_JAVA, BOOK_ARCH), OrderStatus.COMPLETED),
            new Order("ORD-003", CHARLIE, LocalDate.of(2026, 3, 5), List.of(PHONE, COFFEE_MAKER), OrderStatus.PENDING),
            new Order("ORD-004", ALICE, LocalDate.of(2026, 4, 20), List.of(BOOK_JAVA), OrderStatus.CANCELLED),
            new Order("ORD-005", BOB, LocalDate.of(2026, 5, 12), List.of(HEADPHONES, COFFEE_MAKER), OrderStatus.COMPLETED)
        );
    }
}