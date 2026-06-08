public class Main {

    public static void main(String[] args) {

        System.out.println("\n" + "=".repeat(60));
        System.out.println("   WELCOME TO THE HOTEL ROOM RESERVATION SYSTEM");
        System.out.println("=".repeat(60));

        // Create hotel: 3 floors, 5 rooms per floor (15 rooms total)
        Hotel hotel = new Hotel("Grand Palace Hotel", 3, 5);

        // ── Display initial room status ───────────────────────────
        System.out.println("\n>> Initial Room Status:");
        hotel.displayAllRooms();

        // ── Book rooms ────────────────────────────────────────────
        System.out.println("\n>> Booking Rooms...");
        hotel.bookRoom(0, 0, "Rahul Sharma");   // Floor 1, Room 101 — Single
        hotel.bookRoom(0, 2, "Priya Verma");    // Floor 1, Room 103 — Double
        hotel.bookRoom(1, 4, "Amit Joshi");     // Floor 2, Room 205 — Deluxe
        hotel.bookRoom(2, 1, "Sneha Patel");    // Floor 3, Room 302 — Single

        // ── Try booking an already occupied room ──────────────────
        System.out.println("\n>> Attempting to book an already occupied room...");
        hotel.bookRoom(0, 0, "Vikram Singh");   // Room 101 is taken — should show OCCUPIED

        // ── Room status after bookings ────────────────────────────
        System.out.println("\n>> Room Status After Bookings:");
        hotel.displayAllRooms();

        // ── Cancel a booking ──────────────────────────────────────
        System.out.println("\n>> Cancelling booking for Rahul Sharma (Room 101)...");
        hotel.cancelBooking(0, 0);

        // ── Try cancelling a room that's already free ─────────────
        System.out.println("\n>> Attempting to cancel an already available room...");
        hotel.cancelBooking(0, 1);   // Room 102 was never booked

        // ── Available rooms after cancellation ────────────────────
        System.out.println("\n>> Available Rooms After Cancellation:");
        hotel.displayAvailableRooms();

        // ── Generate bills ────────────────────────────────────────
        System.out.println("\n>> Generating Bills...");

        // Priya Verma — Floor 1 Room 103, 4 days, with food
        Room r1 = hotel.getRoom(0, 2);
        if (r1 != null) hotel.displayBill("Priya Verma", 4, r1.getPricePerDay(), true);

        // Amit Joshi — Floor 2 Room 205, 2 days, no food
        Room r2 = hotel.getRoom(1, 4);
        if (r2 != null) hotel.displayBill("Amit Joshi", 2, r2.getPricePerDay(), false);

        // Sneha Patel — Floor 3 Room 302, 5 days, with food
        Room r3 = hotel.getRoom(2, 1);
        if (r3 != null) hotel.displayBill("Sneha Patel", 5, r3.getPricePerDay(), true);

        // ── Hotel summary ─────────────────────────────────────────
        hotel.displaySummary();

        // ── Polymorphism demo ─────────────────────────────────────
        System.out.println("\n>> Room Category Demo (Polymorphism):");
        System.out.println("=".repeat(60));
        Room[] sample = { hotel.getRoom(0, 0), hotel.getRoom(0, 2), hotel.getRoom(0, 4) };
        for (Room r : sample) {
            if (r != null)
                System.out.println("  Room " + r.getRoomNumber() + " → " + r.getRoomCategory());
        }
        System.out.println("=".repeat(60));

        System.out.println("\n>> Program complete. Thank you!\n");
    }
}