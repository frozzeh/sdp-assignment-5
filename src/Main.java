public class Main {
    public static void main(String[] args) {
        double ticketPrice = 120.0;
        double hotelPricePerNight = 80.0;
        int nights = 3;
        double baseTripPrice = ticketPrice + hotelPricePerNight * nights;

        TravelPricingContext pricing = new TravelPricingContext(new RegularPricingStrategy());
        System.out.printf("Regular trip price: $%.2f%n", pricing.calculatePrice(baseTripPrice));

        pricing.setStrategy(new EarlyBookingStrategy());
        System.out.printf("Early booking trip price: $%.2f%n", pricing.calculatePrice(baseTripPrice));

        pricing.setStrategy(new LoyaltyPricingStrategy());
        double discountedTripPrice = pricing.calculatePrice(baseTripPrice);
        System.out.printf("Loyalty trip price: $%.2f%n", discountedTripPrice);

        // Allocate the discounted total proportionally between flight and hotel,
        // so the existing Facade and Bridge payment flow remain unchanged.
        double discountedFlightPrice = Math.round(discountedTripPrice * ticketPrice / baseTripPrice * 100.0) / 100.0;
        double discountedHotelPricePerNight = (discountedTripPrice - discountedFlightPrice) / nights;

        TravelBookingFacade travelBooking = new TravelBookingFacade();
        System.out.println("\n=== Complete Travel Booking (Loyalty Pricing) ===");
        travelBooking.bookCompleteTrip("Aruzhan", "Almaty", discountedFlightPrice,
                "Almaty Grand Hotel", nights, discountedHotelPricePerNight, "PayPal");
        System.out.println("\n=== Complete Travel Cancellation ===");
        travelBooking.cancelCompleteTrip();
    }
}
