public class PayPalPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.printf("Payment of $%.2f completed using PayPal.%n", amount);
    }
}
