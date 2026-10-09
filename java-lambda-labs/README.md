# Java Lambdas & Functional Interfaces Exercises

A practice application focused on **Java Lambda Expressions** and standard **Functional Interfaces** (`Predicate`, `Function`, `Consumer`, `Supplier`, `BiFunction`).

---

## 🛠️ Project Structure

```text
lambda-exercises/
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/devmanchego/
    │   ├── data/
    │   │   └── MockDataRepository.java
    │   ├── exercises/
    │   │   └── LambdaEasyToMediumExercises.java
    │   └── model/
    │       ├── Category.java
    │       ├── Customer.java
    │       ├── Order.java
    │       ├── OrderStatus.java
    │       └── Product.java
    └── test/java/com/devmanchego/exercises/
        └── LambdaEasyToMediumExercisesTest.java
```

---

## 🚀 Execution Commands

Run executable class:
```bash
mvn clean compile exec:java
```

Run unit tests:
```bash
mvn test
```
