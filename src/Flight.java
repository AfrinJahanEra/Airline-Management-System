
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private final String schedule;
    private final String number;
    private final String fromCity;
    private final String toCity;
    private final String gate;
    private final double distanceMiles;
    private final double distanceKm;
    private final String flightTime;
    private int availableSeats;
    private final List<Customer> passengers;
    private final List<Integer> ticketsPerPassenger;

    public Flight(String schedule, String number, int seats, 
                 String[][] destinations, String[] distance, String gate) {
        this.schedule = schedule;
        this.number = number;
        this.fromCity = destinations[0][0];
        this.toCity = destinations[1][0];
        this.gate = gate;
        this.distanceMiles = Double.parseDouble(distance[0]);
        this.distanceKm = Double.parseDouble(distance[1]);
        this.flightTime = calculateFlightTime(distanceMiles);
        this.availableSeats = seats;
        this.passengers = new ArrayList<>();
        this.ticketsPerPassenger = new ArrayList<>();
    }

    private String calculateFlightTime(double distance) {
        double groundSpeed = 450; // knots
        double time = (distance / groundSpeed);
        String timeInString = String.format("%.4s", time);
        String[] timeArray = timeInString.replace('.', ':').split(":");
        int hours = Integer.parseInt(timeArray[0]);
        int minutes = Integer.parseInt(timeArray[1]);
        int modulus = minutes % 5;
        
        if (modulus < 3) {
            minutes -= modulus;
        } else {
            minutes += 5 - modulus;
        }
        
        if (minutes >= 60) {
            minutes -= 60;
            hours++;
        }
        
        if (hours <= 9 && Integer.toString(minutes).length() == 1) {
            return String.format("0%s:%s0", hours, minutes);
        } else if (hours <= 9 && Integer.toString(minutes).length() > 1) {
            return String.format("0%s:%s", hours, minutes);
        } else if (hours > 9 && Integer.toString(minutes).length() == 1) {
            return String.format("%s:%s0", hours, minutes);
        } else {
            return String.format("%s:%s", hours, minutes);
        }
    }

    public String getArrivalTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, HH:mm a");
        LocalDateTime departureDateTime = LocalDateTime.parse(schedule, formatter);
        
        String[] flightTime = getFlightTime().split(":");
        int hours = Integer.parseInt(flightTime[0]);
        int minutes = Integer.parseInt(flightTime[1]);
        
        LocalDateTime arrivalTime = departureDateTime.plusHours(hours).plusMinutes(minutes);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("EE, dd-MM-yyyy HH:mm a");
        return arrivalTime.format(formatter1);
    }

    public boolean addPassenger(Customer customer, int numTickets) {
        if (availableSeats >= numTickets) {
            if (passengers.contains(customer)) {
                int index = passengers.indexOf(customer);
                ticketsPerPassenger.set(index, ticketsPerPassenger.get(index) + numTickets);
            } else {
                passengers.add(customer);
                ticketsPerPassenger.add(numTickets);
            }
            availableSeats -= numTickets;
            return true;
        }
        return false;
    }

    public boolean removePassenger(Customer customer, int numTickets) {
        int index = passengers.indexOf(customer);
        if (index != -1) {
            int currentTickets = ticketsPerPassenger.get(index);
            if (numTickets >= currentTickets) {
                passengers.remove(index);
                ticketsPerPassenger.remove(index);
            } else {
                ticketsPerPassenger.set(index, currentTickets - numTickets);
            }
            availableSeats += numTickets;
            return true;
        }
        return false;
    }

    // Getters
    public String getSchedule() { return schedule; }
    public String getNumber() { return number; }
    public String getFromCity() { return fromCity; }
    public String getToCity() { return toCity; }
    public String getGate() { return gate; }
    public double getDistanceMiles() { return distanceMiles; }
    public double getDistanceKm() { return distanceKm; }
    public String getFlightTime() { return flightTime; }
    public int getAvailableSeats() { return availableSeats; }
    public List<Customer> getPassengers() { return passengers; }
    public List<Integer> getTicketsPerPassenger() { return ticketsPerPassenger; }
}