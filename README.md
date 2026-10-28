# Assignment #5 — Strategy Pattern

## Topic: TravelBooking

This project extends Assignment #4 (Facade) and retains its Bridge-based payment system. The new Strategy pattern selects the pricing algorithm at runtime.

## Pattern roles

- **Strategy interface:** `PricingStrategy` with `calculatePrice(double basePrice)`.
- **Concrete strategies:** `RegularPricingStrategy` (no discount), `EarlyBookingStrategy` (10% off), `LoyaltyPricingStrategy` (15% off).
- **Context:** `TravelPricingContext` delegates price calculation to its current strategy and supports runtime replacement via `setStrategy`.
- **Client:** `Main` chooses strategies explicitly, calculates prices, and uses the existing `TravelBookingFacade` to book a trip at the selected price.

No `if` or `switch` on strategy type exists inside the context. The pricing figures are illustrative business rules, not actual travel offers.

## Clean Code principles (at least five)

1. **Polymorphism instead of branching:** `TravelPricingContext` invokes `strategy.calculatePrice()` with no strategy-specific conditionals.
2. **Meaningful names:** `EarlyBookingStrategy`, `LoyaltyPricingStrategy`, `calculatePrice`, and `setStrategy` reveal intent.
3. **Single Responsibility:** Each concrete strategy handles exactly one pricing algorithm.
4. **Dependency inversion / abstraction:** The context depends on `PricingStrategy`, not a concrete discount class.
5. **Open/Closed Principle:** Add another pricing strategy by implementing the interface, without editing the context.
6. **Encapsulation:** The current strategy is private and updated through a validated setter.
7. **Input validation:** Invalid prices and null strategies fail with clear exceptions.

## Compile and run

From the project root:

```bash
javac src/*.java
java -cp src Main
```

## Expected pricing output

```text
Regular trip price: $360.00
Early booking trip price: $324.00
Loyalty trip price: $306.00
```

The client then books and cancels a complete trip using the pre-existing Facade. The loyalty discount is allocated across the flight and hotel components; insurance and transfers remain separate services.
