public class AirportTransferService {
    private boolean transferScheduled;

    public void scheduleTransfer(String hotelName) {
        transferScheduled = true;
        System.out.println("Airport transfer scheduled to: " + hotelName);
    }

    public void cancelTransfer() {
        transferScheduled = false;
        System.out.println("Airport transfer cancelled.");
    }

    public boolean isTransferScheduled() {
        return transferScheduled;
    }
}
