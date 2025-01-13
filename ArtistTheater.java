import java.util.ArrayList;
import java.util.List;

// Custom exception for when no seats are available
class NoSeatAvailableException extends Exception {
    public NoSeatAvailableException(String message) {
        super(message);
    }
}

// Class representing the Artist Theater System
public class ArtistTheater {
    private final String date;
    private final String time;
    private final int totalSeats;
    private final List<Integer> availableSeats;

    // Constructor to initialize date, time, and number of seats
    public ArtistTheater(String date, String time, int totalSeats) {
        this.date = date;
        this.time = time;
        this.totalSeats = totalSeats;
        availableSeats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            availableSeats.add(i);
        }
    }

    // Method to check if there are any seats available
    public boolean ArtistTheaterFull() {
        return availableSeats.isEmpty();
    }

    // Method to buy a ticket
    public void buyTicket() throws NoSeatAvailableException {
        if (ArtistTheaterFull()) {
            throw new NoSeatAvailableException("Sorry, all tickets for the show on " + date + " at " + time + " are sold out.");
        } else {
            int seatNumber = availableSeats.remove(0);
            System.out.println("Ticket purchased successfully for the show on " + date + " at " + time + ". Seat number: " + seatNumber);
        }
    }

    // Method to return a ticket
    public void returnTicket(int seatNumber) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            System.out.println("Invalid seat number. Please provide a valid seat number.");
            return;
        }

        if (!availableSeats.contains(seatNumber)) {
            availableSeats.add(seatNumber);
            System.out.println("Ticket for seat number " + seatNumber + " returned successfully.");
        } else {
            System.out.println("Seat number " + seatNumber + " is already available.");
        }
    }

    // Getter method for date
    public String getDate() {
        return date;
    }

    // Getter method for time
    public String getTime() {
        return time;
    }

    // Getter method for totalSeats
    public int getTotalSeats() {
        return totalSeats;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Constants for date and time
        final String APRIL_20 = "April 20";
        final String APRIL_28 = "April 28";
        final String TIME_1PM = "1:00 PM";
        final String TIME_8PM = "8:00 PM";

        // Creating instances of ArtistTheater
        ArtistTheater theater1 = new ArtistTheater(APRIL_20, TIME_1PM, 30);
        ArtistTheater theater2 = new ArtistTheater(APRIL_28, TIME_8PM, 30);

        try {
            // Trying to buy tickets
            theater1.buyTicket();
            theater1.buyTicket();
            theater1.buyTicket();
        } catch (NoSeatAvailableException e) {
            System.out.println(e.getMessage());
        }

        // Returning a ticket
        theater1.returnTicket(2);

        try {
            // Trying to buy tickets again after returning one
            theater1.buyTicket();
        } catch (NoSeatAvailableException e) {
            System.out.println(e.getMessage());
        }
    }
}
