public abstract class Booking {
    protected PaymentMethod paymentMethod;
    private boolean booked;

    protected Booking(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    protected void markAsBooked() {
        booked = true;
    }

    public void cancel() {
        booked = false;
    }

    public boolean isBooked() {
        return booked;
    }

    public abstract void book();
}
