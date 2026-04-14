public class Room {
    private int roomNumber;
    private String roomType;
    private double pricePerDay;
    private boolean isAvailable;

    public Room(int roomNumber, String roomType, double pricePerDay) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }

    public int getRoomNumber()       { return roomNumber; }
    public String getRoomType()      { return roomType; }
    public double getPricePerDay()   { return pricePerDay; }
    public boolean isAvailable()     { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}