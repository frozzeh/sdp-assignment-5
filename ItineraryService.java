public class ItineraryService {
    private boolean itineraryCreated;

    public void createItinerary(
            String travelerName,
            String destination,
            String hotelName,
            int nights) {
        itineraryCreated = true;
        System.out.println("Itinerary created for: " + travelerName);
        System.out.println(
                "Trip: " + destination + ", " + hotelName + ", " + nights + " nights.");
    }

    public void deleteItinerary() {
        itineraryCreated = false;
        System.out.println("Travel itinerary deleted.");
    }

    public boolean isItineraryCreated() {
        return itineraryCreated;
    }
}
