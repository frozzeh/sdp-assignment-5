public class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.printf("Payment of $%.2f completed using Credit Card.%n", amount);
    }
}
