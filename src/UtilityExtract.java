import java.util.List;
import java.util.Scanner;


public class UtilityExtract {
    private final Scanner scanner;

    public UtilityExtract() {
        this.scanner = new Scanner(System.in);
    }

    /* Main Menu Methods */
    public void displayMainMenu() {
        System.out.println("\n\t\t\t\t\t+++++++++++++ Welcome to BAV AirLines +++++++++++++");
        System.out.println("\n***** Default Username && Password is root-root *****");
        System.out.println("\n\t(a) Press 0 to Exit.");
        System.out.println("\t(b) Press 1 to Login as admin.");
        System.out.println("\t(c) Press 2 to Register as admin.");
        System.out.println("\t(d) Press 3 to Login as Passenger.");
        System.out.println("\t(e) Press 4 to Register as Passenger.");
        System.out.println("\t(f) Press 5 to Display the User Manual.");
        System.out.print("\tEnter the desired option: ");
    }

    public int getMainMenuChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        while (choice < 0 || choice > 5) {
            System.out.print("Invalid choice (0-5): ");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice;
    }

    /* Admin Menu Methods */
    public void displayAdminMenu(String username) {
        System.out.printf("\n\n%-60s+++++++++ Admin Menu +++++++++%50sLogged in as \"%s\"\n", "", "", username);
        System.out.printf("%-30s (a) Enter 1 to add new Passenger\n", "");
        System.out.printf("%-30s (b) Enter 2 to search a Passenger\n", "");
        System.out.printf("%-30s (c) Enter 3 to update Passenger data\n", "");
        System.out.printf("%-30s (d) Enter 4 to delete a Passenger\n", "");
        System.out.printf("%-30s (e) Enter 5 to Display all Passengers\n", "");
        System.out.printf("%-30s (f) Enter 6 to Display passenger flights\n", "");
        System.out.printf("%-30s (g) Enter 7 to Display flight passengers\n", "");
        System.out.printf("%-30s (h) Enter 8 to Delete a Flight\n", "");
        System.out.printf("%-30s (i) Enter 0 to Logout\n", "");
        System.out.print("Enter your choice: ");
    }

    public int getAdminMenuChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 0 || choice > 8) {
            System.out.print("Invalid choice (0-8): ");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice;
    }

    /* Customer Menu Methods */
    public void displayCustomerMenu(String username) {
        System.out.printf("\n\n%-60s+++++++++ Customer Menu +++++++++%50sLogged in as \"%s\"\n", "", "", username);
        System.out.printf("%-30s (a) Enter 1 to Book a flight\n", "");
        System.out.printf("%-30s (b) Enter 2 to Update your data\n", "");
        System.out.printf("%-30s (c) Enter 3 to Delete your account\n", "");
        System.out.printf("%-30s (d) Enter 4 to View flight schedule\n", "");
        System.out.printf("%-30s (e) Enter 5 to Cancel a flight\n", "");
        System.out.printf("%-30s (f) Enter 6 to View your bookings\n", "");
        System.out.printf("%-30s (g) Enter 0 to Logout\n", "");
        System.out.print("Enter your choice: ");
    }

    public int getCustomerMenuChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        while (choice < 0 || choice > 6) {
            System.out.print("Invalid choice (0-6): ");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice;
    }

    /* Input Methods */
    public String[] promptForCredentials() {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        return new String[]{username, password};
    }

    public CustomerRegistrationData promptForCustomerRegistration() {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        
        return new CustomerRegistrationData(username, password, name, email, phone, address, age);
    }

    public CustomerRegistrationData promptForCustomerUpdate() {
        System.out.print("\nEnter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter new address: ");
        String address = scanner.nextLine();
        System.out.print("Enter new age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        
        return new CustomerRegistrationData(null, null, name, email, phone, address, age);
    }

    public String promptForCustomerId() {
        System.out.print("\nEnter customer ID: ");
        return scanner.nextLine();
    }

    public String promptForFlightNumber() {
        System.out.print("\nEnter flight number: ");
        return scanner.nextLine();
    }

    public int promptForNumberOfTickets() {
        System.out.print("\nEnter number of tickets: ");
        int num = scanner.nextInt();
        scanner.nextLine();
        while (num <= 0) {
            System.out.print("Number must be positive. Enter again: ");
            num = scanner.nextInt();
            scanner.nextLine();
        }
        return num;
    }

    public boolean confirmAction(String message) {
        System.out.print("\n" + message + " (Y/N): ");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("Y");
    }

    /* Display Methods */
    public void displayError(String message) {
        System.out.println("\n[ERROR] " + message);
    }

    public void displaySuccess(String message) {
        System.out.println("\n[SUCCESS] " + message);
    }

    public void displayFlightSchedule(List<Flight> flights) {
        if (flights.isEmpty()) {
            System.out.println("\nNo flights available.");
            return;
        }

        System.out.println("\nFlight Schedule:");
        System.out.println("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+");
        System.out.printf("| Num  | FLIGHT SCHEDULE\t\t\t   | FLIGHT NO | Available Seats  | \tFROM ====>>       | \t====>> TO\t   | \t    ARRIVAL TIME       | FLIGHT TIME |  GATE  |   DISTANCE(MILES/KMS)  |%n");
        System.out.println("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+");
        
        int i = 1;
        for (Flight flight : flights) {
            System.out.printf("| %-5d| %-41s | %-9s | \t%-9d | %-21s | %-22s | %-10s  |   %-6sHrs |  %-4s  |  %-8.2f / %-11.2f|%n",
                i++,
                flight.getSchedule(),
                flight.getNumber(),
                flight.getAvailableSeats(),
                flight.getFromCity(),
                flight.getToCity(),
                flight.getArrivalTime(),
                flight.getFlightTime(),
                flight.getGate(),
                flight.getDistanceMiles(),
                flight.getDistanceKm());
            System.out.println("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+");
        }
    }

    public void displayCustomerDetails(Customer customer) {
        System.out.println("\nCustomer Details:");
        System.out.println("+------------+----------------------------------+-----------------------------+-------------------------+-------------------------------------+---------+");
        System.out.println("|   UserID   | Passenger Names                  | EmailID\t\t       | Phone Number\t       | Home Address\t\t\t     | Age     |");
        System.out.println("+------------+----------------------------------+-----------------------------+-------------------------+-------------------------------------+---------+");
        System.out.printf("| %-10s | %-32s | %-27s | %-23s | %-35s | %-7d |%n",
            customer.getUserId(),
            customer.getName(),
            customer.getEmail(),
            customer.getPhone(),
            customer.getAddress(),
            customer.getAge());
        System.out.println("+------------+----------------------------------+-----------------------------+-------------------------+-------------------------------------+---------+");
    }

    public void displayAllCustomers(List<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("\nNo customers registered.");
            return;
        }

        System.out.println("\nAll Customers:");
        System.out.println("+------+------------+----------------------------------+-----------------------------+-------------------------+-------------------------------------+---------+");
        System.out.println("| Num  |   UserID   | Passenger Names                  | EmailID\t\t       | Phone Number\t       | Home Address\t\t\t     | Age     |");
        System.out.println("+------+------------+----------------------------------+-----------------------------+-------------------------+-------------------------------------+---------+");
        
        int i = 1;
        for (Customer customer : customers) {
            System.out.printf("| %-5d| %-10s | %-32s | %-27s | %-23s | %-35s | %-7d |%n",
                i++,
                customer.getUserId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getAge());
            System.out.println("+------+------------+----------------------------------+-----------------------------+-------------------------+-------------------------------------+---------+");
        }
    }

    public void displayCustomerBookings(List<FlightBooking> bookings) {
        if (bookings.isEmpty()) {
            System.out.println("\nNo bookings found.");
            return;
        }

        System.out.println("\nYour Bookings:");
        System.out.println("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+");
        System.out.printf("| Num  | FLIGHT SCHEDULE\t\t\t   | FLIGHT NO |  Booked Tickets  | \tFROM ====>>       | \t====>> TO\t   | \t    ARRIVAL TIME       | FLIGHT TIME |  GATE  |  FLIGHT STATUS  |%n");
        System.out.println("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+");
        
        int i = 1;
        for (FlightBooking booking : bookings) {
            System.out.printf("| %-5d| %-41s | %-9s | %-16d | %-21s | %-22s | %-10s  |   %-6sHrs |  %-4s  | %-15s |%n",
                i++,
                booking.getFlight().getSchedule(),
                booking.getFlight().getNumber(),
                booking.getNumTickets(),
                booking.getFlight().getFromCity(),
                booking.getFlight().getToCity(),
                booking.getFlight().getArrivalTime(),
                booking.getFlight().getFlightTime(),
                booking.getFlight().getGate(),
                "Confirmed");
            System.out.println("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+");
        }
    }

    public void displayFlightPassengers(Flight flight) {
        if (flight.getPassengers().isEmpty()) {
            System.out.println("\nNo passengers booked for this flight.");
            return;
        }

        System.out.printf("\nPassengers for Flight %s:\n", flight.getNumber());
        System.out.println("+------+------------+----------------------------------+-----------------+");
        System.out.println("| Num  |   UserID   | Passenger Name                  | Tickets Booked |");
        System.out.println("+------+------------+----------------------------------+-----------------+");
        
        int i = 1;
        List<Customer> passengers = flight.getPassengers();
        List<Integer> tickets = flight.getTicketsPerPassenger();
        
        for (int j = 0; j < passengers.size(); j++) {
            Customer passenger = passengers.get(j);
            System.out.printf("| %-5d| %-10s | %-32s | %-15d |%n",
                i++,
                passenger.getUserId(),
                passenger.getName(),
                tickets.get(j));
            System.out.println("+------+------------+----------------------------------+-----------------+");
        }
    }

    static void manualInstructions() {
        Scanner read = new Scanner(System.in);
        System.out.printf("%n%n%50s %s Welcome to BAV Airlines User Manual %s", "", "+++++++++++++++++",
                "+++++++++++++++++");
        System.out.println("\n\n\t\t(a) Press 1 to display Admin Manual.");
        System.out.println("\t\t(b) Press 2 to display User Manual.");
        System.out.print("\nEnter the desired option :    ");
        int choice = read.nextInt();
        while (choice < 1 || choice > 2) {
            System.out.print("ERROR!!! Invalid entry...Please enter a value either 1 or 2....Enter again....");
            choice = read.nextInt();
        }
        if (choice == 1) {
            System.out.println(
                    "\n\n(1) Admin have the access to all users data...Admin can delete, update, add and can perform search for any customer...\n");
            System.out.println(
                    "(2) In order to access the admin module, you've to get yourself register by pressing 2, when the main menu gets displayed...\n");
            System.out.println(
                    "(3) Provide the required details i.e., name, email, id...Once you've registered yourself, press 1 to login as an admin... \n");
            System.out.println(
                    "(4) Once you've logged in, 2nd layer menu will be displayed on the screen...From here on, you can select from variety of options...\n");
            System.out.println(
                    "(5) Pressing \"1\" will add a new Passenger, provide the program with required details to add the passenger...\n");
            System.out.println(
                    "(6) Pressing \"2\" will search for any passenger, given the admin(you) provides the ID from the table printing above....  \n");
            System.out.println(
                    "(7) Pressing \"3\" will let you update any passengers data given the user ID provided to program...\n");
            System.out.println("(8) Pressing \"4\" will let you delete any passenger given its ID provided...\n");
            System.out.println("(9) Pressing \"5\" will let you display all registered passenger...\n");
            System.out.println(
                    "(10) Pressing \"6\" will let you display all registered passengers...After selecting, program will ask, if you want to display passengers for all flights(Y/y) or a specific flight(N/n)\n");
            System.out.println(
                    "(11) Pressing \"7\" will let you delete any flight given its flight number provided...\n");
            System.out.println(
                    "(11) Pressing \"0\" will make you logged out of the program...You can login again any time you want during the program execution....\n");
        } else {
            System.out.println(
                    "\n\n(1) Local user has the access to its data only...He/She won't be able to change/update other users data...\n");
            System.out.println(
                    "(2) In order to access local users benefits, you've to get yourself register by pressing 4, when the main menu gets displayed...\n");
            System.out.println(
                    "(3) Provide the details asked by the program to add you to the users list...Once you've registered yourself, press \"3\" to login as a passenger...\n");
            System.out.println(
                    "(4) Once you've logged in, 3rd layer menu will be displayed...From here on, you embarked on the journey to fly with us...\n");
            System.out.println(
                    "(5) Pressing \"1\" will display available/scheduled list of flights...To get yourself booked for a flight, enter the flight number and number of tickets for the flight...Max num of tickets at a time is 10 ...\n");
            System.out.println(
                    "(7) Pressing \"2\" will let you update your own data...You won't be able to update other's data... \n");
            System.out.println("(8) Pressing \"3\" will delete your account... \n");
            System.out
                    .println("(9) Pressing \"4\" will display randomly designed flight schedule for this runtime...\n");
            System.out.println("(10) Pressing \"5\" will let you cancel any flight registered by you...\n");
            System.out.println("(11) Pressing \"6\" will display all flights registered by you...\n");
            System.out.println(
                    "(12) Pressing \"0\" will make you logout of the program...You can login back at anytime with your credentials...for this particular run-time... \n");
        }
    }

    public void displayMeasurementInstructions() {
        System.out.println("\nFlight Measurement Guidelines:");
        System.out.println("1. Distances between cities are calculated using their airport coordinates");
        System.out.println("2. Actual flight paths may vary due to airline policies and air traffic");
        System.out.println("3. Flight duration is calculated at a standard ground speed of 450 knots");
        System.out.println("4. All times shown are local times for each airport");
        System.out.println("5. Departure time is when the plane pushes back from the gate");
        System.out.println("6. Arrival time is when the plane arrives at the destination gate");
    }

    public void displayFlightDetails(Flight flight) {
        System.out.println("\nFlight Details:");
        System.out.println("+----------------------+-------------------------------------------+");
        System.out.printf("| %-20s | %-41s |\n", "Flight Number", flight.getNumber());
        System.out.printf("| %-20s | %-41s |\n", "Departure", flight.getSchedule());
        System.out.printf("| %-20s | %-41s |\n", "From", flight.getFromCity());
        System.out.printf("| %-20s | %-41s |\n", "To", flight.getToCity());
        System.out.printf("| %-20s | %-41s |\n", "Arrival Time", flight.getArrivalTime());
        System.out.printf("| %-20s | %-41s |\n", "Flight Duration", flight.getFlightTime() + " hours");
        System.out.printf("| %-20s | %-41s |\n", "Gate", flight.getGate());
        System.out.printf("| %-20s | %-41.2f |\n", "Distance (Miles)", flight.getDistanceMiles());
        System.out.printf("| %-20s | %-41.2f |\n", "Distance (Km)", flight.getDistanceKm());
        System.out.printf("| %-20s | %-41d |\n", "Available Seats", flight.getAvailableSeats());
        System.out.println("+----------------------+-------------------------------------------+");
    }

    public void displayBookingConfirmation(FlightBooking booking) {
        System.out.println("\nBooking Confirmation:");
        System.out.println("+----------------------+-------------------------------------------+");
        System.out.printf("| %-20s | %-41s |\n", "Booking ID", booking.getBookingId());
        System.out.printf("| %-20s | %-41s |\n", "Flight Number", booking.getFlight().getNumber());
        System.out.printf("| %-20s | %-41s |\n", "Route", booking.getFlight().getFromCity() + " to " + booking.getFlight().getToCity());
        System.out.printf("| %-20s | %-41s |\n", "Departure", booking.getFlight().getSchedule());
        System.out.printf("| %-20s | %-41s |\n", "Arrival", booking.getFlight().getArrivalTime());
        System.out.printf("| %-20s | %-41d |\n", "Tickets Booked", booking.getNumTickets());
        System.out.printf("| %-20s | %-41s |\n", "Booking Time", booking.getBookingTime());
        System.out.println("+----------------------+-------------------------------------------+");
    }

    public void displayAdminWelcome(String username) {
        System.out.printf("\nWelcome, Admin %s!\n", username);
        System.out.println("You have full access to the airline management system.");
    }

    public void displayCustomerWelcome(String username) {
        System.out.printf("\nWelcome, %s!\n", username);
        System.out.println("You can now book flights and manage your account.");
    }

    /* Utility Methods */
    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // If clearing screen fails, just print some newlines
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
        }
    }

    public void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}