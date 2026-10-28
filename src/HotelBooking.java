public class HotelBooking extends Booking {
    private final String hotelName;
    private final int nights;
    private final double pricePerNight;

    public HotelBooking(
            String hotelName,
            int nights,
            double pricePerNight,
            PaymentMethod paymentMethod) {
        super(paymentMethod);
        this.hotelName = hotelName;
        this.nights = nights;
        this.pricePerNight = pricePerNight;
    }

    @Override
    public void book() {
        double totalPrice = nights * pricePerNight;

        System.out.println("Hotel booking: " + hotelName);
        System.out.println("Nights: " + nights);
        paymentMethod.pay(totalPrice);
        markAsBooked();
    }

    @Override
    public void cancel() {
        super.cancel();
        System.out.println("Hotel booking at " + hotelName + " cancelled.");
    }
}
