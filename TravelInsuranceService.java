public class TravelInsuranceService {
    private boolean policyActive;

    public void issuePolicy(String travelerName, String destination) {
        policyActive = true;
        System.out.println(
                "Travel insurance issued for " + travelerName + " to " + destination + ".");
    }

    public void cancelPolicy() {
        policyActive = false;
        System.out.println("Travel insurance policy cancelled.");
    }

    public boolean isPolicyActive() {
        return policyActive;
    }
}
