import java.util.Objects;

public class TravelPricingContext {
    private PricingStrategy strategy;

    public TravelPricingContext(PricingStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(PricingStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Pricing strategy is required.");
    }

    public double calculatePrice(double basePrice) {
        if (!Double.isFinite(basePrice) || basePrice <= 0) {
            throw new IllegalArgumentException("Base price must be positive and finite.");
        }
        return Math.round(strategy.calculatePrice(basePrice) * 100.0) / 100.0;
    }
}
