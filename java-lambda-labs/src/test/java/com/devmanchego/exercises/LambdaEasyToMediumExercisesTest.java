package com.devmanchego.exercises;

import com.devmanchego.data.MockDataRepository;
import com.devmanchego.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class LambdaEasyToMediumExercisesTest {

    private LambdaEasyToMediumExercises exercises;

    @BeforeEach
    void setUp() {
        exercises = new LambdaEasyToMediumExercises();
    }

    @Test
    @DisplayName("Level 1: Should evaluate active status using Predicate")
    void testLevel1() {
        Predicate<Product> isActive = exercises.level1_isProductActivePredicate();
        assertNotNull(isActive, "Predicate should not be null");
        assertTrue(isActive.test(MockDataRepository.LAPTOP), "Laptop should be active");
        assertFalse(isActive.test(MockDataRepository.BOOK_ARCH), "Clean Architecture book should be inactive");
    }

    @Test
    @DisplayName("Level 2: Should map Product to Name using Function")
    void testLevel2() {
        Function<Product, String> nameMapper = exercises.level2_getProductNameFunction();
        assertNotNull(nameMapper, "Function should not be null");
        assertEquals("iPhone 15 Pro", nameMapper.apply(MockDataRepository.PHONE));
    }

    @Test
    @DisplayName("Level 3: Should consume product and print details")
    void testLevel3() {
        Consumer<Product> printer = exercises.level3_printProductConsumer();
        assertNotNull(printer, "Consumer should not be null");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        printer.accept(MockDataRepository.HEADPHONES);

        System.setOut(System.out);
        assertTrue(outContent.toString().contains("Product: Sony WH-1000XM5 - $399.50"));
    }

    @Test
    @DisplayName("Level 4: Should supply product list")
    void testLevel4() {
        Supplier<List<Product>> supplier = exercises.level4_getProductsSupplier();
        assertNotNull(supplier, "Supplier should not be null");
        List<Product> products = supplier.get();
        assertEquals(6, products.size(), "Should supply 6 products");
    }

    @Test
    @DisplayName("Level 5: Should calculate discount price using BiFunction")
    void testLevel5() {
        BiFunction<Product, BigDecimal, BigDecimal> discountCalc = exercises.level5_calculateDiscountedPriceBiFunction();
        assertNotNull(discountCalc, "BiFunction should not be null");

        BigDecimal result = discountCalc.apply(MockDataRepository.LAPTOP, new BigDecimal("10.0"));
        assertEquals(new BigDecimal("2249.991"), result);
    }

    @Test
    @DisplayName("Level 6: Should test combined active and expensive predicate")
    void testLevel6() {
        Predicate<Product> filter = exercises.level6_isExpensiveAndActivePredicate();
        assertNotNull(filter, "Combined predicate should not be null");

        assertTrue(filter.test(MockDataRepository.LAPTOP), "Laptop is active and > $100");
        assertFalse(filter.test(MockDataRepository.BOOK_JAVA), "Java book is active but <= $100");
        assertFalse(filter.test(MockDataRepository.BOOK_ARCH), "Arch book is inactive");
    }

    @Test
    @DisplayName("Level 7: Should map product name in uppercase using function composition")
    void testLevel7() {
        Function<Product, String> upperMapper = exercises.level7_getUpperProductNameFunction();
        assertNotNull(upperMapper, "Chained function should not be null");
        assertEquals("IPHONE 15 PRO", upperMapper.apply(MockDataRepository.PHONE));
    }

    @Test
    @DisplayName("Level 8: Should execute custom generic processing pipeline")
    void testLevel8() {
        List<Product> products = MockDataRepository.getProducts();
        List<String> results = new ArrayList<>();

        exercises.level8_processProducts(
                products,
                p -> p.active(),
                Product::name,
                results::add
        );

        assertEquals(5, results.size(), "Should process 5 active products");
        assertTrue(results.contains("MacBook Pro 16\""));
        assertFalse(results.contains("Clean Architecture"));
    }
}
