import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String roomType;
    boolean isAvailable;

    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = true;
    }
}

class Booking {
    int roomNumber;
    String guestName;
    String bookingDate;
    String checkInDate;
    String checkOutDate;

    public Booking(int roomNumber, String guestName, String bookingDate, String checkInDate, String checkOutDate) {
        this.roomNumber = roomNumber;
        this.guestName = guestName;
        this.bookingDate = bookingDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
}

public class HotelReservationSystem {
    private ArrayList<Room> rooms;
    private ArrayList<Booking> bookings;
    private Scanner scanner;

    public HotelReservationSystem() {
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        // Initialize rooms
        rooms.add(new Room(1, "Single"));
        rooms.add(new Room(2, "Double"));
        rooms.add(new Room(3, "Suite"));
    }

    public void run() {
        while (true) {
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewBookingDetails();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private void searchAvailableRooms() {
        System.out.println("Available rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println("Room Number: " + room.roomNumber + ", Room Type: " + room.roomType);
            }
        }
    }

    private void makeReservation() {
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        Room room = getRoom(roomNumber);
        if (room != null && room.isAvailable) {
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();
            System.out.print("Enter booking date: ");
            String bookingDate = scanner.nextLine();
            System.out.print("Enter check-in date: ");
            String checkInDate = scanner.nextLine();
            System.out.print("Enter check-out date: ");
            String checkOutDate = scanner.nextLine();

            Booking booking = new Booking(roomNumber, guestName, bookingDate, checkInDate, checkOutDate);
            bookings.add(booking);
            room.isAvailable = false;

            System.out.println("Reservation successful.");
        } else {
            System.out.println("Room is not available or does not exist.");
        }
    }

    private void viewBookingDetails() {
        System.out.println("Booking details:");
        for (Booking booking : bookings) {
            System.out.println("Room Number: " + booking.roomNumber + ", Guest Name: " + booking.guestName + ", Booking Date: " + booking.bookingDate + ", Check-in Date: " + booking.checkInDate + ", Check-out Date: " + booking.checkOutDate);
        }
    }

    private Room getRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HotelReservationSystem system = new HotelReservationSystem();
        system.run();
    }
}
