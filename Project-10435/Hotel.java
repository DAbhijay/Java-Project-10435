public class Hotel implements Service {

    private String   hotelName;
    private Room[][] rooms;
    private int      totalFloors;
    private int      roomsPerFloor;

    public Hotel(String hotelName, int totalFloors, int roomsPerFloor) {
        this.hotelName     = hotelName;
        this.totalFloors   = totalFloors;
        this.roomsPerFloor = roomsPerFloor;
        this.rooms         = new Room[totalFloors][roomsPerFloor];
        initializeRooms();
    }

    private void initializeRooms() {
        for (int floor = 0; floor < totalFloors; floor++) {
            for (int room = 0; room < roomsPerFloor; room++) {
                int roomNumber = (floor + 1) * 100 + (room + 1);
                if (room < 2) {
                    rooms[floor][room] = new SingleRoom(roomNumber);
                } else if (room < 4) {
                    rooms[floor][room] = new DoubleRoom(roomNumber);
                } else {
                    rooms[floor][room] = new DeluxeRoom(roomNumber);
                }
            }
        }
    }

    // ── Booking ──────────────────────────────────────────────────

    public boolean bookRoom(int floor, int roomIndex, String guestName) {
        if (!isValidIndex(floor, roomIndex)) {
            System.out.println("[ERROR] Invalid floor or room index.");
            return false;
        }
        Room r = rooms[floor][roomIndex];
        if (r.isAvailable()) {
            r.setAvailable(false);
            r.setGuestName(guestName);
            System.out.println("\n[SUCCESS] Room " + r.getRoomNumber()
                    + " (" + r.getRoomType() + ") booked successfully for " + guestName + ".");
            System.out.println("          " + r.getRoomCategory());
            return true;
        } else {
            System.out.println("\n[OCCUPIED] Room " + r.getRoomNumber()
                    + " is already occupied by " + r.getGuestName() + ".");
            return false;
        }
    }

    // ── Cancellation ─────────────────────────────────────────────

    public boolean cancelBooking(int floor, int roomIndex) {
        if (!isValidIndex(floor, roomIndex)) {
            System.out.println("[ERROR] Invalid floor or room index.");
            return false;
        }
        Room r = rooms[floor][roomIndex];
        if (!r.isAvailable()) {
            String prev = r.getGuestName();
            r.setAvailable(true);
            r.setGuestName("N/A");
            System.out.println("\n[CANCELLED] Booking for Room " + r.getRoomNumber()
                    + " (Guest: " + prev + ") has been cancelled.");
            return true;
        } else {
            System.out.println("\n[INFO] Room " + r.getRoomNumber()
                    + " is already available. No active booking to cancel.");
            return false;
        }
    }

    // ── Service Interface Implementation ─────────────────────────

    @Override
    public double calculateBill(int days, double rate) {
        return days * rate;
    }

    @Override
    public double calculateFullBill(int days, double rate, boolean includeFood) {
        double base     = calculateBill(days, rate);
        double food     = includeFood ? (FOOD_CHARGE * days) : 0.0;
        double subtotal = base + food + SERVICE_FEE;
        double tax      = subtotal * TAX_RATE;
        return subtotal + tax;
    }

    @Override
    public void displayBill(String guestName, int days, double rate, boolean includeFood) {
        double base     = calculateBill(days, rate);
        double food     = includeFood ? (FOOD_CHARGE * days) : 0.0;
        double subtotal = base + food + SERVICE_FEE;
        double tax      = subtotal * TAX_RATE;
        double total    = subtotal + tax;

        System.out.println("\n" + "=".repeat(50));
        System.out.println("         HOTEL BILL — " + hotelName);
        System.out.println("=".repeat(50));
        System.out.printf("  Guest Name   : %s%n",        guestName);
        System.out.printf("  Duration     : %d day(s)%n", days);
        System.out.printf("  Room Rate    : Rs. %.2f/day%n", rate);
        System.out.println("-".repeat(50));
        System.out.printf("  Room Charges : Rs. %.2f%n",  base);
        System.out.printf("  Food Charges : Rs. %.2f%n",  food);
        System.out.printf("  Service Fee  : Rs. %.2f%n",  SERVICE_FEE);
        System.out.println("-".repeat(50));
        System.out.printf("  Subtotal     : Rs. %.2f%n",  subtotal);
        System.out.printf("  Tax (10%%)    : Rs. %.2f%n", tax);
        System.out.println("=".repeat(50));
        System.out.printf("  TOTAL BILL   : Rs. %.2f%n",  total);
        System.out.println("=".repeat(50));
    }

    // ── Display Methods ──────────────────────────────────────────

    public void displayAllRooms() {
        System.out.println("\n" + "=".repeat(62));
        System.out.println("           ROOM STATUS — " + hotelName);
        System.out.println("=".repeat(62));
        for (int floor = 0; floor < totalFloors; floor++) {
            System.out.println("\n  [ Floor " + (floor + 1) + " ]");
            System.out.println("  " + "-".repeat(58));
            for (int room = 0; room < roomsPerFloor; room++) {
                System.out.println("  " + rooms[floor][room].getStatusString());
            }
        }
        System.out.println("\n" + "=".repeat(62));
    }

    public void displayAvailableRooms() {
        System.out.println("\n" + "=".repeat(62));
        System.out.println("         AVAILABLE ROOMS — " + hotelName);
        System.out.println("=".repeat(62));
        boolean any = false;
        for (int floor = 0; floor < totalFloors; floor++) {
            for (int room = 0; room < roomsPerFloor; room++) {
                if (rooms[floor][room].isAvailable()) {
                    System.out.println("  Floor " + (floor + 1)
                            + " | " + rooms[floor][room].getStatusString());
                    any = true;
                }
            }
        }
        if (!any) System.out.println("  No rooms currently available.");
        System.out.println("=".repeat(62));
    }

    public void displaySummary() {
        int total = totalFloors * roomsPerFloor, occupied = 0, available = 0;
        for (int f = 0; f < totalFloors; f++)
            for (int r = 0; r < roomsPerFloor; r++)
                if (rooms[f][r].isAvailable()) available++; else occupied++;

        System.out.println("\n" + "=".repeat(40));
        System.out.println("          HOTEL SUMMARY");
        System.out.println("=".repeat(40));
        System.out.printf("  Hotel       : %s%n",  hotelName);
        System.out.printf("  Total Rooms : %d%n",  total);
        System.out.printf("  Occupied    : %d%n",  occupied);
        System.out.printf("  Available   : %d%n",  available);
        System.out.println("=".repeat(40));
    }

    // ── Helpers ──────────────────────────────────────────────────

    private boolean isValidIndex(int floor, int roomIndex) {
        return floor >= 0 && floor < totalFloors
                && roomIndex >= 0 && roomIndex < roomsPerFloor;
    }

    public Room getRoom(int floor, int roomIndex) {
        if (!isValidIndex(floor, roomIndex)) return null;
        return rooms[floor][roomIndex];
    }

    public String getHotelName() { return hotelName; }
}