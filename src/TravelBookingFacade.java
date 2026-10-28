public class TravelBookingFacade {
    private final TravelInsuranceService insuranceService;
    private final AirportTransferService transferService;
    private final ItineraryService itineraryService;

    private Booking flightBooking;
    private Booking hotelBooking;
    private boolean tripBooked;

    public TravelBookingFacade() {
        insuranceService = new TravelInsuranceService();
        transferService = new AirportTransferService();
        itineraryService = new ItineraryService();
    }

    public void bookCompleteTrip(
            String travelerName,
            String destination,
            double ticketPrice,
            String hotelName,
            int nights,
            double pricePerNight,
            String paymentType) {
        validateTrip(
                travelerName,
                destination,
                ticketPrice,
                hotelName,
                nights,
                pricePerNight);

        if (tripBooked) {
            throw new IllegalStateException("A complete trip is already booked.");
        }

        PaymentMethod paymentMethod = createPaymentMethod(paymentType);
        flightBooking = new FlightBooking(destination, ticketPrice, paymentMethod);
        hotelBooking = new HotelBooking(hotelName, nights, pricePerNight, paymentMethod);

        System.out.println("Booking a complete trip for: " + travelerName);

        try {
            flightBooking.book();
            hotelBooking.book();
            insuranceService.issuePolicy(travelerName, destination);
            transferService.scheduleTransfer(hotelName);
            itineraryService.createItinerary(
                    travelerName,
                    destination,
                    hotelName,
                    nights);
            tripBooked = true;

            System.out.println("Complete trip booked successfully.");
        } catch (RuntimeException exception) {
            rollbackBooking();
            throw new IllegalStateException(
                    "Could not complete the travel booking.",
                    exception);
        }
    }

    public void cancelCompleteTrip() {
        if (!tripBooked) {
            throw new IllegalStateException("There is no complete trip to cancel.");
        }

        System.out.println("Cancelling complete trip...");
        rollbackBooking();
        tripBooked = false;
        System.out.println("Complete trip cancelled.");
    }

    private PaymentMethod createPaymentMethod(String paymentType) {
        if (paymentType == null) {
            throw new IllegalArgumentException("Payment type cannot be empty.");
        }

        if (paymentType.equalsIgnoreCase("credit card")) {
            return new CreditCardPayment();
        }

        if (paymentType.equalsIgnoreCase("paypal")) {
            return new PayPalPayment();
        }

        throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
    }

    private void validateTrip(
            String travelerName,
            String destination,
            double ticketPrice,
            String hotelName,
            int nights,
            double pricePerNight) {
        if (travelerName == null || travelerName.isBlank()) {
            throw new IllegalArgumentException("Traveler name cannot be empty.");
        }

        if (destination == null || destination.isBlank()) {
            throw new IllegalArgumentException("Destination cannot be empty.");
        }

        if (hotelName == null || hotelName.isBlank()) {
            throw new IllegalArgumentException("Hotel name cannot be empty.");
        }

        if (ticketPrice <= 0 || pricePerNight <= 0 || nights <= 0) {
            throw new IllegalArgumentException("Prices and number of nights must be positive.");
        }
    }

    private void rollbackBooking() {
        itineraryService.deleteItinerary();
        transferService.cancelTransfer();
        insuranceService.cancelPolicy();

        if (hotelBooking != null && hotelBooking.isBooked()) {
            hotelBooking.cancel();
        }

        if (flightBooking != null && flightBooking.isBooked()) {
            flightBooking.cancel();
        }
    }
}
