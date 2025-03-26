import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightManager {
    private final List<Flight> flights;
    private final RandomGenerator randomGenerator;

    public FlightManager() {
        this.flights = new ArrayList<>();
        this.randomGenerator = new RandomGenerator();
        initializeFlights();
    }

    public void initializeFlights() {
        int numOfFlights = 15;
        for (int i = 0; i < numOfFlights; i++) {
            String[][] chosenDestinations = randomGenerator.randomDestinations();
            String[] distance = calculateDistance(
                Double.parseDouble(chosenDestinations[0][1]),
                Double.parseDouble(chosenDestinations[0][2]),
                Double.parseDouble(chosenDestinations[1][1]),
                Double.parseDouble(chosenDestinations[1][2]));
            
            String schedule = createNewFlightTime();
            String number = randomGenerator.randomFlightNumber().toUpperCase();
            int seats = randomGenerator.randomNumOfSeats();
            String gate = randomGenerator.randomGate();
            
            flights.add(new Flight(schedule, number, seats, chosenDestinations, distance, gate));
        }
    }

    private String[] calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double distance = Math.sin(degreeToRadian(lat1)) * Math.sin(degreeToRadian(lat2)) + 
                         Math.cos(degreeToRadian(lat1)) * Math.cos(degreeToRadian(lat2)) * 
                         Math.cos(degreeToRadian(theta));
        distance = Math.acos(distance);
        distance = radianToDegree(distance);
        distance = distance * 60 * 1.1515;
        
        String[] distanceString = new String[3];
        distanceString[0] = String.format("%.2f", distance * 0.8684); // miles
        distanceString[1] = String.format("%.2f", distance * 1.609344); // km
        distanceString[2] = Double.toString(Math.round(distance * 100.0) / 100.0); // knots
        return distanceString;
    }

    private double degreeToRadian(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double radianToDegree(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    private String createNewFlightTime() {
        LocalDateTime now = LocalDateTime.now();
        int nextFlightDay = (int) (Math.random() * 7);
        
        LocalDateTime date = now.plusDays(nextFlightDay)
                              .plusHours(nextFlightDay)
                              .plusMinutes((now.getMinute() * 3) - (int) (Math.random() * 45));
        
        date = getNearestHourQuarter(date);
        return date.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, HH:mm a"));
    }

    private LocalDateTime getNearestHourQuarter(LocalDateTime datetime) {
        int minutes = datetime.getMinute();
        int mod = minutes % 15;
        LocalDateTime newDatetime;
        if (mod < 8) {
            newDatetime = datetime.minusMinutes(mod);
        } else {
            newDatetime = datetime.plusMinutes(15 - mod);
        }
        return newDatetime.truncatedTo(ChronoUnit.MINUTES);
    }

    public void displayFlightSchedule(UtilityExtract ui) {
        ui.displayFlightSchedule(flights);
    }

    public void deleteFlight(UtilityExtract ui) {
        String flightNumber = ui.promptForFlightNumber();
        if (flights.removeIf(f -> f.getNumber().equalsIgnoreCase(flightNumber))) {
            ui.displaySuccess("Flight deleted successfully");
        } else {
            ui.displayError("Flight not found");
        }
    }

    public void displayFlightPassengers(UtilityExtract ui) {
        String flightNumber = ui.promptForFlightNumber();
        Flight flight = findFlightByNumber(flightNumber);
        if (flight != null) {
            ui.displayFlightPassengers(flight);
        } else {
            ui.displayError("Flight not found");
        }
    }

    public Flight findFlightByNumber(String flightNumber) {
        return flights.stream()
                .filter(f -> f.getNumber().equalsIgnoreCase(flightNumber))
                .findFirst()
                .orElse(null);
    }

    public List<Flight> getAllFlights() {
        return Collections.unmodifiableList(flights);
    }

    public void displayMeasurementInstructions() {
        System.out.println("\nFlight Measurement Guidelines:");
        System.out.println("1. Distances are based on airport coordinates");
        System.out.println("2. Actual flight paths may vary");
        System.out.println("3. Flight times calculated at 450 knots ground speed");
        System.out.println("4. Arrival times are estimates (±1 hour margin)");
        System.out.println("5. Times shown are gate-to-gate");
    }
}