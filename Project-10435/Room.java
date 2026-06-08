public abstract class Room {

    private int     roomNumber;
    private String  roomType;
    private double  pricePerDay;
    private boolean isAvailable;
    private String  guestName;

    public Room(int roomNumber, String roomType, double pricePerDay) {
        this.roomNumber  = roomNumber;
        this.roomType    = roomType;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
        this.guestName   = "N/A";
    }

    // Abstract method — each subclass provides its own implementation
    public abstract String getRoomCategory();

    // Getters
    public int getRoomNumber()    { return roomNumber; }
    public String getRoomType()   { return roomType; }
    public double getPricePerDay(){ return pricePerDay; }
    public boolean isAvailable()  { return isAvailable; }
    public String getGuestName()  { return guestName; }

    // Setters
    public void setAvailable(boolean available) { this.isAvailable = available; }
    public void setGuestName(String guestName)  { this.guestName = guestName; }

    public String getStatusString() {
        String status = isAvailable
                ? "Available"
                : "Occupied  (Guest: " + guestName + ")";
        return String.format("Room %d | %-8s | Rs. %6.0f/day | %s",
                roomNumber, roomType, pricePerDay, status);
    }

    @Override
    public String toString() {
        return getStatusString();
    }
}