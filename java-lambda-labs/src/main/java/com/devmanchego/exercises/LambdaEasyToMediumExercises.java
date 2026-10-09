package com.devmanchego.exercises;

import com.devmanchego.data.MockDataRepository;
import com.devmanchego.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaEasyToMediumExercises {

    // =========================================================================
    // LEVEL 1: Predicate - Active Products
    // Difficulty: ★☆☆☆☆
    // Goal: Define a Predicate lambda that checks if a product is active.
    // Hint: product -> product.active()
    // =========================================================================
    public Predicate<Product> level1_isProductActivePredicate() {
        return product -> product.active();
    }

    // =========================================================================
    // LEVEL 2: Function - Product Name Mapper
    // Difficulty: ★☆☆☆☆
    // Goal: Return a Function lambda that maps a Product object to its String name.
    // Hint: Product::name or product -> product.name()
    // =========================================================================
    public Function<Product, String> level2_getProductNameFunction() {
        return Product::name;
    }

    // =========================================================================
    // LEVEL 3: Consumer - Print Product Details
    // Difficulty: ★★☆☆☆
    // Goal: Return a Consumer lambda that prints a product formatted as "Product: [NAME] - $[PRICE]".
    // Hint: product -> System.out.println("Product: " + product.name() + " - $" + product.price())
    // =========================================================================
    public Consumer<Product> level3_printProductConsumer() {
        return product -> System.out.println("Product: " + product.name() + " - $" + product.price());
    }

    // =========================================================================
    // LEVEL 4: Supplier - Provide Default Product List
    // Difficulty: ★★☆☆☆
    // Goal: Return a Supplier lambda that provides the full product list from MockDataRepository.
    // Hint: () -> MockDataRepository.getProducts()
    // =========================================================================
    public Supplier<List<Product>> level4_getProductsSupplier() {
        return MockDataRepository::getProducts;
    }

    // =========================================================================
    // LEVEL 5: BiFunction - Discount Calculator
    // Difficulty: ★★★☆☆
    // Goal: Return a BiFunction lambda that accepts a Product and a discount percentage (BigDecimal, e.g. 10.0),
    //       and returns the new discounted price (BigDecimal).
    // Formula: price - (price * percentage / 100)
    // =========================================================================
    public BiFunction<Product, BigDecimal, BigDecimal> level5_calculateDiscountedPriceBiFunction() {
        return (product, percentage) -> {
            BigDecimal discount = product.price().multiply(percentage).divide(new BigDecimal("100"));
            return product.price().subtract(discount);
        };
    }

    // =========================================================================
    // LEVEL 6: Combined Predicates - High Value & Active
    // Difficulty: ★★★☆☆
    // Goal: Combine two predicates to check if a product is active AND costs more than $100.
    // Hint: isProductActivePredicate().and(...)
    // =========================================================================
    public Predicate<Product> level6_isExpensiveAndActivePredicate() {
        Predicate<Product> isActive = level1_isProductActivePredicate();
        Predicate<Product> isExpensive = product -> product.price().compareTo(new BigDecimal("100.00")) > 0;
        return isActive.and(isExpensive);
    }

    // =========================================================================
    // LEVEL 7: Chained Functions - Uppercase Formatted Product Info
    // Difficulty: ★★★★☆
    // Goal: Chain two functions: first map Product to Name, then convert the Name to uppercase.
    // Hint: getProductNameFunction().andThen(String::toUpperCase)
    // =========================================================================
    public Function<Product, String> level7_getUpperProductNameFunction() {
    	 return level2_getProductNameFunction()
    	            .andThen(String::toUpperCase);
    }

    // =========================================================================
    // LEVEL 8: Custom Processing Engine Method
    // Difficulty: ★★★★★
    // Goal: Process a list of products by filtering with Predicate, transforming with Function,
    //       and performing side effects on results using Consumer.
    // =========================================================================
    public <T, R> void level8_processProducts(
            List<T> items,
            Predicate<T> filterCondition,
            Function<T, R> mapper,
            Consumer<R> action) {
    	
    	 items.stream()
         	.filter(filterCondition)
         	.map(mapper)
         	.forEach(action);
    }

    // =========================================================================
    // MAIN METHOD: Console execution test runner
    // =========================================================================
    public static void main(String[] args) {
        LambdaEasyToMediumExercises exercises = new LambdaEasyToMediumExercises();
        List<Product> products = MockDataRepository.getProducts();

        System.out.println("=== LEVEL 1: Active Products Predicate ===");
        try {
            Predicate<Product> isActive = exercises.level1_isProductActivePredicate();
            products.stream().filter(isActive).forEach(p -> System.out.println("- " + p.name()));
        } catch (Exception e) { System.out.println("L1 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 2: Product Name Function ===");
        try {
            Function<Product, String> nameMapper = exercises.level2_getProductNameFunction();
            products.forEach(p -> System.out.println("Name: " + nameMapper.apply(p)));
        } catch (Exception e) { System.out.println("L2 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 3: Print Product Consumer ===");
        try {
            Consumer<Product> printer = exercises.level3_printProductConsumer();
            products.forEach(printer);
        } catch (Exception e) { System.out.println("L3 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 4: Products Supplier ===");
        try {
            Supplier<List<Product>> supplier = exercises.level4_getProductsSupplier();
            System.out.println("Supplied count: " + supplier.get().size());
        } catch (Exception e) { System.out.println("L4 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 5: Discount Calculator BiFunction ===");
        try {
            BiFunction<Product, BigDecimal, BigDecimal> discountCalc = exercises.level5_calculateDiscountedPriceBiFunction();
            Product laptop = MockDataRepository.LAPTOP;
            BigDecimal discounted = discountCalc.apply(laptop, new BigDecimal("10.0"));
            System.out.println(laptop.name() + " 10% off: $" + discounted);
        } catch (Exception e) { System.out.println("L5 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 6: Expensive & Active Predicate ===");
        try {
            Predicate<Product> filter = exercises.level6_isExpensiveAndActivePredicate();
            products.stream().filter(filter).forEach(p -> System.out.println("Expensive Active: " + p.name()));
        } catch (Exception e) { System.out.println("L6 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 7: Uppercase Product Name Function ===");
        try {
            Function<Product, String> upperMapper = exercises.level7_getUpperProductNameFunction();
            products.forEach(p -> System.out.println("UPPER: " + upperMapper.apply(p)));
        } catch (Exception e) { System.out.println("L7 Pending: " + e.getMessage()); }

        System.out.println("\n=== LEVEL 8: Custom Processing Engine ===");
        try {
            exercises.level8_processProducts(
                    products,
                    p -> p.price().compareTo(new BigDecimal("100")) > 0,
                    Product::name,
                    name -> System.out.println("Engine Output: " + name)
            );
        } catch (Exception e) { System.out.println("L8 Pending: " + e.getMessage()); }
    }
}
